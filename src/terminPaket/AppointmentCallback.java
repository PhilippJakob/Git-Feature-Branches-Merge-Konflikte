package terminPaket;

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
	  return null;
	  
	  
   }
   
   

}  
