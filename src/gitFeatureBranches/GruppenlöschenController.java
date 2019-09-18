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

public class GruppenlöschenController
{
   private static DBVerbindung dbVerbindung = new DBVerbindung();
   @FXML
   private ChoiceBox<String> cbPersonen;
   
   @FXML
   private Button btPersonlöschen;
   
   @FXML
   public void initialize()
   {
	  
	 cbPersonen.getItems().addAll(Person.getPersonen()); 
	 btPersonlöschen.setOnAction(new EventHandler<ActionEvent>() {
		@Override
	    public void handle(ActionEvent event)
	    { 
		   löschenPerson(cbPersonen.getValue(), dbVerbindung.holenConnection());
	    }
   } );
   }
   public void löschenPerson(String pPerson, Connection connection)
   {
	  Statement lBefehl;
	  Statement lBefehl2;
	  try
	  {
		 lBefehl 	= connection.createStatement();
		 lBefehl.executeQuery("SET SQL_SAFE_UPDATES = 0;");
		 lBefehl2	= connection.createStatement();
		 lBefehl2.executeUpdate("DELETE FROM person where IDPerson ='"+pPerson.substring(pPerson.length() - 1) +"';");
	  }
	  catch (SQLException e)
	  {
		 // TODO Automatisch generierter Erfassungsblock
		e.printStackTrace();
	  }
	 
   }