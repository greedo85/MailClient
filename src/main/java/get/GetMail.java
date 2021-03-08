package get;

import database.controller.EmailAddressController;
import database.controller.MailAddressController;
import database.controller.EmailController;
import database.entities.Email;
import database.entities.EmailAddress;
import database.entities.MailAddress;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.persistence.NoResultException;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

public class GetMail {

    private Properties properties;
    private String saveDirectory;
    private Session session;
    private Store store;
    private Folder folder;
    private Message message;
    private Message[] messages;
    private InternetAddress[] fromAddressess;
    private Multipart multipart;
    private MimeBodyPart mimeBodyPart;
    private Scanner scanner;
    private File file;
    private EmailAddressController emailAddressController;
    private EmailController emailController;
    private Email email;
    private EmailAddress emailAddress;
    private MailAddressController mailAddressController;
    private MailAddress mailAddress;
    private final String user;
    private final String password;

    public GetMail( String user, String password ) {
        this.user = user;
        this.password = password;
        scanner = new Scanner(System.in);
        emailController = new EmailController();
        emailAddressController = new EmailAddressController();
    }

    public void setConnection( String host, int port ) throws MessagingException {
        properties = new Properties();
        properties.put("mail.store.protocol", "imap");
        properties.put("mail.pop3.host", host);
        properties.put("mail.pop3.port", String.valueOf(port));
        properties.put("mail.pop3.starttls.enable", "true");
        session = Session.getDefaultInstance(properties);
        store = session.getStore("imaps");
        store.connect(host, user, password);

    }

    public void getMessages( String folderName ) throws MessagingException, IOException, NoResultException {
        folder = store.getFolder(folderName);
        folder.open(Folder.READ_WRITE);
        messages = folder.getMessages();
        for (int i = 0; i < messages.length; i++) {
            message = messages[i];
            fromAddressess = (InternetAddress[]) message.getFrom();
            String from = fromAddressess[0].getAddress();
            String subject = message.getSubject();
            Date sendDate = message.getSentDate();
            String messageText = message.getContent().toString();
            String attachedFiles = "";
            if (message.getContentType().contains("multipart")) {
                multipart = (Multipart) message.getContent();
                for (int j = 0; j < multipart.getCount(); j++) {
                    mimeBodyPart = (MimeBodyPart) multipart.getBodyPart(j);
                    if (Part.ATTACHMENT.equals(mimeBodyPart.getDisposition())) {
                        menu();
                        file = new File(saveDirectory + "/" + mimeBodyPart.getFileName());
                        mimeBodyPart.saveFile(file);
                    }
                }
            }

            emailAddress= emailAddressController.findOrCreate(from);
            emailController.findOrCreate(subject, sendDate.toString(), "INBOX", messageText, emailAddress);

            /*do poprawy - trzeba pobrać listę adresów do których był wysłany dany email, adresy dodać do "książki"
             * i potem po kolei robić na jednej wiadomości dodawanie tych do tabeli ale z jednym konkretnym mailem*/
           /* mailAddressController = new MailAddressController();
            mailAddress = new MailAddress(email, emailAddress);
            mailAddressController.add(mailAddress);*/
            /*---------------------*/
            System.out.println("From: " + fromAddressess[0]);
            System.out.println("From String: " + from);
            System.out.println("Date: " + sendDate);
            System.out.println("Content: " + messageText);
        }
    }

    public void menu() {
        char choice;
        choice = scanner.next().charAt(0);
        System.out.println("zapisać załącznik?");
        switch (choice) {
            case 'y':
                System.out.println("podaj nazwę katalogu: ");
                saveDirectory = scanner.nextLine();
                break;
            case 'n':
                break;
        }
    }

}
