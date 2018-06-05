package xmlweb.projekat.model.dtos;

import java.util.Date;

/**
 * @author Nikola
 *
 */
public class ReservationDTO {

	private long id;
	private long user;
	private long accomodation;
	private int numberOfPersons;
	private Date startDate;
	private Date endDate;
	private int version;

	public ReservationDTO() {
	}

	public ReservationDTO(long id, long user, long accomodation, int numberOfPersons, Date startDate, Date endDate,
			int version) {
		super();
		this.id = id;
		this.user = user;
		this.accomodation = accomodation;
		this.numberOfPersons = numberOfPersons;
		this.startDate = startDate;
		this.endDate = endDate;
		this.version = version;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumberOfPersons() {
		return numberOfPersons;
	}

	public void setNumberOfPersons(int numberOfPersons) {
		this.numberOfPersons = numberOfPersons;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public long getUser() {
		return user;
	}

	public void setUser(long user) {
		this.user = user;
	}

	public long getAccomodation() {
		return accomodation;
	}

	public void setAccomodation(long accomodation) {
		this.accomodation = accomodation;
	}

}
