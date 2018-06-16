package xmlweb.projekat.model.dtos;

import javax.validation.constraints.NotNull;

/**
 * @author Nikola
 *
 */
public class AgentRequestDTO {

	@NotNull
	private boolean accept;
	private String pid;
	private long address;

	public boolean isAccept() {
		return accept;
	}

	public void setAccept(boolean accept) {
		this.accept = accept;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public long getAddress() {
		return address;
	}

	public void setAddress(long address) {
		this.address = address;
	}

	public AgentRequestDTO() {
	}

}
