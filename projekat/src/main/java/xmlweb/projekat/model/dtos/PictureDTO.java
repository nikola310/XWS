package xmlweb.projekat.model.dtos;

public class PictureDTO {

	private long id;
	private long accomodation;
	private byte[] content;
	private int version;

	public PictureDTO() {
	}

	public PictureDTO(long id, long accomodation, byte[] content, int version) {
		super();
		this.id = id;
		this.accomodation = accomodation;
		this.content = content;
		this.version = version;
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

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
