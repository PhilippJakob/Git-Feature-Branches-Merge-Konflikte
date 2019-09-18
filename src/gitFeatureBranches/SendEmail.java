package gitFeatureBranches;


 
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
 
public class SendEmail {
 
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
 
	public static void main(String args[]) throws AddressException, MessagingException {
	//	generateAndSendEmail();
	//	System.out.println("\n\n ===> Deine Email wurde erfolgreich verschickt.");
	}
 
	public static void generateAndSendEmail(String Betreff, String Sender, String Empfänger,String Text, String Gruppen, String Einheiten) throws AddressException, MessagingException {
 
		// Step1
		System.out.println("\n 1st ===> Mailserver-Eigenschaften einrichten.");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		System.out.println("Die Mailserver-Eigenschaften wurden erfolgreich eingerichtet.");
 
		// Step2
		System.out.println("\n\n 2nd ===> Mail-Sitzung erhalten.");
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("freexplays01@gmail.com"));
//		generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("freexplays01@gmail.com"));
		generateMailMessage.setSubject(Betreff);
		String emailBody = Text;
		generateMailMessage.setContent(emailBody, "text/html");
		System.out.println("Mail Session wurde erfolgreich erstellt.");
 
		// Step3
		System.out.println("\n\n 3rd ===> Sitzung erhalten und Mail senden");
		Transport transport = getMailSession.getTransport("smtp");
 
		// Enter your correct gmail UserID and Password
		// if you have 2FA enabled then provide App Specific Password
		transport.connect("smtp.gmail.com", "musterversan@gmail.com", "MaxMuster_1");
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
	}
}