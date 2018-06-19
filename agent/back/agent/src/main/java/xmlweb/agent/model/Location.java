package xmlweb.agent.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Location implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "location_id", nullable = false)
	private long id;
	
	@Column(name = "state", nullable = false)
	private String state;
	
	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "street_name", nullable = false)
	private String streetName;
	
	@Column(name = "street_number", nullable = false)
	private String streetNumber;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "location")
	@JsonIgnore
	private List<Accomodation> accomodations;
	
	@Version
	@Column(name = "entity_version", nullable = false)
	private int version;
	
	public Location() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public List<Accomodation> getAccomodations() {
		return accomodations;
	}

	public void setAccomodations(List<Accomodation> accomodations) {
		this.accomodations = accomodations;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
}
