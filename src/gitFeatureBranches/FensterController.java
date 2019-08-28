package gitFeatureBranches;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
   public class FensterController {
	  private static DBVerbindung dbVerbindung = new DBVerbindung();
	  private static ArrayList<Gruppe> gruppenAL = new ArrayList<Gruppe>();
	  @FXML
	  private Menu mGruppe;
      @FXML
      private MenuBar mbBar;
	  @FXML
	  private MenuItem mGruppelöschen;
	  @FXML 
	  private MenuItem mGruppehinzufügen;
       @FXML
       private ChoiceBox<String> cbGruppenauswahl;
       @FXML
       private AnchorPane 	grundPane;
       @FXML
       public void initialize()
       {
    	  if (dbVerbindung.verbinden("dbserver", "dbpr_termin", "dblkuser", "lkbenutzer")== false)
    	  {
    		return;
    	  }
    	  setGruppenAL(Gruppe.auslesenDB(DBVerbindung.holenConnection()));
    	  cbGruppenauswahl.getItems().addAll(Gruppe.getGruppe());
    	  mGruppelöschen.setText("Gruppe löschen");
    	  mGruppelöschen.setOnAction(new EventHandler<ActionEvent>() {
    		 
		    @Override
		    public void handle(ActionEvent event)
		    { 
		       Stage bühne = new Stage();	
		       FXMLLoader lLoader = new FXMLLoader();
    		       try
			   {
		    	  	   lLoader.setLocation(getClass().getResource("GruppeloeschenView.fxml"));
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
    	  mGruppehinzufügen.setOnAction(new EventHandler<ActionEvent>(){
    		    @Override
    		    public void handle(ActionEvent event)
    		    { 
    		       Stage bühne = new Stage();	
    		       FXMLLoader lLoader = new FXMLLoader();
        		       try
    			   {
    		    	  	   lLoader.setLocation(getClass().getResource("GruppehinzufügenView.fxml"));
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
    	 
    	  cbGruppenauswahl.setTooltip(new Tooltip("Wähle die Gruppe aus"));
       }
       
	  public static ArrayList<Gruppe> getGruppenAL()
	  {
	     return gruppenAL;
	  }
	  public static void setGruppenAL(ArrayList<Gruppe> gruppenAL)
	  {
	     FensterController.gruppenAL = gruppenAL;
	  }





   }