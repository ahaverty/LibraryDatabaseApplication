package ie.dit.student.haverty.alan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {

	private String servername;
	private String username;
	private String password;
	private String portnumber;
	private String url;

	public DatabaseHelper(String servername, String user, String pass, String portnumber) {
		this.servername = servername;
		this.username = user;
		this.password = pass;
		this.portnumber = portnumber;
		init();
	}

	private void init() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("driver loaded");
		} catch (ClassNotFoundException e) {
			System.err.println("Could not load the driver");
		}

		String sid = "xe";
		url = "jdbc:oracle:thin:@//" + servername + ":" + portnumber + "/" + sid;

		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException e) {
			System.err.println("Failed to register driver.");
		}
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
}