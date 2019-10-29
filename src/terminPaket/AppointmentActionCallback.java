package terminPaket;

import javafx.util.Callback;
import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.Agenda.Appointment;

public class AppointmentActionCallback implements Callback<Agenda.Appointment, Void>
{

   @Override
   public Void call(Appointment arg0)
   {
	  System.out.println("Callback Terminstartzeit in Action Callback: "+arg0.getStartTemporal().toString());
	  // TODO Auto-generated method stub
	  return null;
   }
   
}
