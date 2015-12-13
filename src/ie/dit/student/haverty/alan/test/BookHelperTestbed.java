package ie.dit.student.haverty.alan.test;

import java.sql.SQLException;
import java.util.List;

import ie.dit.student.haverty.alan.Book;
import ie.dit.student.haverty.alan.BookHelper;

public class BookHelperTestbed {

	public static void main(String args[]) {
		try {
			List<Book> books = BookHelper.getAvailableBooks(1);
			System.out.println(books);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
