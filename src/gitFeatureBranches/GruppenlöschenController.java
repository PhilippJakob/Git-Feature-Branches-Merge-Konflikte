package gitFeatureBranches;

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
   private ChoiceBox<String> cbGruppe;
   
   @FXML
   private Button btGruppelöschen;
   
   @FXML
   public void initialize()
   {
	  
	 cbGruppe.getItems().addAll(Gruppe.getGruppe());
	 btGruppelöschen.setOnAction(new EventHandler<ActionEvent>() {
		@Override
	    public void handle(ActionEvent event)
	    { 
		   löschenGruppe(cbGruppe.getValue(), DBVerbindung.holenConnection());
	    }
   } );
   }
   public void löschenGruppe(String pGruppe, Connection connection)
   {
	  Statement lBefehl;
	  Statement lBefehl2;
	  try
	  {
		 lBefehl 	= connection.createStatement();
		 lBefehl.executeQuery("SET SQL_SAFE_UPDATES = 0;");
		 lBefehl2	= connection.createStatement();
		 lBefehl2.executeUpdate("DELETE FROM gruppe where IDGruppe ='"+pGruppe.substring(pGruppe.length() - 1) +"';");
	  }
	  catch (SQLException e)
	  {
		 // TODO Automatisch generierter Erfassungsblock
		e.printStackTrace();
	  }
	 
   }
   public static DBVerbindung getDbVerbindung()
   {
	  return dbVerbindung;
   }
   public static void setDbVerbindung(DBVerbindung dbVerbindung)
   {
	  GruppenlöschenController.dbVerbindung = dbVerbindung;
   }
}

