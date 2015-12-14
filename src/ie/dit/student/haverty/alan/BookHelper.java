package ie.dit.student.haverty.alan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookHelper {

	private static String getAllBooks = "SELECT book_id, title, publisher_name, author_name FROM books JOIN publishers USING(publisher_id) JOIN authors USING(author_id)";
	private static String availableBookCopiesGrouped = "SELECT book_id, title, publisher_name, author_name FROM books JOIN publishers USING(publisher_id) JOIN authors USING(author_id) WHERE book_id in (SELECT book_id FROM book_copies WHERE book_copy_id NOT IN (SELECT book_copy_id FROM book_loans WHERE date_returned IS null) AND branch_id = ? ) ORDER BY book_id";
	private static String patronLoans = "SELECT bl.card_no, bl.date_out, bl.date_due, bl.date_returned, bc.book_copy_id, bc.branch_id, b.book_id, b.title, p.publisher_name, a.author_name FROM book_loans bl JOIN book_copies bc ON bl.book_copy_id = bc.book_copy_id JOIN branches br ON br.branch_id = bc.branch_id JOIN books b ON b.book_id = bc.book_id JOIN authors a ON a.author_id = b.author_id JOIN publishers p ON p.publisher_id = b.publisher_id WHERE card_no = ?";
	private static String returnLoanedBook = "UPDATE book_loans SET date_returned = SYSDATE WHERE book_copy_id = ? AND card_no = ?";
	private static String loanBook = "INSERT INTO book_loans VALUES (?, ?, SYSDATE, SYSDATE + 14, null)";
	private static String insertNewBook = "BEGIN InsertNewBook(?, ?, ?); END;";
	
	/**
	 * Get all books from library
	 * @return
	 */
	public static List<Book> getBooks() {
		List<Book> books = new ArrayList<Book>();

		try {
			Connection connection = DatabaseHelper.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(getAllBooks);
			ResultSet rs = pstmt.executeQuery();
			books = convertResultSetToBooks(rs);
		} catch(SQLException e) {
			System.err.println("Error occured while selecting all books.");
			System.err.println(e);
		}
		return books;
	}
	
	/**
	 * Retrieve all books available to rent from a branch (ie. copies available
	 * that are not currently on loan)
	 * 
	 * @param branchId
	 * @return
	 * @throws SQLException
	 */
	public static List<Book> getAvailableBooks(int branchId) throws SQLException {
		List<Book> books = new ArrayList<Book>();

		try {
			Connection connection = DatabaseHelper.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(availableBookCopiesGrouped);
			pstmt.setInt(1, branchId);
			ResultSet rs = pstmt.executeQuery();
			books = convertResultSetToBooks(rs);
		} catch(SQLException e) {
			System.err.println("Error occured while selecting available books for branch.");
			System.err.println(e);
		}
		return books;
	}
	
	/**
	 * Retrieve all book copies that a patron has loaned
	 * @param patronId
	 * @return
	 */
	public static List<BookLoan> getPatronLoans(int patronId) {
		List<BookLoan> bookLoans = new ArrayList<BookLoan>();
		try {
			Connection connection = DatabaseHelper.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(patronLoans);
			pstmt.setInt(1, patronId);
			ResultSet rs = pstmt.executeQuery();
			bookLoans = convertResultSetToBookLoans(rs);
		} catch(SQLException e) {
			System.err.println("Error occured while retrieving patrons list of books on loan.");
			System.err.println(e);
		}
		return bookLoans;
	}
	
	/**
	 * Returns all books currently on loan by a patron
	 * @param patronId
	 * @return
	 */
	public static List<BookLoan> getPatronsCurrentlyLoanedBooks(int patronId) {
		List<BookLoan> currentlyLoaned = new ArrayList<BookLoan>();
		for(BookLoan bookLoan : getPatronLoans(1)) {
			if (!bookLoan.isReturned()) {
				currentlyLoaned.add(bookLoan);
			}
		}
		return currentlyLoaned;
	}
	
	public static boolean insertNewBook(String title, String author, String publisher) {
		try {
			Connection connection = DatabaseHelper.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(insertNewBook);
			pstmt.setString(1, title);
			pstmt.setString(2, author);
			pstmt.setString(3, publisher);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error occured while inserting new book.");
			System.err.println(e);
			return false;
		}
		return true;
	}
	
	public static int loanBook(int patronId, int bookCopyId) {
		int rowsInserted = 0;
		try {
			Connection connection = DatabaseHelper.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(loanBook);
			pstmt.setInt(1, bookCopyId);
			pstmt.setInt(2, patronId);
			rowsInserted = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error occured while inserting book loan record.");
			System.err.println(e);
		}
		return rowsInserted;
	}
	
	/**
	 * Return a book copy for a patron by setting the return date to the current systime
	 * @param patronId
	 * @param bookCopyId
	 * @return
	 */
	public static int returnLoanedBook(int patronId, int bookCopyId) {
		int rowsUpdated = 0;
		try {
			Connection connection = DatabaseHelper.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(returnLoanedBook);
			pstmt.setInt(1, bookCopyId);
			pstmt.setInt(2, patronId);
			rowsUpdated = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error occured while updating patron fine.");
			System.err.println(e);
		}
		return rowsUpdated;
	}
	
	/**
	 * Convert a result set to a list of Book
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private static List<Book> convertResultSetToBooks(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<Book>();
		while (rs.next()) {
			final Book book = new Book();
			book.setId(rs.getInt("book_id"));
			book.setTitle(rs.getString("title"));
			book.setAuthorName(rs.getString("author_name"));
			book.setPublisherName(rs.getString("publisher_name"));
			books.add(book);
		}
		return books;
	}
	
	/**
	 * Convert a result set to a list of BookCopy
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unused")
	private static List<BookCopy> convertResultSetToBookCopies(ResultSet rs) throws SQLException {
		List<BookCopy> bookCopies = new ArrayList<BookCopy>();
		while (rs.next()) {
			final BookCopy bookCopy = new BookCopy();
			bookCopy.setCopyId(rs.getInt("book_copy_id"));
			bookCopy.setBranchId(rs.getInt("branch_id"));
			bookCopy.setId(rs.getInt("book_id"));
			bookCopy.setTitle(rs.getString("title"));
			bookCopy.setAuthorName(rs.getString("author_name"));
			bookCopy.setPublisherName(rs.getString("publisher_name"));
			bookCopies.add(bookCopy);
		}
		return bookCopies;
	}
	
	/**
	 * Convert a result set to a list of BookLoan
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private static List<BookLoan> convertResultSetToBookLoans(ResultSet rs) throws SQLException {
		List<BookLoan> bookLoans = new ArrayList<BookLoan>();
		while (rs.next()) {
			final BookLoan bookLoan = new BookLoan();
			bookLoan.setPatronId(rs.getInt("card_no"));
			bookLoan.setDateDue(rs.getDate("date_due"));
			bookLoan.setDateOut(rs.getDate("date_out"));
			bookLoan.setDateReturned(rs.getDate("date_returned"));
			bookLoan.setCopyId(rs.getInt("book_copy_id"));
			bookLoan.setBranchId(rs.getInt("branch_id"));
			bookLoan.setId(rs.getInt("book_id"));
			bookLoan.setTitle(rs.getString("title"));
			bookLoan.setAuthorName(rs.getString("author_name"));
			bookLoan.setPublisherName(rs.getString("publisher_name"));
			bookLoans.add(bookLoan);
		}
		return bookLoans;
	}

	

}
