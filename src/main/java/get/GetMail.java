package get;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import java.util.Properties;

public class GetMail {

    private Properties properties;
    private String saveDirectory;
    private Session session;
    private Store store;
    private Folder folder;
    private Message message;
    private Message []messages;
    private Address [] addresses;
    private Multipart multipart;
    private MimeBodyPart mimeBodyPart;

    private final String user;
    private final String password;

    public GetMail( String user, String password ) {
        this.user = user;
        this.password = password;
    }

    public void setConnection(String host, int port) throws MessagingException {
        properties=new Properties();
        properties.put("mail.store.protocol", "imap");
        properties.put("mail.pop3.host", host);
        properties.put("mail.pop3.port", String.valueOf(port));
        properties.put("mail.pop3.starttls.enable", "true");
        session = Session.getDefaultInstance(properties);
        store = session.getStore("impas");
        store.connect(host,user,password);

    }

    public void getMessages(String folderName) throws MessagingException {
        folder = store.getFolder(folderName);
        folder.open(Folder.READ_WRITE);
        messages = folder.getMessages();


    }


}
