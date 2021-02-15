import database.DataBase;
import org.junit.Test;

import java.io.File;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class CreateDBTest {
    @Test
    public void createTest() throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        //given
        DataBase dataBase=new DataBase();
        //when
        dataBase.createConnection("mail-db.db");
        File file=new File("mail-db.db");
        //then
        assertTrue(file.exists());
    }
}
