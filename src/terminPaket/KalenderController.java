package terminPaket;



	import java.io.IOException;
   	import java.time.LocalDate;

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
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
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
	  private Agenda agKalender;
       
	  private AnchorPane 	grundPane;
	  @FXML  
       public void initialize()
       {
		 agKalender.setEditAppointmentCallback( (appointment) -> {
			PopupController.setAppointment(appointment);
		       PopupController.setAgkalender(agKalender);
//		       appointment.setLocation("A1110");
//		       appointment.setDescription("hallo meine freunde der sonne");
		       //agKaleder.appointments().remove(appointment);
//		       PopupController.setGanztag(appointment.setWholeDay());
		      
//			System.out.println("KalenderController");
			
		       try
			   {
		    	  Stage bühne = new Stage();	
			       FXMLLoader lLoader = new FXMLLoader();
		    	  	   lLoader.setLocation(getClass().getResource("popupnew.fxml"));
		    	  	   grundPane = lLoader.load();
		    	  	   Scene lScene = new Scene(grundPane);
				       bühne.setScene(lScene);
				       bühne.show();  
				      
				       System.out.println(appointment.getEndLocalDateTime());
				       System.out.println(appointment.getStartLocalDateTime());
				       
			   }
			   catch (IOException e)
			   {
				  // TODO Automatisch generierter Erfassungsblock
				  e.printStackTrace();
			   }
		
			
	        return null;
	    });
//		 if(!dbVerbindung.verbinden("dbserver","dbpr_termin","dblkuser","lkbenutzer"))
//		 {
//			return;
//		 }
		 Agenda.AppointmentImplLocal lAppointment = new Agenda.AppointmentImplLocal()
                  .withStartLocalDateTime(LocalDate.now().atTime(4, 00))
                  .withEndLocalDateTime(LocalDate.now().atTime(15, 30))
                  .withDescription("It's time")
                  .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1")) // you should use a map of AppointmentGroups
          ;
      agKalender.appointments().addAll(lAppointment);
       	
 


	  agKalender.setAppointmentChangedCallback( new AppointmentCallback()); 
  
     	
       	
       	
       	btZusatzinformationen.setOnAction(new EventHandler<ActionEvent>(){
		    @Override
		    public void handle(ActionEvent event)
		    { 
		       Stage bühne = new Stage();	
		       FXMLLoader lLoader = new FXMLLoader();
//		       PopupController.stage = bühne;
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
}
