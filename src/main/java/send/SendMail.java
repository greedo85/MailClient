package send;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Struct;
import java.util.Properties;
import java.util.Scanner;

public class SendMail {

    final String username = "mailattachmenttest@gmail.com";
    final String password = "testowe1";
    private Properties properties;
    private Session session;
    private Message message;
    private MimeMessage mimeMessage;
    private InternetAddress internetAddress;
    private Multipart multipart;
    private BodyPart bodyPart;
    private DataSource dataSource;
    private Scanner scanner;
    private char choice;
    private boolean isValid;

    public SendMail() {
        scanner = new Scanner(System.in);
        bodyPart = new MimeBodyPart();
        multipart = new MimeMultipart();
    }

    public void setConnection( String host ) {
        properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "25");

        session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    public void sendMessage( String to, String from, String title ) throws MessagingException {
        internetAddress = new InternetAddress();
        internetAddress.setAddress(from);
        mimeMessage = new MimeMessage(session);
        message = mimeMessage;
        message.setFrom(internetAddress);
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(title);
        String msg = "";
        System.out.println("Wpisz wiadomość");
        String line;
        while (!(line = scanner.nextLine() + "\n").equals("wyslij\n")) {
            msg += line;
        }
        bodyPart.setText(msg);
        multipart.addBodyPart(bodyPart);
        do {
            menu();

        } while (!isValid);
        message.setContent(multipart);
        Transport.send(message);
        System.out.println("wysłałem wiadomość");
    }

    private boolean menu() throws MessagingException {
        System.out.println("Wysłać załącznik? ");
        System.out.println("y - tak");
        System.out.println("n - nie");
        choice = scanner.next().charAt(0);
        switch (choice) {

            case 'y':
                System.out.println("podaj pełną ścieżkę do pliku:");
                scanner.nextLine();
                String filename;
                filename = scanner.nextLine();
                File file = new File(filename);
                if (file.exists()) {
                    addAttachmentFile(filename);
                    System.out.println("załączyłem plik");
                    isValid = true;
                } else {
                    System.out.println("Zła nazwa pliku");
                    isValid = false;
                }
                break;
            case 'n':
                isValid = true;
        }
        return isValid;
    }

    public void addAttachmentFile( String fileName ) throws MessagingException {
        bodyPart = new MimeBodyPart();
        dataSource = new FileDataSource(fileName);
        bodyPart.setDataHandler(new DataHandler(dataSource));
        bodyPart.setFileName(fileName);
        multipart.addBodyPart(bodyPart);
    }
}
