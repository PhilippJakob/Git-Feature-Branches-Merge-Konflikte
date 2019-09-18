package terminPaket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import jfxtras.scene.control.CalendarTextField;
import jfxtras.scene.control.CalendarTimeTextField;
import jfxtras.scene.control.LocalDateTextField;
import jfxtras.scene.control.LocalDateTimeTextField;


public class TerminController {
   Termin termin = new Termin();
  

    @FXML
    private TextArea tfBeschreibung;

    @FXML
    private LocalDateTimeTextField ldttfVon;

    @FXML
    private LocalDateTimeTextField ldttfBis;
    
    @FXML
    private Button btzurück;
    
    @FXML
    private Button btBestätigen;
    
    @FXML
    private TextField tfRaumnummer;
    
    @FXML
    private CheckBox ckPrivat;



    @FXML
    void initialize()
    {
       
    }
    @SuppressWarnings("static-access")
   @FXML
    void erstellenTermin(ActionEvent event) 
    {
       if(getTfBeschreibung().getText().isEmpty())
    	
       {
    	  System.out.print("Fehler");
       }
       else
       {
    	  
       termin.setTerminInfo(getTfBeschreibung().getText());
       termin.setTerminDatumVon(getLdttfVon().getLocalDateTime());
       termin.setTerminDatumBis(getLdttfBis().getLocalDateTime());
       termin.setTerminRaum(Integer.parseInt(getTfRaumnummer().getText()));
       if(getCkPrivat().isSelected())
       {
    	  termin.setTerminPrivat(1);
       }
       else
       {
    	  termin.setTerminPrivat(0);
       }
       
       
       if (btBestätigen.onActionProperty() != null)
		{
		   termin.übergebenInDB(Datenbankverbindung.getConnection());
		   schließen();
		   
		}
       }
    }

    @FXML
    void zurück(ActionEvent event) 
    {
       Stage stage = (Stage) btzurück.getScene().getWindow();
    	stage.close();  
    }
    @FXML
    void schließen()
    {
       Stage stage = (Stage) btBestätigen.getScene().getWindow();


       
       btBestätigen.setOnAction(event ->
               stage.fireEvent(
                       new WindowEvent(
                               stage,
                               WindowEvent.WINDOW_CLOSE_REQUEST
                       )
               )
       );
    }
  
   public TextArea getTfBeschreibung()
   {
      return tfBeschreibung;
   }

   public void setTfBeschreibung(TextArea tfBeschreibung)
   {
      this.tfBeschreibung = tfBeschreibung;
   }

   public Button getBtzurück()
   {
      return btzurück;
   }

   public void setBtzurück(Button btzurück)
   {
      this.btzurück = btzurück;
   }



   public Button getBtBestätigen()
   {
      return btBestätigen;
   }

   public void setBtBestätigen(Button btBestätigen)
   {
      this.btBestätigen = btBestätigen;
   }
   public TextField getTfRaumnummer()
   {
      return tfRaumnummer;
   }
   public void setTfRaumnummer(TextField tfRaumnummer)
   {
      this.tfRaumnummer = tfRaumnummer;
   }
   public CheckBox getCkPrivat()
   {
      return ckPrivat;
   }
   public void setCkPrivat(CheckBox ckPrivat)
   {
      this.ckPrivat = ckPrivat;
   }
   public LocalDateTimeTextField getLdttfVon()
   {
      return ldttfVon;
   }
   public void setLdttfVon(LocalDateTimeTextField ldttfVon)
   {
      this.ldttfVon = ldttfVon;
   }
   public LocalDateTimeTextField getLdttfBis()
   {
      return ldttfBis;
   }
   public void setLdttfBis(LocalDateTimeTextField ldttfBis)
   {
      this.ldttfBis = ldttfBis;
   }
   
   }



