package ie.dit.student.haverty.alan;

import java.util.Date;

import org.joda.time.DateTime;

/**
 * Book loan pojo class
 * @author Alan
 *
 */
public class BookLoan extends BookCopy {

	private int patronId;
	private DateTime dateOut;
	private DateTime dateReturned;
	private DateTime dateDue;
	private boolean returned;

	public int getPatronId() {
		return patronId;
	}

	public void setPatronId(int patronId) {
		this.patronId = patronId;
	}

	public DateTime getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut != null ? new DateTime(dateOut) : null;
	}

	public DateTime getDateReturned() {
		return dateReturned;
	}

	public void setDateReturned(Date dateReturned) {
		this.dateReturned = dateReturned != null ? new DateTime(dateReturned) : null;
		this.returned = dateReturned != null ? true : false;
	}

	public DateTime getDateDue() {
		return dateDue;
	}

	public void setDateDue(Date dateDue) {
		this.dateDue = dateDue != null ? new DateTime(dateDue) : null;
	}
	
	public boolean isReturned() {
		return returned;
	}

	@Override
	public String toString() {
		return "BookLoan [patronId=" + patronId + ", dateOut=" + dateOut + ", dateReturned=" + dateReturned
				+ ", dateDue=" + dateDue + ", toString()=" + super.toString() + "]";
	}
}