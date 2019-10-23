package gitFeatureBranches;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.mail.MessagingException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    
    static ArrayList<Integer> OEIDAL = new ArrayList<Integer>();
    
    static ArrayList<Person> Personenliste = new ArrayList<Person>();

   
    @FXML
    public void initialize()
    {
       cbAbsender.getItems().addAll(Person.getPersonen()); 
       cbEmpfänger.getItems().addAll(Person.getPersonen());
 	  cbEinheiten.getItems().addAll(Organisationseinheit.getOrganisationseinheiten());
 	  cbGruppen.getItems().addAll(Gruppe.getGruppen());
 	  cbEinheiten.setOnAction(new EventHandler<ActionEvent>() {
 		 @Override
 		 public void handle(ActionEvent event) 
 		 {
 			String[] tokens;
 			tokens = cbEinheiten.getValue().split(" ");
 			int ID = Integer.parseInt(tokens[tokens.length-1]);
 			OEIDAL.add(ID);
 			auslesenPersonenAusOE(ID);			
		 }});
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
     
//    1 Methode
    public static void auslesenPersonenAusOE(int pOE)
    {
       //lErgebnisliste
       ArrayList<Person> lErgebnisliste = new ArrayList<Person>();
       Statement lBefehl;
       ResultSet lErgebnis;
       ResultSet lErgebnis1;
       Person lPerson;
       connection = DBVerbindung.holenConnection();
       
       try
 	  {
    	  //wenn untergeordneteOE existiert
 		 lBefehl 	= connection.createStatement();
 	     lErgebnis = lBefehl.executeQuery("select OEID from organisationseinheit where OEÜBER = '"+ pOE +"'");
//		 2 Methode
// 	     {
// 	     loop überprüfen untergeordneteOE's
 	     if(lErgebnis.first())
 	     {
	     do
	     	{
	    		OEIDAL.add(lErgebnis.getInt(1));
	    		auslesenPersonenAusOE(lErgebnis.getInt(1));	          
	        }while(lErgebnis.next());
 	     }
 	     {
 	    	lBefehl = connection.createStatement();
 	    	lErgebnis1 = lBefehl.executeQuery("select Name,IDPerson from person where StID = ANY(select StID from stelle where OEID = '" + pOE + "')");
 	     } 
 	     if(lErgebnis1.first()) 
 	     {
 	    	do{
 	    	lPerson = new Person(lErgebnis1.getString(1),lErgebnis1.getInt(2));
 	    	Personenliste.add(lPerson);
 	        }while(lErgebnis1.next());
 	     }
 	  }
 	  catch (SQLException e)
 	  {
 		 // TODO Automatisch generierter Erfassungsblock
 		e.printStackTrace();
 	  }              
        
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
