package xmlweb.agent.model.dtos;

public class AAAccomodationAgentDTO {
	
	private long id;
	private long accomodation;
	private long agent;
	private boolean mainAgent;
	
	public AAAccomodationAgentDTO() {}

	public AAAccomodationAgentDTO(long id, long accomodation, long agent, boolean mainAgent) {
		super();
		this.id = id;
		this.accomodation = accomodation;
		this.agent = agent;
		this.mainAgent = mainAgent;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAccomodation() {
		return accomodation;
	}

	public void setAccomodation(long accomodation) {
		this.accomodation = accomodation;
	}

	public long getAgent() {
		return agent;
	}

	public void setAgent(long agent) {
		this.agent = agent;
	}

	public boolean isMainAgent() {
		return mainAgent;
	}

	public void setMainAgent(boolean mainAgent) {
		this.mainAgent = mainAgent;
	}
}
