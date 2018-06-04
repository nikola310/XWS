package xmlweb.projekat.model.dtos;

public class PictureDTO {

	private long id;
	private long accomodation;
	private String content;
	private int version;

	public PictureDTO() {
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
