package xmlweb.projekat.model.dtos;

public class MessageDTO {

	private long id;
	private long sender;
	private long receiver;
	private String content;
	private int version;
	
	public MessageDTO() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSender() {
		return sender;
	}

	public void setSender(long sender) {
		this.sender = sender;
	}

	public long getReceiver() {
		return receiver;
	}

	public void setReceiver(long receiver) {
		this.receiver = receiver;
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
