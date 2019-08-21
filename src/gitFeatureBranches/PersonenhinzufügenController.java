package gitFeatureBranches;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class PersonenhinzufügenController
{
   private static DBVerbindung dbVerbindung = new DBVerbindung();



   @FXML
   private Button btPersonhinzufügen;

   @FXML
   private TextField tfName;

   @FXML
   public void initialize()
   {

	 btPersonhinzufügen.setOnAction(new EventHandler<ActionEvent>() {
		@Override
	    public void handle(ActionEvent event)
	    { 
		   hinzufügenPerson(tfName.getText(), dbVerbindung.holenConnection());
	    }
   } );
   }
   void hinzufügenPerson(String pPerson, Connection connection)
   {
	  Statement lBefehl;
	  String ID = new String();
	  ID = Person.getLetztePerson();
	  ID = ID.substring(ID.length() - 1);
	  ID = Integer.toString(Integer.parseInt(ID)+1);
	  try
	  {
		 lBefehl	= connection.createStatement();
		 lBefehl.executeUpdate("INSERT INTO person(IDPerson,Name,StID) VALUES('"+ID+"','"+pPerson+"',NULL);");
	  }
	  catch (SQLException e)
	  {
		 // TODO Automatisch generierter Erfassungsblock
		e.printStackTrace();
	  }
   }
}
