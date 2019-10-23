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

public class EmailController
{

   private static ArrayList<Person>	personenOEAL = new ArrayList<Person>();

   @FXML
   private TextField				tfBetreff;

   @FXML
   private TextArea					taText;

   @FXML
   private Button					btVersenden;

   @FXML
   private Label					lbAbsender;

   @FXML
   private Label					lbEmpfänger;

   @FXML
   private ChoiceBox<String>		cbAbsender;

   @FXML
   private ChoiceBox<String>		cbEmpfänger;

   @FXML
   private Label					lbGruppen;

   @FXML
   private Label					lbEinheiten;

   @FXML
   private ChoiceBox<String>		cbGruppen;

   @FXML
   private ChoiceBox<String>		cbEinheiten;

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
		 SendEmail.generateAndSendEmail(
				  tfBetreff.getText(), cbAbsender.getId(), cbEmpfänger.getId(), taText.getText(), cbGruppen.getId(),
				  cbEinheiten.getId()
		 );
	  }
	  catch (MessagingException e)
	  {
		 // TODO Automatisch generierter Erfassungsblock
		 e.printStackTrace();
	  }

   }
   public static void auslesenPersonenAusOE(int pOE)
   {
      //lErgebnisliste
      ArrayList<Person> lErgebnisliste = new ArrayList<Person>();
      Statement lBefehl;
      ResultSet lErgebnis;
      ResultSet lErgebnis1;
      Person lPerson;
      Connection connection = DBVerbindung.holenConnection();
      
      try
	  {
   	  //wenn untergeordneteOE existiert
		 lBefehl 	= connection.createStatement();
	     lErgebnis = lBefehl.executeQuery("select OEID from organisationseinheit where OEÜBER = '"+ pOE +"'");
//		 2 Methode
//	     {
//	     loop überprüfen untergeordneteOE's
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
       

   public void ermittelnPersonen()
   {

   }

   public static ArrayList<Person> getPersonenOEAL()
   {
	  return personenOEAL;
   }

   public static void setPersonenOEAL(ArrayList<Person> personenOEAL)
   {
	  EmailController.personenOEAL = personenOEAL;
   }
}