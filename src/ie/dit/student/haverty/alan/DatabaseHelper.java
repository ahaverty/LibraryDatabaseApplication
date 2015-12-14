package ie.dit.student.haverty.alan;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Database helper class for static connections and accessing the properties file
 * @author Alan
 *
 */
public class DatabaseHelper {

	public static Connection getConnection() throws SQLException {
		Properties prop = new Properties();
		InputStream inStream = null;
		try {
			String propertiesPath = System.getProperty("propertiesFilepath");
			inStream = new FileInputStream(propertiesPath);
		} catch (FileNotFoundException e2) {
			System.err.println(
					"Unable to find properties file... Set with -DpropertiesFilepath=/path/to/application.properties");
		}
		try {
			prop.load(inStream);
		} catch (IOException e1) {
			System.err.println("Unable to find application.properties file");
		}

		String servername = prop.getProperty("servername");
		String portnumber = prop.getProperty("portnumber");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.err.println("Could not load the driver");
		}

		String sid = "xe";
		String url = "jdbc:oracle:thin:@//" + servername + ":" + portnumber + "/" + sid;

		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e) {
			System.err.println("Failed to register driver.");
		}

		return DriverManager.getConnection(url, username, password);
	}
}