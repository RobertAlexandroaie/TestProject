package com.endavafii.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import org.apache.commons.lang3.StringUtils;

public class EmailSenderUtil {
	
	private final static String localhost = "localhost";

	public static void sendMail(final String sbHTML, final String sbPlain, final String fromAddress, final String toAddress)
            throws MessagingException {
		
		if(StringUtils.isEmpty(toAddress)){		
			return;
		}
		
		final Properties props = new Properties();
		props.put("mail.smtp.host", localhost);
		props.put("mail.smtp.port", 25);
		props.put("mail.smtp.auth","false");
        final Session session = Session.getInstance(props);

		
		try {
			final Message msg = new MimeMessage(session);
			
			msg.setFrom(new InternetAddress(fromAddress));
			msg.setRecipient(RecipientType.TO, new InternetAddress(toAddress));
			msg.setSubject("[TEST]SignUp");
			msg.setSentDate(new Date());
			
			final BodyPart textContent = new MimeBodyPart();
			textContent.setContent(sbPlain, "text/plain");
			
			final BodyPart htmlContent = new MimeBodyPart();
			htmlContent.setContent(sbHTML, "text/html");
			
			final MimeMultipart mp = new MimeMultipart("alternative"); 
			mp.addBodyPart(textContent);
			mp.addBodyPart(htmlContent);
			
			msg.setContent(mp);
			
			Transport.send(msg);
		}
		catch (MessagingException mex) {
			// Prints all nested (chained) exceptions as well
			throw mex;
		}
	}
}

