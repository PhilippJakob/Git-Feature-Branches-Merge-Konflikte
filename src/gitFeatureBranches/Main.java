package gitFeatureBranches;
	
import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class Main extends Application {
   
   private Stage 		bühne;
   private HBox 	grundPane;

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

//<?import jfxtras.scene.layout.*?>
//<?import javafx.scene.text.*?>
//<?import javafx.scene.image.*?>
//<?import javafx.scene.control.*?>
//<?import java.lang.*?>
//<?import javafx.scene.layout.*?>
//<?import javafx.scene.layout.AnchorPane?>
//<?import javafx.scene.control.*?>
//<?import javafx.scene.layout.*?>
//<?import java.lang.*?>
//<?import java.util.*?>
//<?import jfxtras.scene.control.agenda.*?>