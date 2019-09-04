package gitFeatureBranches;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class SendEmailSMTP {

    	public static void main(String[] args) {
    		//authentication info 
    		final String username = "testabschicken@gmail.com";
    		final String password = "testabschicken1_";
    		String fromEmail = "testabschicken@gmail.com";
    		String toEmail = "testerhalten@gmail.com";
    		
    		Properties properties = new Properties();
    		properties.put("mail.smtp.auth", "true");
    		properties.put("mail.smtp.starttls.enable", "true");
    		properties.put("mail.smtp.host", "smtp.gmail.com");
    		properties.put("mail.smtp.port", "25");
    		
    		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
    			protected PasswordAuthentication getPasswordAuthentication() {
    				return new PasswordAuthentication(username,password);
    			}
    		});
    		//Start our mail message
    		MimeMessage msg = new MimeMessage(session);
    		try {
    			msg.setFrom(new InternetAddress(fromEmail));
    			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
    			msg.setSubject("Subject Line");
    			
    			Multipart emailContent = new MimeMultipart();
    			
    			//Text body part
    			MimeBodyPart textBodyPart = new MimeBodyPart();
    			textBodyPart.setText("My multipart text");
    			
    			
    			//Attach body parts
    			emailContent.addBodyPart(textBodyPart);
    			
    			
    			//Attach multipart to message
    			msg.setContent(emailContent);
    			
    			Transport.send(msg);
    			System.out.println("Sent message");
    		} catch (MessagingException e) {
    			e.printStackTrace();
    		}
    	}
}
