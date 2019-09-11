package gitFeatureBranches;





import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
   import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.Agenda.AppointmentGroup;
import jfxtras.scene.control.agenda.Agenda.AppointmentGroupImpl;
import jfxtras.scene.control.agenda.AgendaSkinSwitcher;
import jfxtras.scene.control.gauge.linear.AbstractLinearGauge;
import jfxtras.scene.layout.VBox;
import javafx.scene.control.MenuBar;

import javafx.scene.control.MenuItem;
   public class FensterController {
	  
	  private static DBVerbindung dbVerbindung = new DBVerbindung();
	  private static ArrayList<Person> personenAL = new ArrayList<Person>();
	  private static ArrayList<Gruppe> gruppenAL = new ArrayList<Gruppe>();
	  private static ArrayList<Organisationseinheit> organisationseinheitAL = new ArrayList<Organisationseinheit>();
	   @FXML
	   private Menu mEmail;
	   @FXML
	   private MenuItem mEmailOB;
	  @FXML
	  private Menu mPersonen;
	  @FXML
	  private MenuItem mPersonenlöschen;
	  @FXML 
	  private MenuItem mPersonhinzufügen;
       @FXML
       public ChoiceBox<String> cbPersonauswahl;
       @FXML
       private Agenda agKalender;
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
       private ChoiceBox<String> cbGruppen;
       @FXML
       private Label Lrang3;
       @FXML
       private VBox vbAgenda;
       @FXML
       private ChoiceBox<String> cbOE;
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
    	  constructAppointmentGroups();

    	 AgendaSkinSwitcher skin = new AgendaSkinSwitcher(agKalender);
    	  vbAgenda.getChildren().clear();
    	  vbAgenda.getChildren().addAll(skin,agKalender);
    	  agKalender.setEditAppointmentCallback( (appointment) -> {
  			PopupController.setAppointment(appointment);
  		       PopupController.setAgkalender(agKalender);
  		     try
			   {
		    	  Stage bühne = new Stage();	
			       FXMLLoader lLoader = new FXMLLoader();
		    	  	   lLoader.setLocation(getClass().getResource("popupnew1.fxml"));
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
  	        return null;
  	    });
    	  
    	  Agenda.AppointmentImplLocal lAppointment = new Agenda.AppointmentImplLocal()
                   .withStartLocalDateTime(LocalDate.now().atTime(4, 00))
                   .withEndLocalDateTime(LocalDate.now().atTime(15, 30))
                   .withDescription("It's time")
                   .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group5")) // you should use a map of AppointmentGroups
           ;
       agKalender.appointments().addAll(lAppointment);
    	  if (dbVerbindung.verbinden("dbserver", "dbpr_termin", "dblkuser", "lkbenutzer")== false)
    	  {
    		return;
    	  }
    	  setGruppenAL(Gruppe.auslesenDB(dbVerbindung.holenConnection()));
    	  setOrganisationseinheitAL(Organisationseinheit.auslesenDB(dbVerbindung.holenConnection()));
    	  setPersonenAL(Person.auslesenDB(DBVerbindung.holenConnection()));
    	  cbPersonauswahl.getItems().addAll(Person.getPersonen());
    	  cbOE.getItems().addAll(Organisationseinheit.getOrganisationseinheiten());
    	  cbGruppen.getItems().addAll(Gruppe.getGruppen());
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
    	  mEmailOB.setOnAction(new EventHandler<ActionEvent>() {
    		 
 		    @Override
 		    public void handle(ActionEvent event)
 		    { 
 		     
 		       FXMLLoader lLoader = new FXMLLoader();
     		       try
 			   {
 		    	  	   lLoader.setLocation(getClass().getResource("Email.fxml"));
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
    	  cbPersonauswahl.setOnAction(new EventHandler<ActionEvent>(){
 		    @Override
 		    public void handle(ActionEvent event)
 		    { 
 		       
 		      filtern();
 		    }
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
	  public ObservableList<AppointmentGroup> appointmentGroups() { return appointmentGroups; }
		final private ObservableList<AppointmentGroup> appointmentGroups =  javafx.collections.FXCollections.observableArrayList();
		private void constructAppointmentGroups() {
			// setup appointment groups as predefined in the CSS
	        final Map<String, Agenda.AppointmentGroup> lAppointmentGroupMap = new TreeMap<String, Agenda.AppointmentGroup>();
	        for (int i = 0; i < 24; i++) {
	        	lAppointmentGroupMap.put("group" + (i < 10 ? "0" : "") + i, new Agenda.AppointmentGroupImpl().withStyleClass("group" + i));
	        }
	        for (String lId : lAppointmentGroupMap.keySet())
	        {
	            Agenda.AppointmentGroup lAppointmentGroup = lAppointmentGroupMap.get(lId);
	            lAppointmentGroup.setDescription(lId);
	            appointmentGroups().add(lAppointmentGroup);
	        }
	}
	  public void filtern()
	  {
		 cbGruppen.getItems().clear();
		 setGruppenAL(Gruppenzugehörigkeit.auslesenDB(dbVerbindung.holenConnection(),cbPersonauswahl.getValue()));
		 cbGruppen.getItems().addAll(Gruppe.getGruppen());
	  }


	  public void setPersonenAL(ArrayList<Person> personenAL)
	  {
	     this.personenAL = personenAL;
	  }
	  public static ArrayList<Organisationseinheit> getOrganisationseinheitAL()
	  {
	     return organisationseinheitAL;
	  }
	  public static void setOrganisationseinheitAL(ArrayList<Organisationseinheit> organisationseinheitAL)
	  {
	     FensterController.organisationseinheitAL = organisationseinheitAL;
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


