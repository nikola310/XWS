package xmlweb.projekat.model.dtos;

import java.util.List;

import xmlweb.projekat.model.AccomodationType;
import xmlweb.projekat.model.BonusService;
import xmlweb.projekat.model.Comment;
import xmlweb.projekat.model.Location;
import xmlweb.projekat.model.Price;

public class AccomodationDTO {
	
	private long id;
	private String name;
	private String type;
	private int category;
	private List<BonusService> bonusServices;
	private List<Comment> comments;
	private List<Price> prices;
	private int capacity;
	private Location location;
	
	
	public AccomodationDTO(long id, String name, AccomodationType type, int category, List<BonusService> bonusServices,
			List<Comment> comments, List<Price> prices, int capacity, Location location) {
		super();
		this.id = id;
		this.name = name;
		this.type = type.getName();
		this.category = category;
		this.bonusServices = bonusServices;
		this.comments = comments;
		this.prices = prices;
		this.capacity = capacity;
		this.location = location;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
