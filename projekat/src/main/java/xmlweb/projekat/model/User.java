package xmlweb.projekat.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false)
	private long id;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "user_name", nullable = false)
	private String userName;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	//poslovni maticni broj
	@Column(name = "pid", nullable = false)
	private String pid;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private UserType userType;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reciever")
	private List<Message> recievedMessages;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sender")
	private List<Message> sentMessages;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
	private List<Comment> comments;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Reservation> reservations;
	
	@OneToMany(mappedBy = "agent")
	private List<AccomodationAgent> accomodationAgent;
	
	@Column(name = "entity_version", nullable = false)
	private int version;
	
	public User() {}

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

	public List<Message> getRecievedMessages() {
		return recievedMessages;
	}

	public void setRecievedMessages(List<Message> recievedMessages) {
		this.recievedMessages = recievedMessages;
	}

	public List<Message> getSentMessages() {
		return sentMessages;
	}

	public void setSentMessages(List<Message> sentMessages) {
		this.sentMessages = sentMessages;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public List<AccomodationAgent> getAccomodationAgent() {
		return accomodationAgent;
	}

	public void setAccomodationAgent(List<AccomodationAgent> accomodationAgent) {
		this.accomodationAgent = accomodationAgent;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	};
	
	
	
}
