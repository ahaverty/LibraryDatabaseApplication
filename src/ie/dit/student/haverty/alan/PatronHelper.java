package ie.dit.student.haverty.alan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Patron helper class
 * @author Alan
 *
 */
public class PatronHelper {

	private static String allPatrons = "SELECT * FROM borrowers";
	private static String payPatronDues = "UPDATE borrowers SET unpaid_dues = 0 WHERE card_no = ?";
	private static String getPatronDues = "SELECT unpaid_dues FROM borrowers WHERE card_no = ?";
	
	/**
	 * Get all patrons from the library, set the patron objects to a specific
	 * branch
	 * 
	 * @param branchId
	 * @return
	 */
	public static List<Patron> getPatrons(int branchId) {
		List<Patron> patrons = new ArrayList<Patron>();

		try {
			Connection connection = DatabaseHelper.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(allPatrons);
			ResultSet rs = pstmt.executeQuery();
			patrons = convertResultSetToPatrons(rs, branchId);
		} catch (SQLException e) {
			System.err.println("Error occured while retrieving all patrons.");
			System.err.println(e);
		}
		return patrons;
	}

	/**
	 * Set a patrons unpaid dues to 0
	 * 
	 * @param patronId
	 * @return
	 */
	public static int payDues(int patronId) {
		int rowsUpdated = 0;
		try {
			Connection connection = DatabaseHelper.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(payPatronDues);
			pstmt.setInt(1, patronId);
			rowsUpdated = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error occured while updating patron fine.");
			System.err.println(e);
		}
		return rowsUpdated;
	}
	
	/**
	 * Get all patrons from the library, set the patron objects to a specific
	 * branch
	 * 
	 * @param branchId
	 * @return
	 */
	public static Double getDues(int patronId) {
		try {
			Connection connection = DatabaseHelper.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(getPatronDues);
			pstmt.setInt(1, patronId);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			return rs.getDouble("unpaid_dues");
		} catch (SQLException e) {
			System.err.println("Error occured while retrieving all patrons.");
			System.err.println(e);
		}
		return (double) -1;
	}

	private static List<Patron> convertResultSetToPatrons(ResultSet rs, int branchId) throws SQLException {
		List<Patron> patrons = new ArrayList<Patron>();
		while (rs.next()) {
			final Patron patron = new Patron();
			patron.setId(rs.getInt("card_no"));
			patron.setName(rs.getString("borrower_name"));
			patron.setAddress(rs.getString("borrower_address"));
			patron.setPhone(rs.getString("borrower_phone"));
			patron.setUnpaidDues(rs.getDouble("unpaid_dues"));
			patron.setBranchId(branchId);
			patrons.add(patron);
		}
		return patrons;
	}

}
