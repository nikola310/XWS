package xmlweb.projekat.model.dtos;

/**
 * @author Nikola
 *
 */
public class AccomodationAdminDTO {

	private long id;
	private String accomodationName;
	private String accomodationType;
	private int capacity;
	private int category;
	private String location;
	private int version;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccomodationName() {
		return accomodationName;
	}

	public void setAccomodationName(String accomodationName) {
		this.accomodationName = accomodationName;
	}

	public String getAccomodationType() {
		return accomodationType;
	}

	public void setAccomodationType(String accomdoationType) {
		this.accomodationType = accomdoationType;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
