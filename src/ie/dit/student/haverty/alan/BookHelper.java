package ie.dit.student.haverty.alan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookHelper {

	private static String availableBookCopiesGrouped = "SELECT book_id, title, publisher_name, author_name FROM books JOIN publishers USING(publisher_id) JOIN authors USING(author_id) WHERE book_id in (SELECT book_id FROM book_copies WHERE book_copy_id NOT IN (SELECT book_copy_id FROM book_loans WHERE date_returned IS null) AND branch_id = ? ) ORDER BY book_id";
	
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
			while (rs.next()) {
				final Book book = new Book();
				book.setId(rs.getInt("book_id"));
				book.setTitle(rs.getString("title"));
				book.setAuthorName(rs.getString("author_name"));
				book.setPublisherName(rs.getString("publisher_name"));
				books.add(book);
			}
		} catch(SQLException e) {
			System.err.println("Error occured while selecting available books for branch.");
			System.err.println(e);
		}
		return books;
	}

}
