package xmlweb.agent.model.dtos;


import java.util.List;

import xmlweb.agent.model.AccomodationType;
import xmlweb.agent.model.BonusService;
import xmlweb.agent.model.Comment;
import xmlweb.agent.model.Location;
import xmlweb.agent.model.Price;

public class AAAccomodationDTO {
	
	private long id;
	private String name;
	private long type;
	private int category;
	private List<BonusService> bonusServices;
	private List<Comment> comments;
	private List<Price> prices;
	private int capacity;
	private long location;
	private long version;
	
	public AAAccomodationDTO(){
		
	}
	public AAAccomodationDTO(long id, String name, long type, int category, List<BonusService> bonusServices,
			List<Comment> comments, List<Price> prices, int capacity, long location) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
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
	public long getType() {
		return type;
	}
	public void setType(long type) {
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
	public long getLocation() {
		return location;
	}
	public void setLocation(long location) {
		this.location = location;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	
}
