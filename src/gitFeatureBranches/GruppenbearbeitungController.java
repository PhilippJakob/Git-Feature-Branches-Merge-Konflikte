package gitFeatureBranches;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

   public class GruppenbearbeitungController {

       @FXML
       private Button btErstellen;
       
       @FXML
       private TextField tfGruppe;

       @FXML
       private Button btLöschen;

       @FXML
       private ChoiceBox<?> cbGruppe;
      
 	  private static DBVerbindung dbVerbindung = new DBVerbindung();
 	  private static ArrayList<Gruppe> gruppeAL = new ArrayList<Gruppe>();

      private AnchorPane 	grundPane;
      @FXML
      public void initialize()
      {
   	  if (dbVerbindung.verbinden("dbserver", "dbpr_termin", "dblkuser", "lkbenutzer")== false)
   	  {
   		return;
   	  }
   	  setPersonenAL(dbVerbindung.auslesenDB(DBVerbindung.holenConnection()));
   	  cbPersonauswahl.getItems().addAll(Person.getPersonen());
   	  mPersonenlöschen.setText("Person löschen");
   	  mPersonenlöschen.setOnAction(new EventHandler<ActionEvent>() {
   		 
		    @Override
		    public void handle(ActionEvent event)
		    { 
		       Stage bühne = new Stage();	
		       FXMLLoader lLoader = new FXMLLoader();
   		       try
			   {
		    	  	   lLoader.setLocation(getClass().getResource("PersonenloeschenView.fxml"));
		    	  	   grundPane = lLoader.load();
		    	  	   Scene lScene = new Scene(grundPane);
				       bühne.setScene(lScene);
				       bühne.show();     
			   }
			   catch (IOException e)
			   {
				  // TODO Automatisch generierter Erfassungsblock
				  e.printStackTrace();
			   }
		    }
		 });
   	  mPersonhinzufügen.setOnAction(new EventHandler<ActionEvent>(){
   		    @Override
   		    public void handle(ActionEvent event)
   		    { 
   		       Stage bühne = new Stage();	
   		       FXMLLoader lLoader = new FXMLLoader();
       		       try
   			   {
   		    	  	   lLoader.setLocation(getClass().getResource("PersonenhinzufügenView.fxml"));
   		    	  	   grundPane = lLoader.load();
   		    	  	   Scene lScene = new Scene(grundPane);
   				       bühne.setScene(lScene);
   				       bühne.show();     
   			   }
   			   catch (IOException e)
   			   {
   				  // TODO Automatisch generierter Erfassungsblock
   				  e.printStackTrace();
   			   }
   		    }
   		 });
   	 
   	  cbPersonauswahl.setTooltip(new Tooltip("Wähle die Person aus"));
      }

 

	  public static ArrayList<Gruppe> getGruppeAL()
	  {
	     return gruppeAL;
	  }

	  public static void setGruppeAL(ArrayList<Gruppe> gruppeAL)
	  {
	     GruppenbearbeitungController.gruppeAL = gruppeAL;
	  }

      


       
       

   }


