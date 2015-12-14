package ie.dit.student.haverty.alan;

import java.util.ArrayList;
import java.util.List;

public class ApplicationModel {

	private final static String initialTitle = "Select a Branch";
	
	private List<Branch> branches = new ArrayList<Branch>();
	private List<Patron> patrons = new ArrayList<Patron>();;
	private Branch selectedBranch;
	private String pageTitle;
	
	ApplicationModel() {
		reset();
	}
	
	public void reset() {
		branches = BranchHelper.getBranches();
		patrons.clear();
		
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
	
	public List<Patron> getPatrons() {
		return PatronHelper.getPatrons(selectedBranch.getId());
	}
	
	public List<Book> getAllBooks() {
		return BookHelper.getBooks();
	}
	
	public boolean insertBook(String title, String author, String publisher) {
		return BookHelper.insertNewBook(title, author, publisher);		
	}
	
}
