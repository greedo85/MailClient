import database.controller.MailAddressController;
import database.entities.Address;
import database.controller.AddressController;
import database.entities.Mail;
import database.controller.MailController;
import database.entities.MailAddress;
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
        AddressController addressController =new AddressController();
        addressController.add(address1);
        addressController.add(address2);
        //then
        System.out.println(addressController.getByMail("test@test.pl"));
        assertEquals(addressController.getByMail("test@test.pl"),address1);
    }

    @Test
    public void findAllAddressesTest() {

        //given
        AddressController addressController =new AddressController();
        System.out.println( addressController.getAll());
    }

    @Test
    public void addMailTest()
    {
        //given
        String date=LocalDateTime.now().toString();
        AddressController addressController =new AddressController();
        Address address= addressController.getById(3);
        MailController mailController =new MailController();
        Mail mail =new Mail("Temat2", date, "odebrana","kolejna", address);
        //when
        mailController.add(mail);
    }

    @Test
    public void mailAddressTest()
    {
        //given
        AddressController addressController =new AddressController();
        Address address1= addressController.getById(3);
        Address address2= addressController.getById(1);
        MailController mailController =new MailController();
        Mail mail= mailController.getById(3);
        MailAddress mailAddress=new MailAddress(mail,address1);
        MailAddressController mailAddressController=new MailAddressController();
        //when
        mailAddressController.add(mailAddress);

    }
}
