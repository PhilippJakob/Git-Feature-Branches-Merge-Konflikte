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

public class GruppenhinzufügenController
{
   private static DBVerbindung dbVerbindung = new DBVerbindung();
   @FXML
   private Button btGruppenhinzufügen;
   @FXML
   private ChoiceBox<String> cbGruppe;
   @FXML
   private TextField tfName;
   @FXML
   public void initialize()
   {
	 cbGruppe.getItems().addAll(Gruppe.getGruppe()); 
	 btGruppenhinzufügen.setOnAction(new EventHandler<ActionEvent>() {
		@Override
	    public void handle(ActionEvent event)
	    { 
		   hinzufügenGruppe(cbGruppe.getValue(), dbVerbindung.holenConnection());
	    }
   } );
   }
   void hinzufügenGruppe(String pGruppe, Connection connection)
   {
	  Statement lBefehl;
	  
	  try
	  {
		 lBefehl	= connection.createStatement();
		 lBefehl.executeUpdate("INSERT INTO person where IDPerson ='"+pGruppe.substring(pGruppe.length() - 1) +"';");
	  }
	  catch (SQLException e)
	  {
		 // TODO Automatisch generierter Erfassungsblock
		e.printStackTrace();
	  }
   }
}