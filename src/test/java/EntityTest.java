import database.entities.Address;
import database.entities.AddressManager;
import database.entities.Mail;
import database.entities.MailManager;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class EntityTest {
    @Test
    public void addAddressTest()
    {
        //given
        Address address1=new Address("test@test.pl");
        Address address2=new Address("abc@abc.pl");
        //when
        AddressManager addressManager=new AddressManager();
        addressManager.add(address1);
        addressManager.add(address2);
        //then
        System.out.println(addressManager.getByMail("test@test.pl"));
        assertEquals(addressManager.getByMail("test@test.pl"),address1);
    }

    @Test
    public void findAllAddressesTest() {

        //given
        AddressManager addressManager=new AddressManager();
        System.out.println( addressManager.getAll());
    }

    @Test
    public void addMailTest()
    {
        //given
        String date=LocalDateTime.now().toString();
        AddressManager addressManager=new AddressManager();
        Address address=addressManager.getById(3);
        MailManager mailManager=new MailManager();
        Mail mail =new Mail("Temat2", date, "odebrana","kolejna", address);
        //when
        mailManager.add(mail);
    }
}
