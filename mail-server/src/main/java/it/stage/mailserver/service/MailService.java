package it.stage.mailserver.service;

import com.sun.mail.smtp.SMTPTransport;
import it.stage.mailserver.constant.MailConstant;
import it.stage.mailserver.payload.request.ConfirmationOrderRequest;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

import static javax.mail.Message.RecipientType.CC;
import static javax.mail.Message.RecipientType.TO;


@Service
public class MailService {

    public void sendConfirmationOrderEmail(ConfirmationOrderRequest mailRequest) throws MessagingException {

        Message message = createEmail(mailRequest);

        SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport(MailConstant.SIMPLE_MAIL_TRANSFER_PROTOCOL);
        smtpTransport.connect(MailConstant.GMAIL_SMTP_SERVER, MailConstant.USERNAME, MailConstant.PASSWORD);
        smtpTransport.sendMessage(message, message.getAllRecipients());
        smtpTransport.close();
    }

    private Message createEmail(ConfirmationOrderRequest mailRequest) throws MessagingException {

        Message message = new MimeMessage(getEmailSession());

        message.setFrom(new InternetAddress(MailConstant.FROM_EMAIL));
        message.setRecipients(TO, InternetAddress.parse(mailRequest.getEmailTo(), false));
        message.setRecipients(CC, InternetAddress.parse(MailConstant.CC_EMAIL, false));

        message.setSubject(MailConstant.SEND_PASSWORD_SUBJECT);
        message.setText(getMailBody(mailRequest));

        message.setSentDate(new Date());
        message.saveChanges();

        return message;
    }

    private Session getEmailSession() {
        Properties properties = System.getProperties();
        properties.put(MailConstant.SMTP_HOST, MailConstant.GMAIL_SMTP_SERVER);
        properties.put(MailConstant.SMTP_AUTH, true);
        properties.put(MailConstant.SMTP_PORT, MailConstant.DEFAULT_PORT);

        // This parameters are to have an secure connexion
        properties.put(MailConstant.SMTP_STARTTLS_ENABLE, true);
        properties.put(MailConstant.SMTP_STARTTLS_REQUIRED, true);

        // We return the session we need to send an email
        return Session.getInstance(properties, null);
    }

    private String getMailBody(ConfirmationOrderRequest mailRequest) {


        return "Hi \n"
                + "Just confirm that your order is done.\n\n"
                + "Your content this product:\n"
                + "email: " + mailRequest.getProductName() + "\n";
    }
}
