package terminPaket;



import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;

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


   public class KalenderController 
   {
	   
	  Stage bühne = new Stage();	

	  private static DBVerbindung dbVerbindung = new DBVerbindung();


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
     
		 if(!dbVerbindung.verbinden("dbserver","dbpr_termin","dblkuser","lkbenutzer"))
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
   /*        // setup appointment groups
           final Map<String, Agenda.AppointmentGroup> lAppointmentGroupMap = new TreeMap<String, Agenda.AppointmentGroup>();
           Agenda lAgenda;
   		for (Agenda.AppointmentGroup lAppointmentGroup : lAgenda.appointmentGroups()) {
             lAppointmentGroupMap.put(lAppointmentGroup.getDescription(), lAppointmentGroup);
           } */
       }

	
	  @SuppressWarnings("static-access")
	  @FXML
	  public void Termin()
	  {
		 TerminController terminController = new TerminController();
		 LocalTime lStartpunkt = terminController.getUhrzeitVon();
		 LocalTime lEndpunkt = terminController.getUhrzeitBis();
		 LocalDate lTag = terminController.getDatum();
		 String lBeschreibung = terminController.getBeschreibung();
		 agKalender.appointments().addAll(
	               new Agenda.AppointmentImplLocal()
	                   .withStartLocalDateTime(lTag.atTime(lStartpunkt))
	                   .withEndLocalDateTime(lTag.atTime(lEndpunkt))
	                   .withDescription(lBeschreibung)
	                   .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group6")));
		 try
		 {
			Statement stmt = DBVerbindung.connection.createStatement();
			ResultSet result = stmt.executeQuery("insert into termin (Datum, UhrzeitVon, UhrzeitBis, InfoTermin) values "
					 + "											 ("+lTag+""+lStartpunkt+""+lEndpunkt+""+lBeschreibung+"");
		 }
		 catch (SQLException e)
		 {
			
			e.printStackTrace();
		 }
	  }
	  @FXML	
	  public void updateDB()
	  {
		 
	  }

	  public Agenda getAgKalender()
	  {
	     return agKalender;
	  }


	  public void setAgKalender(Agenda agKalender)
	  {
	     this.agKalender = agKalender;
	  }
}
