package gitFeatureBranches;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import jfxtras.scene.control.LocalDateTimeTextField;
import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.Agenda.Appointment;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PopupController {

   private static Appointment appointment;
   private static Agenda agkalender;
  
   
   

   @FXML
    private LocalDateTimeTextField bistimedate;

    @FXML
    private LocalDateTimeTextField abtimedate;

    @FXML
    private Button termindelete;

    @FXML
    private ColorPicker Tcolorpicker;

    @FXML
    private CheckBox ganztagcheck;

    @FXML
    private TextArea taNotiz;

    @FXML
    private TextField TFraum;
    
    @FXML
    private Button bänderungenspeichern;
    
    @FXML
    public void Terminlöschen(ActionEvent event) {

      getAgkalender().appointments().remove(getAppointment());
       
    }
    @FXML  
    public void initialize()
    {
       bistimedate.setLocalDateTime(getAppointment().getEndLocalDateTime());
       abtimedate.setLocalDateTime(getAppointment().getStartLocalDateTime());
       ganztagcheck.setSelected(getAppointment().isWholeDay());
       taNotiz.setText(getAppointment().getDescription());
       TFraum.setText(getAppointment().getLocation());
    }
   public static Appointment getAppointment()
    {
       return appointment;
    }

    public static void setAppointment(Appointment appointment)
    {
       PopupController.appointment = appointment;
    }

    public static Agenda getAgkalender()
    {
       return agkalender;
    }

    public static void setAgkalender(Agenda agkalender)
    {
       PopupController.agkalender = agkalender;
    }
    
}
