package xmlweb.projekat.model.dtos;

import javax.validation.constraints.NotNull;

/**
 * @author Nikola
 *
 */
public class BonusDTO {

	@NotNull
	private long id;
	@NotNull
	private String name;
	@NotNull
	private int version;

	public BonusDTO(long id, String name, int version) {
		super();
		this.id = id;
		this.name = name;
		this.version = version;
	}

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
