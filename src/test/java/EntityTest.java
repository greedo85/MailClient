import database.controller.MailAddressController;
import database.entities.Address;
import database.controller.AddressController;
import database.entities.Mail;
import database.controller.MailController;
import database.entities.MailAddress;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

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
        System.out.println(addressController.getByString("test@test.pl"));
        assertEquals(addressController.getByString("test@test.pl"),address1);
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
        Mail mail =new Mail("Temat2", date, "odebrana","testowa treść", address);
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
        Mail mail1= mailController.getById(3);
        Mail mail2= mailController.getById(4);
        MailAddress mailAddress1=new MailAddress(mail1,address2);
        MailAddress mailAddress2=new MailAddress(mail2,address1);
        MailAddressController mailAddressController=new MailAddressController();
        //when
        mailAddressController.add(mailAddress1);
        mailAddressController.add(mailAddress2);
        //then
        assertEquals(mailAddressController.getById(23).getAddress(),address2);
        assertEquals(mailAddressController.getById(23).getMail(),mail1);
    }

    @Test
    public void findMailByString()
    {
        //given
        MailController mailController=new MailController();
        String subject="Temat";

        //when
        List<Mail> mailList= mailController.getAll();
        Stream<Mail>mailStream=mailList.stream();
        List<Mail>findBySubject=mailStream.filter(m->m.getSubject().contains(subject)).collect(Collectors.toList());
        System.out.println(mailList);
        //then
        //assertEquals(3,mailList.size());
    }
}
