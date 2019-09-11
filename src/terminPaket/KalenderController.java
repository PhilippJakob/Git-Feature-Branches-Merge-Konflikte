package terminPaket;



   import java.io.ObjectInputStream.GetField;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import javafx.event.ActionEvent;
	import javafx.event.EventHandler;
	import javafx.fxml.FXML;
	import javafx.fxml.FXMLLoader;
	import javafx.scene.Scene;
	import javafx.scene.control.Button;
	import javafx.scene.layout.AnchorPane;
	import javafx.stage.Stage;
	import jfxtras.scene.control.agenda.Agenda;
	import jfxtras.scene.control.agenda.Agenda.Appointment;
	import jfxtras.scene.control.agenda.Agenda.AppointmentGroup;

import javax.sql.rowset.FilteredRowSet;

import com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException;
import com.sun.xml.internal.ws.util.Pool;

import javafx.fxml.FXML;
   import jfxtras.scene.control.agenda.Agenda;
   import jfxtras.scene.control.agenda.Agenda.Appointment;
   import jfxtras.scene.control.agenda.Agenda.AppointmentGroup;
import sun.security.action.GetBooleanAction;
   



   public class KalenderController 
   {
	   
	  Stage bühne = new Stage();	

	  private static Datenbankverbindung dbVerbindung = new Datenbankverbindung();

	  @FXML
	  private Button btZusatzinformationen;
	  
	  @FXML
	  private Button btTermin;
	

	  @FXML
	  private Button bterstellen;
	  
	  @FXML
	  private Agenda agKalender;
       
	  private AnchorPane 	grundPane;
	  
	  
	  @FXML  
       public void initialize()
       {

		
		 bühne.setOnCloseRequest(event -> {Termin();});
     
		 if(!dbVerbindung.verbinden())
		 {
			return;
		 }
       	
           
       	btZusatzinformationen.setOnAction(new EventHandler<ActionEvent>(){
		    @Override
		    public void handle(ActionEvent event)
		    { 
		       Stage bühne = new Stage();	
		       FXMLLoader lLoader = new FXMLLoader();
   		       try
			   {
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
       	btTermin.setOnAction(new EventHandler<ActionEvent>(){
       	 @Override
		    public void handle(ActionEvent event)
		    { 
		       
		       FXMLLoader lLoader = new FXMLLoader();
		       try
			   {
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

       	   // create Agenda
       	//agKalender = new Agenda();

           // add an appointment
       	agKalender.appointments().addAll(
               new Agenda.AppointmentImplLocal()
                   .withStartLocalDateTime(LocalDate.now().atTime(4, 00))
                   .withEndLocalDateTime(LocalDate.now().atTime(15, 30))
                   .withDescription("It's time")
                   .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1")) // you should use a map of AppointmentGroups
           );
       	Datenbankverbindung dbVerbindung = new Datenbankverbindung();
       	dbVerbindung.verbinden();
       	



   /*        // setup appointment groups
           final Map<String, Agenda.AppointmentGroup> lAppointmentGroupMap = new TreeMap<String, Agenda.AppointmentGroup>();
           Agenda lAgenda;
   		for (Agenda.AppointmentGroup lAppointmentGroup : lAgenda.appointmentGroups()) {
             lAppointmentGroupMap.put(lAppointmentGroup.getDescription(), lAppointmentGroup);
           } */
       }


	
	  @FXML
	  public void Termin()
	  {
		 
		 Termin termin = new Termin();
		 LocalTime lStartpunkt = termin.getTerminZeit();
		 LocalTime lEndpunkt = termin.getTerminZeitBis();
		 LocalDate lTag = termin.getTerminDatum();
		 String lBeschreibung = termin.getBeschreibung();
		 if(lStartpunkt==null||lEndpunkt==null||lTag==null||lBeschreibung==null)
		 {
			bühne.close();
		 }
		 else
		 {
		 agKalender.appointments().addAll(
	               new Agenda.AppointmentImplLocal()
	                   .withStartLocalDateTime(lTag.atTime(lStartpunkt))
	                   .withEndLocalDateTime(lTag.atTime(lEndpunkt))
	                   .withDescription(lBeschreibung)
	                   .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group6")));
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


       
       @FXML
       public void auslesenTermine()
       {
    	  
    	  
//    	  KalenderController lTermin = new KalenderController();
    	  ArrayList<Termin> lTerminListe = Termin.auslesenTermine(Datenbankverbindung.getConnection(), 2);
    	  agKalender.appointments().addAll(
                   new Agenda.AppointmentImplLocal()
                       .withStartLocalDateTime(LocalDateTime.of(lTerminListe.get(0).getTerminDatum().getYear(),lTerminListe.get(0).getTerminDatum().getMonth(),lTerminListe.get(0).getTerminDatum().getDayOfMonth(),lTerminListe.get(0).getTerminZeit().getHour(),lTerminListe.get(0).getTerminZeit().getMinute(),lTerminListe.get(0).getTerminZeit().getSecond()))
                    			
                    			//LocalDate.of(lTerminListe.get(0).getTerminDatum()).atTime(lTerminListe.get(0).getTerminZeit()))
                       .withEndLocalDateTime(LocalDateTime.of(lTerminListe.get(0).getTerminDatum().getYear(),lTerminListe.get(0).getTerminDatum().getMonth(),lTerminListe.get(0).getTerminDatum().getDayOfMonth(),lTerminListe.get(0).getTerminZeitBis().getHour(),lTerminListe.get(0).getTerminZeitBis().getMinute(),lTerminListe.get(0).getTerminZeitBis().getSecond()))  //LocalDate.now().atTime(15, 30))
                       .withDescription("It's time")
                       .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1")) // you should use a map of AppointmentGroups
               );
    	  
    	  
       }
   }

