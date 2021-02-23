import database.entities.Address;
import database.entities.AddressManager;
import org.junit.Test;

public class EntityTest {

    @Test
    public void findAllTest() {

        //given
        AddressManager addressManager=new AddressManager();
        System.out.println( addressManager.getAll());
    }
}
