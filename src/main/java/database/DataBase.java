package database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.*;

public class DataBase {

    private Connection connection;
    private Statement statement;
    private DatabaseMetaData databaseMetaData;


    public void createConnection( String dbName ) throws SQLException {

        connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
        if (connection != null) {
            statement = connection.createStatement();
            databaseMetaData = connection.getMetaData();
        }
    }

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mail-db");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();
}
