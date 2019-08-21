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
   private ChoiceBox<String> cbPersonen;
   @FXML
   private TextField tfName;
   @FXML
   public void initialize()
   {
	 cbPersonen.getItems().addAll(Person.getPersonen()); 
	 btPersonhinzufügen.setOnAction(new EventHandler<ActionEvent>() {
		@Override
	    public void handle(ActionEvent event)
	    { 
		   hinzufügenPerson(cbPersonen.getValue(), dbVerbindung.holenConnection());
	    }
   } );
   }
   void hinzufügenPerson(String pPerson, Connection connection)
   {
	  Statement lBefehl;
	  
	  try
	  {
		 lBefehl	= connection.createStatement();
		 lBefehl.executeUpdate("INSERT INTO person where IDPerson ='"+pPerson.substring(pPerson.length() - 1) +"';");
	  }
	  catch (SQLException e)
	  {
		 // TODO Automatisch generierter Erfassungsblock
		e.printStackTrace();
	  }
   }
}
