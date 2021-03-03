package get;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
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
    private Address[] addresses;
    private Multipart multipart;
    private MimeBodyPart mimeBodyPart;
    private Scanner scanner;
    private File file;
    

    private final String user;
    private final String password;

    public GetMail( String user, String password ) {
        this.user = user;
        this.password = password;
        scanner = new Scanner(System.in);
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

    public void getMessages( String folderName ) throws MessagingException, IOException {
        folder = store.getFolder(folderName);
        folder.open(Folder.READ_WRITE);
        messages = folder.getMessages();
        for (int i = 0; i < messages.length; i++) {
            message = messages[i];
            addresses = message.getFrom();
            /*test*/
            String from = message.getFrom().toString();
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
            System.out.println("From: "+addresses[0]);
            System.out.println("Date: "+sendDate);
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
