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
}

Main.fxml

<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.scene.text.*?>
<?import javafx.scene.image.*?>
<?import javafx.scene.control.*?>
<?import java.lang.*?>
<?import javafx.scene.layout.*?>
<?import javafx.scene.layout.AnchorPane?>

<AnchorPane prefHeight="586.0" prefWidth="910.0" xmlns="http://javafx.com/javafx/8" xmlns:fx="http://javafx.com/fxml/1" fx:controller="gitFeatureBranches.FensterController">
   <children>
      <ChoiceBox fx:id="cbPersonauswahl" layoutX="29.0" layoutY="47.0" prefWidth="150.0" AnchorPane.leftAnchor="15.0" AnchorPane.topAnchor="50.0" />
      <Label layoutX="21.0" layoutY="98.0" text="User Gruppen:">
         <font>
            <Font size="24.0" />
         </font>
      </Label>
      <Label layoutX="47.0" layoutY="26.0" text="Person Auswahl:" />
      <Label fx:id="Lrang1" layoutX="21.0" layoutY="143.0">
         <font>
            <Font size="20.0" />
         </font>
      </Label>
      <Label fx:id="Lrang2" layoutX="21.0" layoutY="173.0">
         <font>
            <Font size="20.0" />
         </font>
      </Label>
      <Label fx:id="Lrang3" layoutX="21.0" layoutY="203.0">
         <font>
            <Font size="20.0" />
         </font>
      </Label>
      <Label fx:id="Lrang4" layoutX="21.0" layoutY="233.0">
         <font>
            <Font size="20.0" />
         </font>
      </Label>
      <Label fx:id="Lrang5" layoutX="21.0" layoutY="263.0">
         <font>
            <Font size="20.0" />
         </font>
      </Label>
      <MenuBar fx:id="mbBar" layoutY="2.0" prefHeight="25.0" prefWidth="910.0">
         <menus>
            <Menu fx:id="mPersonen" mnemonicParsing="false" text="Personen">
              <items>
                  <MenuItem fx:id="mPersonhinzufügen" mnemonicParsing="false" text="Person hinzufügen" />
                <MenuItem fx:id="mPersonenlöschen" mnemonicParsing="false" text="Action 1" />
              </items>
            </Menu>
         </menus></MenuBar>
   </children>
</AnchorPane>


Main
package gitFeatureBranches;
	
import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
   
   private Stage 		bühne;
   private AnchorPane 	grundPane;

   @Override
   public void start(Stage pPrimaryStage) throws IOException
   {

       this.bühne = pPrimaryStage;
       FXMLLoader lLoader = new FXMLLoader();

       lLoader.setLocation(Main.class.getResource("Main.fxml"));
       grundPane = lLoader.load();

       Scene lScene = new Scene(grundPane);
       bühne.setScene(lScene);
       bühne.show();
   }
	
	public static void main(String[] args) {
	 
		launch(args);
		
		
	}
}