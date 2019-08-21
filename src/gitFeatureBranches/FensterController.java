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
	  private MenuItem mPersonenloeschen;
       @FXML
       private ChoiceBox<String> Personauswahl;

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
    	  setPersonenAL(auslesenDB(DBVerbindung.holenConnection()));
    	  ArrayList<Person> lPersonenAL = new ArrayList<Person>();
    	  lPersonenAL = getPersonenAL();
    	  for(int i = 0; i<lPersonenAL.size();i++)
    	  {
    		 String lName = new String();
    		 lName = lPersonenAL.get(i).getName();
    		 String lPersonenID = "";
    		 lPersonenID = Integer.toString(lPersonenAL.get(i).getID());
    		 lName = lName.concat(" " +lPersonenID);
    		 Personauswahl.getItems().add(lName);
    	  }
    	  mPersonenloeschen.setText("Person löschen");
    	  mPersonenloeschen.setOnAction(new EventHandler<ActionEvent>() {
    		 
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
    	  Personauswahl.setTooltip(new Tooltip("Wähle die Person aus"));
       }

       public static ArrayList<Person> auslesenDB(Connection pConnection)
	    {
	      Person lPerson;
	      ArrayList<Person> lPersonen = new ArrayList<Person>();
	      Statement lBefehl;
	      ResultSet lErgebnis;

	      try {
	      lBefehl 	= pConnection.createStatement();
	      lErgebnis = lBefehl.executeQuery("SELECT Name,IDPerson FROM person p;");
	      lErgebnis.first(); 

	      while(! lErgebnis.isAfterLast())   
	         {
	          lPerson = new Person(lErgebnis.getString(1),lErgebnis.getInt(2));
	           lPersonen.add(lPerson);
	           lErgebnis.next();
	         }
	         } catch (Exception ex)
	              {
	                System.out.println("Fehler bei der Verarbeitung + " + "n" + ex.getMessage());
	              }
	      return lPersonen;
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


