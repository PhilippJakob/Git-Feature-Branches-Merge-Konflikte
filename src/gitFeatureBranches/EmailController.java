package gitFeatureBranches;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.mail.MessagingException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EmailController {

   
   private static ArrayList<Person> personenOEAL	= new ArrayList<Person>();
   private static ArrayList<Person> personenGAL		= new ArrayList<Person>();
   static Connection connection;

     
    @FXML
    private TextField tfBetreff;

    @FXML
    private TextArea taText;

    @FXML
    private Button btVersenden;
    
    @FXML
    private Label lbAbsender;

    @FXML
    private Label lbEmpfänger;

    @FXML
    private ChoiceBox<String> cbAbsender;
    
    @FXML
    private ChoiceBox<String> cbEmpfänger;

    @FXML
    private Label lbGruppen;

    @FXML
    private Label lbEinheiten;
    
    @FXML
    private ChoiceBox<String> cbGruppen;

    @FXML
    private ChoiceBox<String> cbEinheiten;

   
    @FXML
    public void initialize()
    {
       cbAbsender.getItems().addAll(Person.getPersonen()); 
       cbEmpfänger.getItems().addAll(Person.getPersonen());
 	  cbEinheiten.getItems().addAll(Organisationseinheit.getOrganisationseinheiten());
 	  cbGruppen.getItems().addAll(Gruppe.getGruppen());

    }
    
    
    
    public void Versenden()
    {
	  try
	  {
		 SendEmail.generateAndSendEmail(tfBetreff.getText(), cbAbsender.getId(),cbEmpfänger.getId(), taText.getText(), cbGruppen.getId(),cbEinheiten.getId());
	  }
	  catch (MessagingException e)
	  {
	  // TODO Automatisch generierter Erfassungsblock
	  e.printStackTrace();
	  }

    }
    public void aufsplitten()
    {
       String[] tokens;
 	  String pPerson = null;
	  tokens = pPerson.split(" ");
 	 Integer.parseInt(tokens[tokens.length-1]);
    }
    
    
    public static ArrayList<Person> auslesenPersonenAusGruppe(String cbGruppen)
    {
       
       ArrayList<Person> personenausgruppe = new ArrayList<Person>();
       Statement lBefehl;
       ResultSet lErgebnis;
       Person lPerson;
       try
	  {
		 lBefehl 	= connection.createStatement();
	     lErgebnis = lBefehl.executeQuery("select Name,IDPerson from person where IDPerson = ANY(select IDPerson from gruppenzugehörigkeit where IDGruppe = '"+ cbGruppen + "')");

	     
	     lErgebnis.first(); 

	     while(!lErgebnis.isAfterLast())   
	        {
	    	lPerson = new Person(lErgebnis.getString(1),lErgebnis.getInt(2));
	    	personenausgruppe.add(lPerson);
	          lErgebnis.next();
	        }
	  }
	  catch (SQLException e)
	  {
		 // TODO Automatisch generierter Erfassungsblock
		e.printStackTrace();
	  }             
       return personenausgruppe;
       
    }
    
    public static ArrayList<Person> auslesenPersonenAusOE(int cbEinheiten)
    {
       ArrayList<Person> personenausOE = new ArrayList<Person>();
       Statement lBefehl;
       ResultSet lErgebnis;
       Person lPerson;
       
       try
 	  {
 		 lBefehl 	= connection.createStatement();
 	     lErgebnis = lBefehl.executeQuery("Select Name,IDPerson from person where IDPerson = ANY(select IDPerson from `oe-zugehörigkeit` where StID  = '"+ cbEinheiten + "')");

 	     
 	     lErgebnis.first(); 

 	     while(!lErgebnis.isAfterLast())   
 	        {
 	    	lPerson = new Person(lErgebnis.getString(1),lErgebnis.getInt(2));
 	    	personenausOE.add(lPerson);
 	          lErgebnis.next();
 	        }
 	    ArrayList<Organisationseinheit> UntergeordneteOE = new ArrayList<Organisationseinheit>();
 	    
 	    
 	    
 	       for(Organisationseinheit lOE : UntergeordneteOE)
 	       {
 	    	  personenausOE.addAll(auslesenPersonenAusOE(lOE.getID()));
 	    	  
 	       } 	       	     
 	    		  
 	  }
 	  catch (SQLException e)
 	  {
 		 // TODO Automatisch generierter Erfassungsblock
 		e.printStackTrace();
 	  }             
        return personenausOE;        
        
     }
            
   public static ArrayList<Person> getPersonenOEAL()
   {
	  return personenOEAL;
   }



   public static void setPersonenOEAL(ArrayList<Person> personenOEAL)
   {
	  EmailController.personenOEAL = personenOEAL;
   }



   public static ArrayList<Person> getPersonenGAL()
   {
	  return personenGAL;
   }



   public static void setPersonenGAL(ArrayList<Person> personenGAL)
   {
	  EmailController.personenGAL = personenGAL;
   }
}
