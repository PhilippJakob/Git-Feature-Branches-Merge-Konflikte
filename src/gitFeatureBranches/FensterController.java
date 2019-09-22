package gitFeatureBranches;

import java.io.IOException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
   import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.AgendaSkinSwitcher;
import jfxtras.scene.layout.VBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
   public class FensterController {
	  static Termin Termin = new Termin();



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
       private Menu mTermin;
       @FXML
       private MenuItem mTerminErstellen;
       @FXML
       private MenuItem mZusatzinfos;
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
       Stage bühne = new Stage(); 
       @FXML
       private ChoiceBox<String> cbOE;
       @FXML
       private BorderPane bpAgenda;
       private Stage 		bühnePersonenlöschen = new Stage();	
       private Stage 		bühnePersonenhinzufügen = new Stage();	
       private AnchorPane 	grundPane3;
       private AnchorPane 	grundPane2;
       private AnchorPane 	grundPane;
       //Handelt Untermenüs und füllt bei Start AL und CB
  
       @FXML
       public void initialize()
       {
    	  if (dbVerbindung.verbinden("127.0.0.1", "dbpr_termin", "root", "localhost")== false)
    	  {
    		 System.out.println("1");
    		return;
    	  }
    	  auslesenTermine();
    	  mZusatzinfos.setOnAction(new EventHandler<ActionEvent>(){
 		    @Override
 		    public void handle(ActionEvent event)
 		    { 
 		       Stage bühne = new Stage();	
 		       FXMLLoader lLoader = new FXMLLoader();
    		       try
 			   {
    		    	  	   bühne.setTitle("Zusatzinformationen");
 		    	  	   lLoader.setLocation(getClass().getResource("Zusatzinfos.fxml"));
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
        	mTerminErstellen.setOnAction(new EventHandler<ActionEvent>(){
        	 @Override
 		    public void handle(ActionEvent event)
 		    { 
 		       
 		       FXMLLoader lLoader = new FXMLLoader();
 		       try
 			   {
 		    	       bühne.setTitle("Termin erstellen");
 		    	  	   lLoader.setLocation(getClass().getResource("TerminView.fxml"));
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
        	 
        	}
        	);;

    	  bühne.setOnCloseRequest(event -> {Termin();});
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
		    	  	   grundPane3 = lLoader.load();
		    	  	   Scene lScene = new Scene(grundPane3);
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
                   .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1")) // you should use a map of AppointmentGroups
           ;
       agKalender.appointments().addAll(lAppointment);

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
		    	  	   grundPane3 = lLoader.load();
		    	  	   Scene lScene = new Scene(grundPane3);
		    	  	
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
 		    	  	   grundPane3 = lLoader.load();
 		    	  	   Scene lScene = new Scene(grundPane3);
 		    	  	
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

		
	  @FXML
	  public void Termin()
	  {
		 Termin termin = getTermin();
		 LocalDateTime lTagVon = LocalDateTime.of(termin.getTerminDatumVon(), termin.getTerminZeit());
		 LocalDateTime lTagBis = LocalDateTime.of(termin.getTerminDatumBis(), termin.getTerminZeitBis());
		 String lBeschreibung = termin.getTerminInfo();
		 String lRaum = Integer.toString(termin.getTerminRaum());
		  
		 if(lTagVon==null||lBeschreibung==null)
		 {
			bühne.close();
		 }
		 else
		 {
		 agKalender.appointments().addAll(
	               new Agenda.AppointmentImplLocal()
	                   .withStartLocalDateTime(lTagVon)
	                   .withEndLocalDateTime(lTagBis)
	                   .withDescription(lBeschreibung)
	                   .withLocation(lRaum)
	                   .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1")));
		 	termin.übergebenInDB(DBVerbindung.holenConnection());
		 }
}
	   @FXML
       public void auslesenTermine()
       {
        
        
//        KalenderController lTermin = new KalenderController();
        ArrayList<Termin> lTerminListe = Termin.auslesenTermine(DBVerbindung.holenConnection(), 1);
        
        for(int i = 0; i<lTerminListe.size();i++)
        {
           Termin lTermin = lTerminListe.get(i);
           System.out.println(lTerminListe.get(i).getTerminDatumVon());
         int lSYear = lTermin.getTerminDatumVon().getYear();
         int lSMonth =lTermin.getTerminDatumVon().getMonthValue();
         int lSDay = lTermin.getTerminDatumVon().getDayOfMonth();
         int lSHour = lTermin.getTerminZeit().getHour();
         int lSMinute = lTermin.getTerminZeit().getMinute();
         
         int lEYear = lTermin.getTerminDatumBis().getYear();
         int lEMonth = lTermin.getTerminDatumBis().getMonthValue();
         int lEDay = lTermin.getTerminDatumBis().getDayOfMonth();
         int lEHour = lTermin.getTerminZeitBis().getHour();
         int lEMinute = lTermin.getTerminZeitBis().getMinute();
         String lTerminInfo = lTerminListe.get(i).getTerminInfo();
        agKalender.appointments().addAll(
                   new Agenda.AppointmentImplLocal()
                       .withStartLocalDateTime(LocalDateTime.of(lSYear, lSMonth, lSDay, lSHour, lSMinute))
                       .withEndLocalDateTime(LocalDateTime.of(lEYear, lEMonth,lEDay, lEHour, lEMinute))
                       .withDescription(lTerminInfo)
                       .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1")) // you should use a map of AppointmentGroups
                   
        );
        }
       }

	  public Agenda getAgKalender()
	  {
	     return agKalender;
	  }


	  public void setAgKalender(Agenda agKalender)
	  {
	     this.agKalender = agKalender;
	  }


	  public Stage getBühne()
	  {
	     return bühne;
	  }


	  public void setBühne(Stage bühne)
	  {
	     this.bühne = bühne;
	  }
	  public Termin getTermin()
	  {
	     return Termin;
	  }
	  public static void setTermin(Termin termin)
	  {
	     Termin = termin;
	  }

       
     
    	  
}
   


