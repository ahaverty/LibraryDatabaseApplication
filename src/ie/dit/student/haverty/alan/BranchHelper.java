package ie.dit.student.haverty.alan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchHelper {

	private static String getBranches = "SELECT * FROM branches";


	/**
	 * Returns all branches
	 * @return
	 */
	public static List<Branch> getBranches() {
		List<Branch> branches = new ArrayList<Branch>();

		try {
			Connection connection = DatabaseHelper.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(getBranches);
			ResultSet rs = pstmt.executeQuery();
			branches = convertResultSetToBranches(rs);
		} catch (SQLException e) {
			System.err.println("Error occured while retrieving all branches.");
			System.err.println(e);
		}
		return branches;
	}

	
	/**
	 * Convert a result set to a list of branches
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private static List<Branch> convertResultSetToBranches(ResultSet rs) throws SQLException {
		List<Branch> branches = new ArrayList<Branch>();
		while (rs.next()) {
			final Branch branch = new Branch();
			branch.setId(rs.getInt("branch_id"));
			branch.setAddress(rs.getString("branch_address"));
			branch.setName(rs.getString("branch_address"));
			branches.add(branch);
		}
		return branches;
	}

}
