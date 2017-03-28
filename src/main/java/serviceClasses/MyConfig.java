package serviceClasses;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class MyConfig {
   private DesiredCapabilities firefoxCapabilities = DesiredCapabilities.firefox();

   public DesiredCapabilities getFirefoxCapabilities() {
      return firefoxCapabilities;
   }

   public static Connection getSQLConnection() throws SQLException {
      String host = "localhost";
      String port = "3306";
      String database = "Usernames";
      Properties p = new Properties();

      p.setProperty("user", "evgenii");
      p.setProperty("password", "qolsys123");
      p.setProperty("verifyServerCertificate", "false");
      p.setProperty("useSSL", "false");
      p.setProperty("serverTimezone", "UTC");
      return DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, p);
   }
}