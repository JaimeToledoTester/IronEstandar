package Adicional;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.json.simple.parser.ParseException;

public class Mail2 {
	Comprimir comp = Comprimir.getInstance();
	Parametros p = Parametros.getInstance();
    LeerJson lj=LeerJson.getInstance();
	FileChooser fc = FileChooser.getInstance();

	public void EnviarEmail() {
		try {
			System.out.println("====================leyendo data JSON...==================");
			lj.dataemail();//leer de el json los datos y guardarlo en clase parametros 
		} catch (FileNotFoundException e) {
			System.out.println("====================FILENOTFOUND...=================="+e.getStackTrace());
		} catch (IOException e) {
			System.out.println("====================IOEXCEPTION...=================="+e.getStackTrace());
		} catch (ParseException e) {
			System.out.println("====================PARSE...=================="+e.getStackTrace());
		}		
		if(p.msend.isEmpty()|p.msend.equals("false")) {
			System.out.println("NO SE HABILITO MAILS.");
		}else {
		try {		 
			comp.comprimira();
		} catch (Exception e) {
			System.out.println("Error al llamar metodo Comprimir en Mail" + e.getStackTrace());
		}
	    
        try {
        	Properties propiedad = new Properties();
            propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
            propiedad.setProperty("mail.smtp.starttls.enable", "true");
            propiedad.setProperty("mail.smtp.port", "587");
            propiedad.setProperty("mail.smtp.auth","true");
            propiedad.setProperty("mail.smtp.user",p.muser);
            propiedad.setProperty("mail.smtp.clave",p.mpass);
            Session sesion = Session.getDefaultInstance(propiedad);
            MimeMessage mail = new MimeMessage(sesion);
        	  //--------------------------------------------destinatarios
            InternetAddress [] destinatarios=new InternetAddress[p.mto.size()];
            for (int i= 0; i< p.mto.size(); i++) {
             destinatarios[i]=(new InternetAddress(p.mto.get(i)));
			}
            mail.addRecipients(Message.RecipientType.TO,destinatarios);
            //--------------------------------------------------------------
            mail.setFrom(new InternetAddress (p.mfrom));//de quien          
            mail.setSubject(p.msubject);//asunto
            //------------------------------------adjuntar texto
            BodyPart texto = new MimeBodyPart();
            texto.setText(p.mmessage);
            //--------------------------------------------------------ADJUNTAR ARCHIVO
            BodyPart adjunto= new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource(fc.traerarchivo("Seleccione el archivo para adjuntar al correo: "))));
            adjunto.setFileName(p.mfilename+".zip");
            //---------------------------------------AJUNTAR TODAS LAS MULTPARTES
            MimeMultipart todo= new MimeMultipart();
            todo.addBodyPart(texto);
            todo.addBodyPart(adjunto);
            //--------------------------------------se guarda en el mail todos los bodypart
            mail.setContent(todo);
            //-------------------------------enviar correo
            Transport transport=sesion.getTransport("smtp"); 
            transport.connect("smtp.gmail.com",p.mfrom,p.mpass);//loggin
            transport.sendMessage(mail,mail.getAllRecipients());
            transport.close();
            System.out.println("Listo, revise su correo");      
        } catch (AddressException ex) {
             System.out.println("Error enviar mail 1: "+ex.getStackTrace());
        } catch (MessagingException ex) {
        	 System.out.println("Error enviar mail 2: "+ex);
        }catch(Exception ex) {
        	 System.out.println("Error enviar mail 3: "+ex.getStackTrace());
        }
        }
	}
}
