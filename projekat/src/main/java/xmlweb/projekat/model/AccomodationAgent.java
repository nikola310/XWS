package xmlweb.projekat.model;

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
public class AccomodationAgent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "accomodation_agent_id", nullable = false)
	private long id;

	@ManyToOne
    @JoinColumn(name = "accomodation_id")
	private Accomodation accomodation;

	@ManyToOne
    @JoinColumn(name = "agent_id")
	private User agent;
	
	@Column(name = "main_agent", nullable = false)
	private boolean mainAgent;
	
	@Version
	@Column(name = "entity_version", nullable = false)
	private int version;
	
	public AccomodationAgent() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Accomodation getAccomodation() {
		return accomodation;
	}

	public void setAccomodation(Accomodation accomodation) {
		this.accomodation = accomodation;
	}

	public User getAgent() {
		return agent;
	}

	public void setAgent(User agent) {
		this.agent = agent;
	}

	public boolean isMainAgent() {
		return mainAgent;
	}

	public void setMainAgent(boolean mainAgent) {
		this.mainAgent = mainAgent;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	};
	
	
}
