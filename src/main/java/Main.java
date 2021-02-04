import send.SendMail;

import javax.mail.MessagingException;

public class Main {

    public static void main( String[] args ) throws MessagingException {
        SendMail sendMail=new SendMail();
        sendMail.setConnection("smtp.gmail.com");
        sendMail.sendMessage("qiliveadata@gmail.com","jamcion@onet.pl","Yeah");

    }

}
