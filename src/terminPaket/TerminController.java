package terminPaket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class TerminController {
   Termin termin = new Termin();
  

    @FXML
    private TextArea tfBeschreibung;

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
    void initialize()
    {
     
    }
    @FXML
    void erstellenTermin(ActionEvent event) 
    {
       if(getTfBeschreibung().getText().isEmpty()||getTfUhrzeitBis().getText().isEmpty()||getTfUhrzeitVon().getText().isEmpty())
       {
    	  System.out.print("Fehler");
       }
       else
       {
       termin.setBeschreibung(getTfBeschreibung().getText());
       termin.setTerminDatum(getDpDatum().getValue());
       termin.setTerminZeit(LocalTime.parse(getTfUhrzeitVon().getText()));
       termin.setTerminZeitBis(LocalTime.parse(getTfUhrzeitBis().getText()));
       
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

