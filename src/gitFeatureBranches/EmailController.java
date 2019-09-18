package gitFeatureBranches;

import java.util.ArrayList;

import javax.mail.MessagingException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EmailController {

   
   private static ArrayList<Person> personenOEAL	= new ArrayList<Person>();
   private static ArrayList<Person> personenGAL		= new ArrayList<Person>();
   
     
    @FXML
    private TextField tfBetreff;

    @FXML
    private TextArea taText;

    @FXML
    private Button btVersenden;
    
    @FXML
    private Label lbAbsender;

    @FXML
    private Label lbEmpfänger;

    @FXML
    private ChoiceBox<String> cbAbsender;
    
    @FXML
    private ChoiceBox<String> cbEmpfänger;

    @FXML
    private Label lbGruppen;

    @FXML
    private Label lbEinheiten;
    
    @FXML
    private ChoiceBox<String> cbGruppen;

    @FXML
    private ChoiceBox<String> cbEinheiten;

   
    @FXML
    public void initialize()
    {
       cbAbsender.getItems().addAll(Person.getPersonen()); 
       cbEmpfänger.getItems().addAll(Person.getPersonen());
 	  cbEinheiten.getItems().addAll(Organisationseinheit.getOrganisationseinheiten());
 	  cbGruppen.getItems().addAll(Gruppe.getGruppen());

    }
    
    
    
    public void Versenden()
    {
	  try
	  {
		 SendEmail.generateAndSendEmail(tfBetreff.getText(), cbAbsender.getId(),cbEmpfänger.getId(), taText.getText(), cbGruppen.getId(),cbEinheiten.getId());
	  }
	  catch (MessagingException e)
	  {
	  // TODO Automatisch generierter Erfassungsblock
	  e.printStackTrace();
	  }

    }
    
    public void ermittelnPersonenausGruppe()
    {
       
    }
    
    


   public static ArrayList<Person> getPersonenOEAL()
   {
	  return personenOEAL;
   }



   public static void setPersonenOEAL(ArrayList<Person> personenOEAL)
   {
	  EmailController.personenOEAL = personenOEAL;
   }



   public static ArrayList<Person> getPersonenGAL()
   {
	  return personenGAL;
   }



   public static void setPersonenGAL(ArrayList<Person> personenGAL)
   {
	  EmailController.personenGAL = personenGAL;
   }
}
