import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import org.apache.commons.io.FileUtils;


public class SenderMail {	
	private Properties properties = new Properties();



private void init(String mailFrom,String host, int port, String pass) {
	properties.put("mail.smtp.host", host);
	properties.put("mail.smtp.starttls.enable", "true");
	properties.put("mail.smtp.port",port);
	properties.setProperty("mail.smtp.user", mailFrom);
	properties.put("mail.smtp.auth", "true");
}

public void sendEmail(String pass,String mailFrom,String host, int port, String correoReceptor,String nombreReceptor, File html , File asustoMail) throws IOException{
	//Se crea propiedad de JavaMail
	init(mailFrom, host, port, pass);
	
	try{
		//Se crea session del correo
		Session session = Session.getInstance(properties, null);
		session.setDebug(false);
		
		//Se crea MimeBodyPart y se asigna el html del correo a enviar
		BodyPart BP = new MimeBodyPart();
		BP.setContent(adquirirHtml(html), "text/html; charset=iso-8859-1");
		
		//Se juntan todas las partes del mensaje solo en una parte compuesta.
		MimeMultipart multipart = new MimeMultipart();
		multipart.addBodyPart(BP);
		
		//Se construye el mensaje del correo.
		MimeMessage message = new MimeMessage(session);
		
		//Se rellena el From
		message.setFrom(new InternetAddress(mailFrom,"Playtyp.cl"));
		//Se rellenan los destinatarios
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));
		//Se rellena el subject
		message.setSubject(nombreReceptor + ", " + adquirirAsuntoString(asustoMail), "iso-8859-1");
		//Se incorporan las partes del mensaje.
		message.setContent(multipart);
		
		Transport t = session.getTransport("smtp");
		t.connect(mailFrom,pass);
		t.sendMessage(message, message.getAllRecipients());
		t.close();		
		System.out.println("Send message to: " +nombreReceptor +" al correo "+correoReceptor);
		

		
		
	}catch (MessagingException me){
                    System.out.println(me);
		return;
	}
}

private String adquirirHtml (File html) throws IOException {
	String shtml = null;
	try {
		 shtml = FileUtils.readFileToString(html, "iso-8859-1");
	}catch (Exception e) {
		System.out.println(e);
		return shtml;
	}
	return shtml;
	}

private String adquirirAsuntoString (File asustoMail) throws IOException {
	String sAsuntoMail = null;
	try {
		sAsuntoMail = FileUtils.readFileToString(asustoMail, "iso-8859-1");
	}catch (Exception e) {
		System.out.println(e);
		return sAsuntoMail;
	}
	return sAsuntoMail;
	}
	

}