package gitFeatureBranches;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class PersonenloeschenController
{
   private static DBVerbindung dbVerbindung = new DBVerbindung();
   @FXML
   private ChoiceBox<Person> cbPersonen;
   
   @FXML
   private Button btPersonloeschen;
   
   @FXML
   public void initialize()
   {
	 cbPersonen.getItems().addAll(FensterController.getPersonenAL()); 
	 btPersonloeschen.setOnAction(new EventHandler<ActionEvent>() {
		@Override
	    public void handle(ActionEvent event)
	    { 
		   loeschenPerson(cbPersonen.getValue(), dbVerbindung.holenConnection());
	    }
   } );
   }
   public void loeschenPerson(Person pPerson, Connection connection)
   {
	  Statement lBefehl;
	  Statement lBefehl2;
	  try
	  {
		 lBefehl 	= connection.createStatement();
		 lBefehl.executeQuery("SET SQL_SAFE_UPDATES = 0;");
		 lBefehl2	= connection.createStatement();
		 lBefehl2.executeUpdate("DELETE FROM personen where IDPerson ='"+pPerson.getID() +"';");
	  }
	  catch (SQLException e)
	  {
		 // TODO Automatisch generierter Erfassungsblock
		e.printStackTrace();
	  }
	 
   }
}

