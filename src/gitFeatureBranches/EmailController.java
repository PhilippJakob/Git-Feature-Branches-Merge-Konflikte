package gitFeatureBranches;

import javax.mail.MessagingException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EmailController {

    @FXML
    private TextField tfBetreff;

    @FXML
    private TextArea taText;

    @FXML
    private Button btVersenden;

    @FXML
    private ChoiceBox<String> cbAbsender;

    @FXML
    private Label lbAbsender;

    @FXML
    private Label lbEmpfänger;

    @FXML
    private ChoiceBox<String> cbEmpfänger;
    @FXML
    public void initialize()
    {
       cbAbsender.getItems().addAll(Person.getPersonen()); 
       cbEmpfänger.getItems().addAll(Person.getPersonen());
    }
    public void Versenden()
    {
	  try
	  {
		 SendEmail.generateAndSendEmail(tfBetreff.getText(), cbAbsender.getId(),cbEmpfänger.getId(), taText.getText());
	  }
	  catch (MessagingException e)
	  {
	  // TODO Automatisch generierter Erfassungsblock
	  e.printStackTrace();
	  }

    }
}
