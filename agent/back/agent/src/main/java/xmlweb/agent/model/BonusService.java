package xmlweb.agent.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class BonusService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bonus_service_id", nullable = false)
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@ManyToMany
    @JoinTable(name = "accomodation_bonus_services",
    joinColumns = @JoinColumn(name = "bonus_service_id", nullable = false),
    inverseJoinColumns = @JoinColumn(name = "accomodation_id", nullable = false))
	@JsonIgnore
	private List<Accomodation> accomodations;
	
	@Version
	@Column(name = "entity_version", nullable = false)
	private int version;
	
	public BonusService() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
