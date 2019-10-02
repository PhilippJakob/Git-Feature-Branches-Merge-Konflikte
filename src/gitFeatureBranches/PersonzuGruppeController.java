package gitFeatureBranches;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class PersonzuGruppeController
{
   private DBVerbindung dbVerbindung = new DBVerbindung();
   @FXML
   private ChoiceBox<String> cbPerson;
   @FXML
   private ChoiceBox<String> cbGruppe;
   @FXML
   private Button btHinzufügen;
   @FXML
   private Button btLöschen;
   @FXML
   private void initialize()
   {
	  cbPerson.getItems().addAll(Person.getPersonen());
	  cbGruppe.getItems().addAll(Gruppe.getGruppen());
	  btHinzufügen.setOnAction(new EventHandler<ActionEvent>() {
		 @Override
		 public void handle(ActionEvent event)
		 {
			hinzufügen(cbPerson.getValue(),cbGruppe.getValue());
		 }
	  }); 
	  btLöschen.setOnAction(new EventHandler<ActionEvent>() {
		 @Override
		 public void handle(ActionEvent event)
		 {
			löschen(cbPerson.getValue(),cbGruppe.getValue());
		 }
	  });
   }
   private void hinzufügen(String pPerson, String pGruppe)
   {
	  Connection connection = dbVerbindung.holenConnection();
	  Statement lBefehl;
	  String[] tokens;
	  String[] tokens2;
	  tokens = pPerson.split(" ");
	  tokens2 = pGruppe.split(" ");
	  try
	  {
		 lBefehl = connection.createStatement();
		 lBefehl.executeUpdate("INSERT INTO gruppenzugehörigkeit(IDGruppe,IDPerson) VALUES('"+tokens2[tokens2.length-1]+"','"+tokens[tokens.length-1]+"');");
	  }
	  catch (SQLException e)
	  {
		 // TODO Automatisch generierter Erfassungsblock
		 e.printStackTrace();
	  }
   }
   private void löschen(String pPerson, String pGruppe)
   {
	  Connection connection = dbVerbindung.holenConnection();
	  Statement lBefehl;
	  Statement lBefehl2;
	  String[] tokens;
	  tokens = pPerson.split(" ");
	  String[] tokens2;
	  tokens2 = pGruppe.split(" ");
	  try
	  {
		 lBefehl = connection.createStatement();
		 lBefehl.executeQuery("SET SQL_SAFE_UPDATES = 0;");
		 lBefehl2 = connection.createStatement();
		 lBefehl2.executeUpdate("DELETE FROM gruppenzugehörigkeit where IDPerson ='" + tokens[tokens.length - 1] + "' AND IDGruppe = '"+tokens2[tokens2.length-1]+"';");
	  }
	  catch (SQLException e)
	  {
		 // TODO Automatisch generierter Erfassungsblock
		 e.printStackTrace();
	  }
   }
}
