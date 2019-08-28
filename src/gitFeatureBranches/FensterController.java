package gitFeatureBranches;





import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
   import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.Separator;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.AgendaSkinSwitcher;
import jfxtras.scene.layout.VBox;
import javafx.scene.control.MenuBar;

import javafx.scene.control.MenuItem;
   public class FensterController {
	  

	  private static DBVerbindung dbVerbindung = new DBVerbindung();
	  private static ArrayList<Person> personenAL = new ArrayList<Person>();
	  @FXML
	  private Menu mPersonen;
	  @FXML
	  private MenuItem mPersonenlöschen;
	  @FXML 
	  private MenuItem mPersonhinzufügen;
       @FXML
       public ChoiceBox<String> cbPersonauswahl;
       @FXML
       private Agenda agKalendar;
       @FXML
       private Label Lrang4;

       @FXML
       private Label Lrang5;

       @FXML
       private Label Lrang1;

       @FXML
       private MenuBar mbBar;
       
       @FXML
       private Label Lrang2;

       @FXML
       private Label Lrang3;
       @FXML
       private VBox vbAgenda;
       @FXML
       private BorderPane bpAgenda;
       private Stage 		bühnePersonenlöschen = new Stage();	
       private Stage 		bühnePersonenhinzufügen = new Stage();	
       private AnchorPane 	grundPane;
       private AnchorPane 	grundPane2;
       //Handelt Untermenüs und füllt bei Start AL und CB
       @FXML
       public void initialize()
       {
    	 AgendaSkinSwitcher skin = new AgendaSkinSwitcher(agKalendar);
    	  vbAgenda.getChildren().clear();
    	  vbAgenda.getChildren().addAll(skin,agKalendar);
    	  if (dbVerbindung.verbinden("dbserver", "dbpr_termin", "dblkuser", "lkbenutzer")== false)
    	  {
    		return;
    	  }
    	  setPersonenAL(Person.auslesenDB(DBVerbindung.holenConnection()));
    	  cbPersonauswahl.getItems().addAll(Person.getPersonen());
    	  mPersonenlöschen.setText("Person löschen");
    	  mPersonenlöschen.setOnAction(new EventHandler<ActionEvent>() {
    		 
		    @Override
		    public void handle(ActionEvent event)
		    { 
		     
		       FXMLLoader lLoader = new FXMLLoader();
    		       try
			   {
		    	  	   lLoader.setLocation(getClass().getResource("PersonenloeschenView.fxml"));
		    	  	   grundPane = lLoader.load();
		    	  	   Scene lScene = new Scene(grundPane);
				       bühnePersonenlöschen.setScene(lScene);
				       bühnePersonenlöschen.show();     
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
    		      
    		       FXMLLoader lLoader = new FXMLLoader();
        		       try
    			   {
    		    	  	   lLoader.setLocation(getClass().getResource("PersonenhinzufuegenView.fxml"));
    		    	  	   grundPane2 = lLoader.load();
    		    	  	   Scene lScene = new Scene(grundPane2);
    				       bühnePersonenhinzufügen.setScene(lScene);
    				       bühnePersonenhinzufügen.show();     
    			   }
    			   catch (IOException e)
    			   {
    				  // TODO Automatisch generierter Erfassungsblock
    				  e.printStackTrace();
    			   }
    		    }
    		 });
    	  bühnePersonenhinzufügen.setOnCloseRequest(event->{
    		 aktualisieren();
    	  });
    	  bühnePersonenlöschen.setOnCloseRequest(event->{
    		 aktualisieren();
    	  });
    	  cbPersonauswahl.setTooltip(new Tooltip("Wähle die Person aus"));
       }
      //Aktualisiert Choiceboxen
      public void aktualisieren()
      {
    	 cbPersonauswahl.getItems().clear();
    	 setPersonenAL(Person.auslesenDB(DBVerbindung.holenConnection()));
    	 cbPersonauswahl.getItems().addAll(Person.getPersonen());
      }

	  public static ArrayList<Person> getPersonenAL()
	  {
	     return personenAL;
	  }



	  public void setPersonenAL(ArrayList<Person> personenAL)
	  {
	     this.personenAL = personenAL;
	  }
       

   }


