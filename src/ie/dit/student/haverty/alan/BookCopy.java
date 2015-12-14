package ie.dit.student.haverty.alan;

/**
 * Book copy pojo class inheriting from the book class
 * @author Alan
 *
 */
public class BookCopy extends Book {

	private int copyId;
	private int branchId;
	
	public int getCopyId() {
		return copyId;
	}
	public void setCopyId(int copyId) {
		this.copyId = copyId;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	@Override
	public String toString() {
		return "BookCopy [copyId=" + copyId + ", branchId=" + branchId + ", toString()=" + super.toString() + "]";
	}
}
