package terminPaket;

import java.time.LocalDate;
import java.time.LocalTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TerminController {
   
   private static String Beschreibung;
   private static LocalDate Datum;
   private static LocalTime UhrzeitVon;
   private static LocalTime UhrzeitBis;

    @FXML
    private TextField tfBeschreibung;

    @FXML
    private TextField tfUhrzeitVon;

    @FXML
    private Button btzurück;

    @FXML
    private DatePicker dpDatum;

    @FXML
    private TextField tfUhrzeitBis;

    @FXML
    private Button btBestätigen;

    @FXML
    void erstellenTermin(ActionEvent event) 
    {
       setBeschreibung(getTfBeschreibung().getText());
       setDatum(getDpDatum().getValue());
       setUhrzeitVon(LocalTime.parse(getTfUhrzeitVon().getText()));
       setUhrzeitBis(LocalTime.parse(getTfUhrzeitBis().getText()));
    }

    @FXML
    void zurück(ActionEvent event) 
    {
       Stage stage = (Stage) btzurück.getScene().getWindow();
    	stage.close();  
    }

   public static String getBeschreibung()
   {
      return Beschreibung;
   }

   public static void setBeschreibung(String beschreibung)
   {
      Beschreibung = beschreibung;
   }

   public static LocalDate getDatum()
   {
      return Datum;
   }

   public static void setDatum(LocalDate datum)
   {
      Datum = datum;
   }

   public static LocalTime getUhrzeitVon()
   {
      return UhrzeitVon;
   }

   public static void setUhrzeitVon(LocalTime uhrzeitVon)
   {
      UhrzeitVon = uhrzeitVon;
   }

   public static LocalTime getUhrzeitBis()
   {
      return UhrzeitBis;
   }

   public static void setUhrzeitBis(LocalTime uhrzeitBis)
   {
      UhrzeitBis = uhrzeitBis;
   }

   public TextField getTfBeschreibung()
   {
      return tfBeschreibung;
   }

   public void setTfBeschreibung(TextField tfBeschreibung)
   {
      this.tfBeschreibung = tfBeschreibung;
   }

   public TextField getTfUhrzeitVon()
   {
      return tfUhrzeitVon;
   }

   public void setTfUhrzeitVon(TextField tfUhrzeitVon)
   {
      this.tfUhrzeitVon = tfUhrzeitVon;
   }

   public Button getBtzurück()
   {
      return btzurück;
   }

   public void setBtzurück(Button btzurück)
   {
      this.btzurück = btzurück;
   }

   public DatePicker getDpDatum()
   {
      return dpDatum;
   }

   public void setDpDatum(DatePicker dpDatum)
   {
      this.dpDatum = dpDatum;
   }

   public TextField getTfUhrzeitBis()
   {
      return tfUhrzeitBis;
   }

   public void setTfUhrzeitBis(TextField tfUhrzeitBis)
   {
      this.tfUhrzeitBis = tfUhrzeitBis;
   }

   public Button getBtBestätigen()
   {
      return btBestätigen;
   }

   public void setBtBestätigen(Button btBestätigen)
   {
      this.btBestätigen = btBestätigen;
   }

}

