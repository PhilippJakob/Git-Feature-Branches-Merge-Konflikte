package terminPaket;



	import java.io.IOException;
   	import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.collections.WeakListChangeListener;
import javafx.event.ActionEvent;
	import javafx.event.EventHandler;
	import javafx.fxml.FXML;
	import javafx.fxml.FXMLLoader;
	import javafx.scene.Scene;
	import javafx.scene.control.Button;
	import javafx.scene.layout.AnchorPane;
	import javafx.stage.Stage;
import jfxtras.icalendarfx.utilities.Callback;
import jfxtras.scene.control.agenda.Agenda;
	import jfxtras.scene.control.agenda.Agenda.Appointment;
	import jfxtras.scene.control.agenda.Agenda.AppointmentGroup;


   public class KalenderController 
   {
	   private static DBVerbindung dbVerbindung = new DBVerbindung();


	  @FXML
	  private Button btZusatzinformationen;

	  @FXML
	  private static Agenda agKalenderST;// = new Agenda();

	  @FXML
	  private Agenda agKalender;

	  @FXML
	  private AnchorPane 	grundPane;
	  @FXML  
       public void initialize()
       {
     
//		 if(!dbVerbindung.verbinden("dbserver","dbpr_termin","dblkuser","lkbenutzer"))
//		 {
//			return;
//		 }

		 //agKalender = new Agenda();
	  
	    Agenda.AppointmentImplLocal lAppointment2 = new
	    Agenda.AppointmentImplLocal()
	    .withStartLocalDateTime(LocalDate.now().atTime(6, 0))
	    .withEndLocalDateTime(LocalDate.now().atTime(7, 30))
	    .withDescription("It's time") .withAppointmentGroup(new
	    Agenda.AppointmentGroupImpl().withStyleClass("group2")); 
	    agKalender.appointments().add(lAppointment2);
	    Agenda.AppointmentImplLocal lAppointment3 = new
	    Agenda.AppointmentImplLocal()
	    .withStartLocalDateTime(LocalDate.now().atTime(8, 0))
	    .withEndLocalDateTime(LocalDate.now().atTime(9, 30))
	    .withDescription("It's time") .withAppointmentGroup(new
	    Agenda.AppointmentGroupImpl().withStyleClass("group3")); 
	    agKalender.appointments().add(lAppointment3);
	    Agenda.AppointmentImplLocal lAppointment4 = new
	    Agenda.AppointmentImplLocal()
	    .withStartLocalDateTime(LocalDate.now().atTime(10, 0))
	    .withEndLocalDateTime(LocalDate.now().atTime(10, 30))
	    .withDescription("It's time") .withAppointmentGroup(new
	    Agenda.AppointmentGroupImpl().withStyleClass("group4")); 
	    agKalender.appointments().addAll(lAppointment4);
	   
		 Agenda.AppointmentImplLocal lAppointment1 = new Agenda.AppointmentImplLocal()
                  .withStartLocalDateTime(LocalDateTime.of(2019,10 ,30,4,0))//LocalDate.now().atTime(4, 0))
                  .withEndLocalDateTime(LocalDateTime.of(2019,10 ,30,5,30))//LocalDate.now().atTime(5, 30))
                  .withDescription("It's time")
                  .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1")) // you should use a map of AppointmentGroups
          ;
		 agKalender.appointments().addAll(lAppointment1);   	
 


	  agKalender.setAppointmentChangedCallback( new AppointmentCallback()); 
	  agKalender.setActionCallback(new AppointmentActionCallback());

	  agKalenderST =  agKalender;
     	
       	
       	
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
       }
	  public static Agenda getAgKalender()
	  {
	     return agKalenderST;
	  }
	  public static void setAgKalender(Agenda agKalender)
	  {
	     KalenderController.agKalenderST = agKalender;
	  }

	  
}
