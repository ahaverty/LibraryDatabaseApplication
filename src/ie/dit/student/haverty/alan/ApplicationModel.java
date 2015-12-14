package ie.dit.student.haverty.alan;

import java.util.ArrayList;
import java.util.List;

public class ApplicationModel {

	private final static String initialTitle = "Select a Branch";
	
	private List<Branch> branches = new ArrayList<Branch>();
	private List<Patron> patrons = new ArrayList<Patron>();;
	private int selectedBranch = -1;
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
	
	public void selectBranch(int branchId) {
		selectedBranch = branchId;
	}
	
	public List<Patron> getPatrons() {
		return PatronHelper.getPatrons(selectedBranch);
	}
	
	public List<Book> getAllBooks() {
		return BookHelper.getBooks();
	}
	
}
