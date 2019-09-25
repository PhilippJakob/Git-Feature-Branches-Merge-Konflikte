package gitFeatureBranches;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

public class PersonenhinzufügenController
{
   private static DBVerbindung dbVerbindung = new DBVerbindung();

   private int hinzugefügtePersonen =1 ;

   @FXML
   private Button btPersonhinzufügen;

   @FXML
   private TextField tfName;
   //Füllt CB und Handelt Knopfdruck
   @FXML
   public void initialize()
   {

	 btPersonhinzufügen.setOnAction(new EventHandler<ActionEvent>() {
		@Override
	    public void handle(ActionEvent event)
	    { 
		   hinzufügenPerson(tfName.getText(), DBVerbindung.holenConnection());
	    }
   } );
   }
   //Holt sich höchste ID und Fügt Person mit höchsterID+1 hinzu.
   void hinzufügenPerson(String pPerson, Connection connection)
   {
	  Statement lBefehl;
	  String ID = new String();
	  String[] tokens;
	  
	  ID = Person.getLetztePerson();
	  tokens = ID.split(" ");
	  ID = tokens[tokens.length-1];
	  ID = Integer.toString(Integer.parseInt(ID)+hinzugefügtePersonen);
	  hinzugefügtePersonen++; 
	  try
	  {
		 lBefehl= connection.createStatement();
		 lBefehl.executeUpdate("INSERT INTO person(IDPerson,Name,StID) VALUES('"+ID+"','"+pPerson+"',NULL);");
	  }
	  catch (SQLException e)
	  {
		 // TODO Automatisch generierter Erfassungsblock
		e.printStackTrace();
	  }
   }
}
