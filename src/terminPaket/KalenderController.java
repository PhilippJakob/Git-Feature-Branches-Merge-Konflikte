package terminPaket;



	import java.io.IOException;
   	import java.time.LocalDate;

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
	   private static DBVerbindung dbVerbindung = new DBVerbindung();

	  @FXML
	  private Button btZusatzinformationen;

	  @FXML
	  private Agenda agKalender;
       
	  private AnchorPane 	grundPane;
	  @FXML  
       public void initialize()
       {
     
		 if(!dbVerbindung.verbinden("dbserver","dbpr_termin","dblkuser","lkbenutzer"))
		 {
			return;
		 }
       	agKalender.appointments().addAll(
               new Agenda.AppointmentImplLocal()
                   .withStartLocalDateTime(LocalDate.now().atTime(4, 00))
                   .withEndLocalDateTime(LocalDate.now().atTime(15, 30))
                   .withDescription("It's time")
                   .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1")) // you should use a map of AppointmentGroups
           );
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


   /*        // setup appointment groups
           final Map<String, Agenda.AppointmentGroup> lAppointmentGroupMap = new TreeMap<String, Agenda.AppointmentGroup>();
           Agenda lAgenda;
   		for (Agenda.AppointmentGroup lAppointmentGroup : lAgenda.appointmentGroups()) {
             lAppointmentGroupMap.put(lAppointmentGroup.getDescription(), lAppointmentGroup);
           } */
       }
}
