package xmlweb.agent.model.dtos;

public class BonusServiceDTO {
	
	private long id;
	private String name;
	private int version;
	
	public BonusServiceDTO(long id, String name, int version) {
		super();
		this.id = id;
		this.name = name;
		this.version = version;
	}
	
	public BonusServiceDTO() {}
	
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

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
}
