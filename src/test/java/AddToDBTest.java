import database.entities.Address;
import database.entities.AddressManager;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddToDBTest {

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
        AddressManager addressManager1=new AddressManager();
        assertEquals(addressManager1.getByMail("test@test.pl"),address1);
    }
}
