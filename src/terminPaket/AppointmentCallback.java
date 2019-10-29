package terminPaket;

import java.util.Comparator;

import javafx.collections.ObservableList;
import javafx.util.Callback;
import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.Agenda.Appointment;
import jfxtras.scene.control.agenda.Agenda.AppointmentImplLocal;

public class AppointmentCallback implements Callback<Agenda.Appointment, Void>
{

   @Override
   public Void call(Appointment param)
   {
	  // TODO Auto-generated method stub
	  System.out.println("Callback Terminstartzeit: "+param.getStartTemporal().toString());
	  
	  Agenda lAgenda = KalenderController.getAgKalender();
	  ObservableList<Agenda.Appointment> lAppointments = lAgenda.appointments();
	  lAppointments.sort(leEndDateTime);
	  
	  for (Agenda.Appointment lAppointment:lAppointments )
	  {
	     if (!lAppointment.getEndLocalDateTime().isBefore(param.getStartLocalDateTime()))
	     {
	    	if (überlappenTermine(lAppointment,param))
	    	{
	    	   System.out.println("Callback ÜberlappungsFehlermeldung: "+param.getStartTemporal().toString());
	    	}
	     }
	  }
	  return null;
   }

   private boolean überlappenTermine(Appointment lAppointment, Appointment param)
   {
	  // TODO Auto-generated method stub
	  return true;
   }
   
   public static Comparator<Appointment> leEndDateTime = new Comparator<Appointment>()
   {
       public int compare(Appointment pAppointment1, Appointment pAppointment2) {
         if (pAppointment1.getEndLocalDateTime().isBefore(pAppointment2.getEndLocalDateTime()))
         {
               return -1;
           }
            else if (pAppointment1.getEndLocalDateTime().equals(pAppointment2.getEndLocalDateTime()))
                 { 
                    if (pAppointment1.getStartLocalDateTime().isBefore(pAppointment2.getStartLocalDateTime()))
                    { return -1;}
                    else
                    { return 0;}
                 }
                 else         
                 {
                    return 1;
                 }
       }
   };
   
   

}
