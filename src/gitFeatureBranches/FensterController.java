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
import javafx.stage.Stage;
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
       
     

       private AnchorPane 	grundPane;
       @FXML
       public void initialize()
       {
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
    		    	  	   lLoader.setLocation(getClass().getResource("PersonenhinzufuegenView.fxml"));
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

      public  void aktualisieren()
      {
    	 cbPersonauswahl.getItems().clear();
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


