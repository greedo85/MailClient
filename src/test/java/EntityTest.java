import database.controller.MailAddressController;
import database.entities.Email;
import database.entities.EmailAddress;
import database.controller.EmailAddressController;
import database.controller.EmailController;
import database.entities.MailAddress;
import org.junit.Test;

import javax.persistence.PersistenceException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class EntityTest {
    @Test
    public void addAddressTest() {
        //given
        EmailAddress emailAddress1 = new EmailAddress("test@test.pl");
        EmailAddress emailAddress2 = new EmailAddress("abc@abc.pl");
        //when
        EmailAddressController emailAddressController = new EmailAddressController();
        emailAddressController.add(emailAddress1);
        emailAddressController.add(emailAddress2);
        //then
        System.out.println(emailAddressController.getByString("test@test.pl"));
        assertEquals(emailAddressController.getByString("test@test.pl"), emailAddress1);
    }

    @Test
    public void findAllAddressesTest() {

        //given
        EmailAddressController emailAddressController = new EmailAddressController();
        System.out.println(emailAddressController.getAll());
    }

    @Test
    public void addMailTest() {
        //given
        String date = LocalDateTime.now().toString();
        EmailAddressController emailAddressController = new EmailAddressController();
        EmailAddress emailAddress = emailAddressController.getById(3);
        EmailController emailController = new EmailController();
        Email email = new Email("Temat2", date, "odebrana", "testowa treść", emailAddress);
        //when
        emailController.add(email);
    }

    @Test
    public void mailAddressTest() {
        //given
        EmailAddressController emailAddressController = new EmailAddressController();
        EmailAddress emailAddress1 = emailAddressController.getById(3);
        EmailAddress emailAddress2 = emailAddressController.getById(1);
        EmailController emailController = new EmailController();
        Email email1 = emailController.getById(3);
        Email email2 = emailController.getById(4);
        MailAddress mailAddress1 = new MailAddress(email1, emailAddress2);
        MailAddress mailAddress2 = new MailAddress(email2, emailAddress1);
        MailAddressController mailAddressController = new MailAddressController();
        //when
        mailAddressController.add(mailAddress1);
        mailAddressController.add(mailAddress2);
        //then
        assertEquals(mailAddressController.getById(23).getEmailAddress(), emailAddress2);
        assertEquals(mailAddressController.getById(23).getEmail(), email1);
    }

    @Test
    public void findMailByString() {
        //given
        EmailController emailController = new EmailController();
        String subject = "Temat";

        //when
        List<Email> getByTextList = emailController.getByText(subject);
        List<Email> getAllList = emailController.getAll();
        Stream<Email> mailStream = getAllList.stream();
        List<Email> findBySubject = mailStream.filter(m -> m.getSubject().contains(subject)).collect(Collectors.toList());
        System.out.println(findBySubject);
        //then
        assertEquals(findBySubject, getByTextList);
    }

    @Test
    public void duplicateTest() {
        //given
        EmailAddressController emailAddressController = new EmailAddressController();
        EmailController emailController = new EmailController();

        //when
        try {
            emailAddressController.add(new EmailAddress("no-reply@accounts.google.com"));
        } catch (PersistenceException e) {
            System.out.println("Wpis już istnieje");
        }
    }
}
