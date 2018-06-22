package xmlweb.projekat.model.dtos;

import javax.validation.constraints.NotNull;

/**
 * @author Nikola
 *
 */
public class CategoryDTO {

	@NotNull
	private long id;
	@NotNull
	private int version;
	@NotNull
	private int category;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}
}
