import get.GetMail;
import send.SendMail;

import javax.mail.MessagingException;
import java.io.IOException;

public class Main {

    public static void main( String[] args ) throws MessagingException, IOException {
        SendMail sendMail=new SendMail();
        //sendMail.setConnection("smtp.gmail.com");
       // sendMail.sendMessage("qiliveadata@gmail.com","jamcion@onet.pl","Yeah");
        GetMail getMail=new GetMail("testmymailjava@gmail.com","Testmymail123");
        getMail.setConnection("imap.gmail.com",995);
        getMail.getMessages("INBOX");

    }

}
