package ie.dit.student.haverty.alan;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Patron {

	private int id;
	private String name;
	private String address;
	private String phone;
	private Double unpaidDues;
	private int branchId;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public Double getUnpaidDues() {
		return unpaidDues;
	}

	public int getBranchId() {
		return branchId;
	}

	void setId(int id) {
		this.id = id;
	}

	void setName(String name) {
		this.name = name;
	}

	void setAddress(String address) {
		this.address = address;
	}

	void setPhone(String phone) {
		this.phone = phone;
	}

	void setUnpaidDues(Double unpaidDues) {
		this.unpaidDues = unpaidDues;
	}

	void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	/**
	 * Get the books currently on loan by this patron
	 * 
	 * @return
	 */
	public List<BookLoan> getBooksCurrentlyOnLoan() {
		return BookHelper.getPatronsCurrentlyLoanedBooks(id);
	}

	/**
	 * Gets all books loaned by a patron (previous and still on loan)
	 * 
	 * @return
	 */
	public List<BookLoan> getLoanHistory() {
		return BookHelper.getPatronLoans(id);
	}

	/**
	 * Return the books currently available to the patron at the branch
	 * specified
	 * 
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

	/**
	 * Pay any dues for this patron.
	 * 
	 * @return True if dues payed correctly, false otherwise
	 */
	public boolean payDues() {
		return PatronHelper.payDues(id) == 1 ? true : false;
	}
	
	/**
	 * Return a bookCopy with current sysdate.
	 * @param bookCopyId
	 * @return True if return successful, False otherwise
	 */
	public boolean returnBookCopy(int bookCopyId) {
		return BookHelper.returnLoanedBook(id, bookCopyId) > 1 ? true : false;
	}
	
	/**
	 * Checkout a bookcopy for this patron
	 * @param bookCopyId
	 * @return True if the checkout was successful
	 */
	public boolean checkoutBook(int bookCopyId) {
		return BookHelper.loanBook(id, bookCopyId) == 1 ? true : false;
	}

}
