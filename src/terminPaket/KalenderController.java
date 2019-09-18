package terminPaket;


   import java.io.ObjectInputStream.GetField;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

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

       @FXML
       private Agenda agKalender;
       
       public void initialize()
       {
           // create Agenda
        //agKalender = new Agenda();

           // add an appointment
/*        agKalender.appointments().addAll(
               new Agenda.AppointmentImplLocal()
                   .withStartLocalDateTime(LocalDate.now().atTime(4, 00))
                   .withEndLocalDateTime(LocalDate.now().atTime(15, 30))
                   .withDescription("It's time")
                   .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1")) // you should use a map of AppointmentGroups
           );
           */
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
       public void auslesenTermine()
       {
        
        
//        KalenderController lTermin = new KalenderController();
        ArrayList<Termin> lTerminListe = Termin.auslesenTermine(Datenbankverbindung.getConnection(), 1);
        
        for(Termin lTermin:lTerminListe)
        {
         int lSYear = lTermin.getTerminDatum().getYear();
         int lSMonth =lTermin.getTerminDatum().getMonthValue();
         int lSDay = lTermin.getTerminDatum().getDayOfMonth();
         int lSHour = lTermin.getTerminZeit().getHour();
         int lSMinute = lTermin.getTerminZeit().getMinute();
         
         int lEYear = lTermin.getTerminDatum().getYear();
         int lEMonth = lTermin.getTerminDatum().getMonthValue();
         int lEDay = lTermin.getTerminDatum().getDayOfMonth();
         int lEHour = lTermin.getTerminZeitBis().getHour();
         int lEMinute = lTermin.getTerminZeitBis().getMinute();
         //asdsda
         String lTerminInfo = lTerminListe.get(0).getTerminInfo();
        agKalender.appointments().addAll(
                   new Agenda.AppointmentImplLocal()
                       .withStartLocalDateTime(LocalDateTime.of(lSYear, lSMonth, lSDay, lSHour, lSMinute))
                       .withEndLocalDateTime(LocalDateTime.of(lEYear, lEMonth,lEDay, lEHour, lEMinute))
                       .withDescription(lTerminInfo)
                       .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1")) // you should use a map of AppointmentGroups
               );
        }
        
        
       }
   }