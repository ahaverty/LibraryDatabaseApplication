package ie.dit.student.haverty.alan;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Patron {
	
	private int patronId;
	private int branchId;
	
	public Patron(int patronId, int branchId) {
		this.patronId = patronId;
		this.branchId = branchId;
	}
	
	/**
	 * Get the books currently on loan by this patron
	 * @return
	 */
	public List<BookLoan> getBooksCurrentlyOnLoan() {
		return BookHelper.getPatronsCurrentlyLoanedBooks(patronId);
	}
	
	public List<BookLoan> getLoanHistory() {
		return BookHelper.getPatronLoans(patronId);
	}
	
	/**
	 * Return the books currently available to the patron at the branch specified
	 * @return
	 */
	public List<Book> getBooksAvailable() {
		List<Book> books = new ArrayList<Book>();
		try {
			books = BookHelper.getAvailableBooks(branchId);
		} catch (SQLException e) {
			System.err.println("Error while trying to get books available to patron");
		}
		return books;
	}
	
}
