package dk.dsg.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseDAO extends Configuration {
    private SQLServerDataSource dataSource;


    public  DatabaseDAO() {
        dataSource = new SQLServerDataSource();
        dataSource.setDatabaseName(configValues.get("DATABASE_NAME"));
        dataSource.setUser(configValues.get("DATABASE_USER"));
        dataSource.setPassword(configValues.get("DATABASE_PASS"));
        dataSource.setServerName(configValues.get("DATABASE_SERVER"));
    }

    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }

    public static void main(String[] args) throws SQLException {

        DatabaseDAO databaseConnector = new DatabaseDAO();
        Connection connection = databaseConnector.getConnection();

        System.out.println("Is it open? " + !connection.isClosed());
        connection.close();
    }
}
