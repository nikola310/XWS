package xmlweb.agent.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
public class Reservation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservation_id", nullable = false)
	private long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name = "number_of_persons", nullable = false)
	private int numberOfPersons;
	
	@Column(name = "start_date", nullable = false)
	private long startDate;
	
	@Column(name = "end_date", nullable = false)
	private long endDate;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="accomodation")
	private Accomodation accomodation;
	
	@Column(name="realized")
	private Boolean realized;
	
	@Version
	@Column(name = "entity_version", nullable = false)
	private int version;
	
	public Reservation() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Accomodation getAccomodation() {
		return accomodation;
	}

	public void setAccomodation(Accomodation accomodation) {
		this.accomodation = accomodation;
	}

	public Boolean getRealized() {
		return realized;
	}

	public void setRealized(Boolean realized) {
		this.realized = realized;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
}
