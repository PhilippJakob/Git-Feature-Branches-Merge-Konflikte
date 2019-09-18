
package gitFeatureBranches;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import jfxtras.scene.control.LocalDateTimeTextField;
import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.Agenda.Appointment;
import jfxtras.scene.control.agenda.Agenda.AppointmentGroup;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PopupController {

   private static Appointment appointment;
   private static Agenda agkalender;
//   public static Stage stage;
   
   

   @FXML
    private LocalDateTimeTextField bistimedate;

    @FXML
    private LocalDateTimeTextField abtimedate;

    @FXML
    private Button termindelete;

    @FXML
    private ColorPicker Tcolorpicker;

    @FXML private javafx.scene.control.Button closeButton;
    
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
    public void änderungspeichern(ActionEvent event) {
       
       LocalDateTime lbisdate = bistimedate.getLocalDateTime();
       LocalDateTime labdate = abtimedate.getLocalDateTime();
       boolean lisganztag =  ganztagcheck.isSelected();
       String lnotiz = taNotiz.getText();
       String lraum = TFraum.getText();
       String lgroup = getAppointment().getAppointmentGroup().getStyleClass();
       
       getAgkalender().appointments().remove(getAppointment());
       
       if(Tcolorpicker.getValue() == Color.LIGHTCORAL) {
        Agenda.AppointmentImplLocal lAppointment = new Agenda.AppointmentImplLocal()
        .withStartLocalDateTime(labdate)
        .withEndLocalDateTime(lbisdate)
        .withDescription(lnotiz)
        .withLocation(lraum)
        .withWholeDay(lisganztag)
        
        .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1")) // you should use a map of AppointmentGroups
        ;
        
        agkalender.appointments().addAll(lAppointment);
          
       }else  if(Tcolorpicker.getValue() == Color.BROWN) {
    	  Agenda.AppointmentImplLocal lAppointment = new Agenda.AppointmentImplLocal()
    		        .withStartLocalDateTime(labdate)
    		        .withEndLocalDateTime(lbisdate)
    		        .withDescription(lnotiz)
    		        .withLocation(lraum)
    		        .withWholeDay(lisganztag)
    		        
    		        .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group0")) // you should use a map of AppointmentGroups
    		        ;
    		        
    		        agkalender.appointments().addAll(lAppointment);
   
       }else  if(Tcolorpicker.getValue() == Color.LIGHTGREEN) {
    	  Agenda.AppointmentImplLocal lAppointment = new Agenda.AppointmentImplLocal()
   		        .withStartLocalDateTime(labdate)
   		        .withEndLocalDateTime(lbisdate)
   		        .withDescription(lnotiz)
   		        .withLocation(lraum)
   		        .withWholeDay(lisganztag)
   		        
   		        .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group6")) // you should use a map of AppointmentGroups
   		        ;
   		        
   		        agkalender.appointments().addAll(lAppointment);
  

          
       }else  if(Tcolorpicker.getValue() == Color.CORNFLOWERBLUE) {
    	  Agenda.AppointmentImplLocal lAppointment = new Agenda.AppointmentImplLocal()
   		        .withStartLocalDateTime(labdate)
   		        .withEndLocalDateTime(lbisdate)
   		        .withDescription(lnotiz)
   		        .withLocation(lraum)
   		        .withWholeDay(lisganztag)
   		        
   		        .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group15")) // you should use a map of AppointmentGroups
   		        ;
   		        
   		        agkalender.appointments().addAll(lAppointment);
  

          
       }else  if(Tcolorpicker.getValue() == Color.YELLOW) {
    	  Agenda.AppointmentImplLocal lAppointment = new Agenda.AppointmentImplLocal()
   		        .withStartLocalDateTime(labdate)
   		        .withEndLocalDateTime(lbisdate)
   		        .withDescription(lnotiz)
   		        .withLocation(lraum)
   		        .withWholeDay(lisganztag)
   		        
   		        .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group5")) // you should use a map of AppointmentGroups
   		        ;
   		        
   		        agkalender.appointments().addAll(lAppointment);
  
    	  
          
       }else {
    	  Agenda.AppointmentImplLocal lAppointment = new Agenda.AppointmentImplLocal()
      		        .withStartLocalDateTime(labdate)
      		        .withEndLocalDateTime(lbisdate)
      		        .withDescription(taNotiz.getText())
      		        .withLocation(TFraum.getText())
      		        .withWholeDay(lisganztag)
      		        
      		        .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass(lgroup)) // you should use a map of AppointmentGroups
      		        ;
      		        
      		        agkalender.appointments().addAll(lAppointment);
       }
       
       
      
       ((Node)(event.getSource())).getScene().getWindow().hide(); 
       
//       Agenda.AppointmentImplLocal lAppointment = new Agenda.AppointmentImplLocal()
//                .withStartLocalDateTime(LocalDate.now().atTime(4, 00))
//                .withEndLocalDateTime(LocalDate.now().atTime(15, 30))
//                .withDescription("It's time")
//                .withAppointmentGroup(new Agenda.AppointmentGroupImpl().withStyleClass("group1")) // you should use a map of AppointmentGroups
//        ;
//       agkalender.appointments().addAll(lAppointment);
//       
//       getAgkalender().appointments().remove(getAppointment());
       
       
       
       
      
    }
    
    
    @FXML  
    public void initialize()
    {
       bistimedate.setLocalDateTime(getAppointment().getEndLocalDateTime());
       abtimedate.setLocalDateTime(getAppointment().getStartLocalDateTime());
       ganztagcheck.setSelected(getAppointment().isWholeDay());
       taNotiz.setText(getAppointment().getDescription());
       TFraum.setText(getAppointment().getLocation());
       Tcolorpicker.getCustomColors().add(Color.BROWN);
       Tcolorpicker.getCustomColors().add(Color.LIGHTCORAL);
       Tcolorpicker.getCustomColors().add(Color.LIGHTGREEN);
       Tcolorpicker.getCustomColors().add(Color.CORNFLOWERBLUE);
       Tcolorpicker.getCustomColors().add(Color.YELLOW);
       
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


