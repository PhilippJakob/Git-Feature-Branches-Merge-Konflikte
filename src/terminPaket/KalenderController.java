package terminPaket;



   import java.time.LocalDate;
   import javafx.fxml.FXML;
   import javafx.scene.control.Button;
   import jfxtras.scene.control.agenda.Agenda;
   import jfxtras.scene.control.agenda.Agenda.Appointment;
   import jfxtras.scene.control.agenda.Agenda.AppointmentGroup;


   public class KalenderController 
   {


	  @FXML
	  private Button btZusatzinformationen;

	  @FXML
	  private Agenda agKalender;
       
       
       public void initialize()
       {
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


   /*        // setup appointment groups
           final Map<String, Agenda.AppointmentGroup> lAppointmentGroupMap = new TreeMap<String, Agenda.AppointmentGroup>();
           Agenda lAgenda;
   		for (Agenda.AppointmentGroup lAppointmentGroup : lAgenda.appointmentGroups()) {
             lAppointmentGroupMap.put(lAppointmentGroup.getDescription(), lAppointmentGroup);
           } */
       }
}
