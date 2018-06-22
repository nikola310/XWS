package xmlweb.agent.model.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import xmlweb.agent.model.UserType;

public class UserDTO {

	private long id;
	@Size(min = 1, max = 255)
	@NotNull
	private String firstName;
	@Size(min = 1, max = 255)
	private String lastName;
	@Size(min = 1, max = 255)
	@NotNull
	private String userName;
	@Size(min = 1, max = 255)
	@NotNull
	private String password;
	@NotNull
	private boolean active;
	private String pid;
	private UserType userType;
	private long agentLocation;
	private int version;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public long getAgentLocation() {
		return agentLocation;
	}

	public void setAgentLocation(long agentLocation) {
		this.agentLocation = agentLocation;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
