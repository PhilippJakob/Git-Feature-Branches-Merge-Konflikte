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

public class OEController
{
   private static DBVerbindung dbVerbindung = new DBVerbindung();

   private int hinzugefügteOE =1 ;

   @FXML
   private Button btZuweisen;

   @FXML
   private TextField tfName;
   //Füllt CB und Handelt Knopfdruck
   @FXML
   public void initialize()
   {

	 
   }
   //Holt sich höchste ID und Fügt Person mit höchsterID+1 hinzu.
   void hinzufügenOrganisationseinheiten(String pOrganisationseinheit, Connection connection)
   {
	  Statement lBefehl;
	  String ID = new String();
	  String[] tokens;
	  
	  ID = Organisationseinheit.getLetzteOrganisationseinheit();
	  tokens = ID.split(" ");
	  ID = tokens[tokens.length-1];
	  ID = Integer.toString(Integer.parseInt(ID)+hinzugefügteOE);
	  hinzugefügteOE++;
	  try
	  {
		 lBefehl= connection.createStatement();
		 lBefehl.executeUpdate("INSERT INTO organisationseinheit(OEID,OENAME) VALUES('"+ID+"','"+pOrganisationseinheit+"',NULL);");
	  }
	  catch (SQLException e)
	  {
		 // TODO Automatisch generierter Erfassungsblock
		e.printStackTrace();
	  }
   }
   public void zuweisen()
   {
   btZuweisen.setOnAction(new EventHandler<ActionEvent>()
			{
	 
		@Override
	    public void handle(ActionEvent event)
	    { 
		   hinzufügenOrganisationseinheiten(tfName.getText(), dbVerbindung.holenConnection());
	    }
   } );
   }
}
