package terminPaket;

import java.time.LocalDate;
import java.time.LocalTime;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.Agenda.AppointmentImpl;

public class TerminController 
{
   	
	  private static String Beschreibung;
   	  private static LocalDate Datum; 
   	  private static LocalTime UhrzeitBis;
   	  private static LocalTime UhrzeitVon;
	  
   	 
	 
   
   @FXML
   private Button btBestätigen;

   @FXML
   private Button btZurück;
   
   @FXML
   private DatePicker dpDatum;

   @FXML
   private TextArea tfBeschreibung;

   @FXML
   private TextField tfUhrBis;

   @FXML
   private TextField tfUhrVon;

   public void bestätigenTermin(ActionEvent Event)
   {
	  
	  setBeschreibung(getTfBeschreibung().getText());
	  setDatum(getDpDatum().getValue());
	  setUhrzeitBis(LocalTime.parse(getTfUhrBis().getText()));
	  setUhrzeitVon(LocalTime.parse(getTfUhrVon().getText()));
	  

	 
	  
   }
   public void zurück(ActionEvent event)
   {
	  Stage stage = (Stage) btZurück.getScene().getWindow();
	  stage.close();
   }
   
  

   public TextArea getTfBeschreibung()
   {
      return tfBeschreibung;
   }

   public void setTfBeschreibung(TextArea tfBeschreibung)
   {
      this.tfBeschreibung = tfBeschreibung;
   }

   public TextField getTfUhrBis()
   {
      return tfUhrBis;
   }

   public void setTfUhrBis(TextField tfUhrBis)
   {
      this.tfUhrBis = tfUhrBis;
   }

   public TextField getTfUhrVon()
   {
      return tfUhrVon;
   }

   public void setTfUhrVon(TextField tfUhrVon)
   {
      this.tfUhrVon = tfUhrVon;
   }

   public DatePicker getDpDatum()
   {
      return dpDatum;
   }

   public void setDpDatum(DatePicker dpDatum)
   {
      this.dpDatum = dpDatum;
   }



   public Button getBtBestätigen()
   {
      return btBestätigen;
   }



   public void setBtBestätigen(Button btBestätigen)
   {
      this.btBestätigen = btBestätigen;
   }



   public String getBeschreibung()
   {
      return Beschreibung;
   }



   public void setBeschreibung(String beschreibung)
   {
      Beschreibung = beschreibung;
   }



   public LocalDate getDatum()
   {
      return Datum;
   }



   public void setDatum(LocalDate datum)
   {
      Datum = datum;
   }



   public LocalTime getUhrzeitBis()
   {
      return UhrzeitBis;
   }



   public void setUhrzeitBis(LocalTime uhrzeitBis)
   {
      UhrzeitBis = uhrzeitBis;
   }



   public LocalTime getUhrzeitVon()
   {
      return UhrzeitVon;
   }



   public void setUhrzeitVon(LocalTime uhrzeitVon)
   {
      UhrzeitVon = uhrzeitVon;
   }

  
}
