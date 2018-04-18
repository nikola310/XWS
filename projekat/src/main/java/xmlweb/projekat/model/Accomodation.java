package xmlweb.projekat.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Accomodation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "accomodation_id", nullable = false)
	private long id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="accomodation_type")
	private AccomodationType accomodationType;
	
	@Column(name = "category", nullable = false)
	private int category;
	
	@ManyToMany(mappedBy = "accomodations")
	private List<BonusService> bonusServices;
	
	@ManyToMany(mappedBy = "accomodation")
	private List<Comment> comments;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accomodation")
	private List<Price> prices;
	
	@Column(name = "capacity", nullable = false)
	private int capacity;
	
	public Accomodation() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AccomodationType getAccomodationType() {
		return accomodationType;
	}

	public void setAccomodationType(AccomodationType accomodationType) {
		this.accomodationType = accomodationType;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public List<BonusService> getBonusServices() {
		return bonusServices;
	}

	public void setBonusServices(List<BonusService> bonusServices) {
		this.bonusServices = bonusServices;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Price> getPrices() {
		return prices;
	}

	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
	
}
