package gitFeatureBranches;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

   public class GruppenbearbeitungController {

       @FXML
       private Button btErstellen;
       
       @FXML
       private TextField tfGruppe;

       @FXML
       private Button btLöschen;

       @FXML
       private ChoiceBox<?> cbGruppe;
      
       @FXML
       public void initialize()
       {
    	  if (DBVerbindung.verbinden("dbserver", "dbpr_termin", "dblkuser", "lkbenutzer")== false)
    	  {
    		return;
    	  }


       
       

   }


