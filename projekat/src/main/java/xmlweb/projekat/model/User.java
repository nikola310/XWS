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
	
	
}
