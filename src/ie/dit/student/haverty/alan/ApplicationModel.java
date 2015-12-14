package ie.dit.student.haverty.alan;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Model class for the application
 * @author Alan
 *
 */
public class ApplicationModel {

	private final static String initialTitle = "Select a Branch";
	DateTimeFormatter dtfOut = DateTimeFormat.forPattern("dd/MM/yyyy");
	
	private List<Branch> branches = new ArrayList<Branch>();
	private List<Patron> patrons = new ArrayList<Patron>();;
	private Branch selectedBranch;
	private Patron selectedPatron;
	private String pageTitle;
	
	ApplicationModel() {
		reset();
	}
	
	public void reset() {
		branches = BranchHelper.getBranches();
		patrons.clear();
		selectedBranch = null;
		selectedPatron = null;
		
		pageTitle = initialTitle;
	}
	
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	
	public String getPageTitle() {
		return pageTitle;
	}
	
	public List<Branch> getBranches() {
		return branches;
	}
	
	public void selectBranch(Branch branch) {
		selectedBranch = branch;
	}
	
	public Branch getSelectedBranch() {
		return selectedBranch;
	}
	
	public void setSelectedPatron(Patron patron) {
		this.selectedPatron = patron;
	}
	
	public Patron getSelectedPatron() {
		return this.selectedPatron;
	}
	
	public List<Patron> getPatrons() {
		return PatronHelper.getPatrons(selectedBranch.getId());
	}
	
	public List<Book> getAllBooks() {
		return BookHelper.getBooks();
	}
	
	public boolean insertBook(String title, String author, String publisher) {
		return BookHelper.insertNewBook(title, author, publisher);		
	}

	public boolean addBookCopy(Book book) {
		return BookHelper.addBookCopy(selectedBranch.getId(), book.getId());
	}

	public List<BookCopy> getPatronsAvailableBookCopies() {
		return selectedPatron.getBooksAvailable();
	}

	public List<BookLoan> getPatronsReturnableBookCopies() {
		return selectedPatron.getBooksCurrentlyOnLoan();
	}

	public String getPatronsDues() {
		return Double.toString(selectedPatron.getCurrentUnpaidDues());
	}

	public String getPatronsLoanHistory() {
		StringBuilder sb = new StringBuilder();
		for(BookLoan bookLoan : selectedPatron.getLoanHistory()){
			sb.append(bookLoan.getTitle().substring(0, Math.min(bookLoan.getTitle().length(), 20)));
			sb.append("\t");
			sb.append("out:");
			sb.append(dtfOut.print(bookLoan.getDateOut()));
			sb.append(" due:");
			sb.append(dtfOut.print(bookLoan.getDateDue()));
			sb.append(" ret:");
			sb.append(bookLoan.getDateReturned()==null ? "ON LOAN" : dtfOut.print(bookLoan.getDateReturned()));
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public boolean checkoutBook(BookCopy bookCopy) {
		return selectedPatron.checkoutBook(bookCopy.getCopyId());
	}

	public boolean returnBook(BookCopy bookCopy) {
		return selectedPatron.returnBookCopy(bookCopy.getCopyId());
	}
	
	public boolean clearFine() {
		return selectedPatron.payDues();
	}
	
}
