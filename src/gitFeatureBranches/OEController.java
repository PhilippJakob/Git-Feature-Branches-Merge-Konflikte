package gitFeatureBranches;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.TODO;

import gitFeatureBranches.DBVerbindung;
import gitFeatureBranches.Organisationseinheit;
import gitFeatureBranches.Stelle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import jfxtras.scene.control.agenda.AgendaSkinSwitcher;

public class OEController
{
   private static DBVerbindung dbVerbindung = new DBVerbindung();

   private int hinzugefügteOE =1 ;

   @FXML
   private Button btLöschen;
   
   @FXML
   private Button btZuweisen;

   @FXML
   private TextField tfName;
   
   @FXML
   private ChoiceBox<String> cbUeber;
   
   @FXML
   private Button btUpdate;
   
   @FXML
   private ChoiceBox<String> cbStelle;
   //Füllt CB und Handelt Knopfdruck
   @FXML
   public void initialize()
   {
	 
	  cbUeber.getItems().addAll(Organisationseinheit.getOrganisationseinheiten());
	  cbStelle.getItems().addAll(Stelle.getStellen());
	  btLöschen.setOnAction(new EventHandler<ActionEvent>() 
	  {
		 @Override
		 public void handle(ActionEvent event)
		 {
			löschenOE(cbUeber.getValue(), DBVerbindung.holenConnection());
			cbUeber.getItems().remove(cbUeber.getValue());

		 }
	  });
	  
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
		 lBefehl.executeQuery("SET SQL_SAFE_UPDATES = 0;");
		 lBefehl.executeUpdate("INSERT INTO organisationseinheit(OEID,OENAME,OEÜBER) VALUES('"+ID+"','"+pOrganisationseinheit+"','"+ getcbÜberID()+"');");
		 System.out.println("Test323s");
	  }
	  catch (SQLException e)
	  {
		 // TODO Automatisch generierter Erfassungsblock
		e.printStackTrace();
	  }
   }
   public void löschenOE(String pOrganisationseinheit, Connection connection)
   {
	  Statement lBefehl;
	  Statement lBefehl2;
	  String[] tokens;
	  tokens = pOrganisationseinheit.split(" ");
	  try
	  {
		 lBefehl = connection.createStatement();
		 lBefehl.executeQuery("SET SQL_SAFE_UPDATES = 0;");
		 lBefehl2 = connection.createStatement();
		 lBefehl2.executeUpdate("DELETE FROM organisationseinheit where OEID ='" + tokens[tokens.length - 1] + "';");
	  }
	  catch (SQLException e)
	  {
		 // TODO Automatisch generierter Erfassungsblock
		 e.printStackTrace();
	  }

   }
   public int getcbÜberID()
   {
	  
	  String ID;
	  String[] tokens;
	   String lcbtext = cbUeber.getValue();
	   System.out.println(lcbtext);
	  tokens = lcbtext.split(" ");
	  ID = tokens[1];
	  
	  Integer ID2 = Integer.valueOf(ID);
	 
	  
	  return ID2;
	 
   }
   public int getcbStelleID()
   {
	  String ID;
	  String[] tokens;
	  String lcbtext = cbStelle.getValue();
	  System.out.println(lcbtext);
	  tokens = lcbtext.split(" ");
	  ID = tokens[1];
	  
	  Integer ID3 = Integer.valueOf(ID);
	  
	  return ID3;
   }
   //public void updateCB(String pOrganisationseinheit, Connection connection)
   //{
	//  Statement lBefehl;
	//  Statement lBefehl2;
	//  String[] tokens;
	//  String ID = new String();
	  
	 // ID = Organisationseinheit.getLetzteOrganisationseinheit();
	 // tokens = ID.split(" ");
	 // ID = tokens[tokens.length-1];
	 // ID = Integer.toString(Integer.parseInt(ID)+hinzugefügteOE);
	 // hinzugefügteOE++;
	 // System.out.println("GG");
	 // try
	 // {
		// lBefehl = connection.createStatement();
		// lBefehl2 = connection.createStatement();
		// System.out.println("GG!");
		// lBefehl2.executeUpdate("UPDATE organisationseinheit o SET OEID = '"+ ID +"',OENAME = '"+pOrganisationseinheit+"';");
		 //System.out.println("IIIII");
	 // }
	  //catch (SQLException e)
	 // {
		 // TODO Automatisch generierter Erfassungsblock
		//e.printStackTrace();
	 // }
  // }
   
   @FXML
   public void zuweisen()
   { 
	  hinzufügenOrganisationseinheiten(tfName.getText(), dbVerbindung.holenConnection());
   }
   //public void updaten()
  // {
	//  updateCB(tfName.getText(),dbVerbindung.holenConnection());
   //}
}
