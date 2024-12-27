package Connect_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Connect {
    private Connection connection;


   public Connect() throws SQLException {
         connection = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5433/databaseofathletes",
                "postgres","12345"
        );

   }

   public Connection getConnection() {
       return connection;
   }

   public void close() throws SQLException {
      if (connection != null && !connection.isClosed()) {
          connection.close();
      }

   }




}
