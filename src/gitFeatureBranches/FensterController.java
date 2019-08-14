package gitFeatureBranches;





import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
   import javafx.scene.control.ChoiceBox;
   import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tooltip;
import javafx.scene.control.MenuBar;
   public class FensterController {
	  private static DBVerbindung dbVerbindung = new DBVerbindung();
       @FXML
       private ChoiceBox<String> Personauswahl;

       @FXML
       private Label Lrang4;

       @FXML
       private Label Lrang5;

       @FXML
       private Label Lrang1;

       @FXML
       private Label Lrang2;

       @FXML
       private Label Lrang3;
       
       @FXML
       public void initialize()
       {
    	  if (dbVerbindung.verbinden("dbserver", "dbpr_termin", "dblkuser", "lkbenutzer")== false)
    	  {
    		return;
    	  }
    	  Personauswahl.getItems().addAll("HerrJacob", "KeyCode32", "Patrik");

    	  Personauswahl.setTooltip(new Tooltip("Wähle die Person aus"));
       }
       

   }


