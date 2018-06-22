package xmlweb.projekat.model.dtos;

/**
 * @author Nikola
 *
 */
public class ReservationDTO {

	private long id;
	private long user;
	private long accomodation;
	private int numberOfPersons;
	private long startDate;
	private long endDate;
	private int price;
	private int version;
	private Boolean realized;
	private Boolean reviewed;

	public ReservationDTO() {
	}

	public ReservationDTO(long id, long user, long accomodation, int numberOfPersons, long startDate, long endDate,
			int price, Boolean realized, Boolean reviewed, int version) {
		super();
		this.id = id;
		this.user = user;
		this.accomodation = accomodation;
		this.numberOfPersons = numberOfPersons;
		this.startDate = startDate;
		this.endDate = endDate;
		this.realized = realized;
		this.version = version;
		this.price = price;
		this.reviewed = reviewed;
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

	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
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

	public Boolean getRealized() {
		return realized;
	}

	public void setRealized(Boolean realized) {
		this.realized = realized;
	}
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Boolean getReviewed() {
		return reviewed;
	}

	public void setReviewed(Boolean reviewed) {
		this.reviewed = reviewed;
	}
}