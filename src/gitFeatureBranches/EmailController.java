package gitFeatureBranches;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EmailController
{
   @FXML
   private TextField tfEmpfänger;

   @FXML
   private TextField tfBetreff;

   @FXML
   private TextField tfAbsender;

   @FXML
   private Button btVersenden;
   @FXML
   private TextArea taText;
   @FXML
   public void initialize()
   {
	  
   }
   public void Versenden()
   {
	  try
	  {
		 SendEmail.generateAndSendEmail(tfBetreff.getText(), tfAbsender.getText(), tfEmpfänger.getText(), taText.getText());
	  }
	  catch (MessagingException e)
	  {
	  // TODO Automatisch generierter Erfassungsblock
	  e.printStackTrace();
	  }

   }
}
