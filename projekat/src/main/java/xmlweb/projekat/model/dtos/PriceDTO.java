package xmlweb.projekat.model.dtos;

import java.util.Date;

public class PriceDTO {

	private long id;
	private int price;
	private Date startDate;
	private Date endDate;
	private long accomodation;
	private int version;

	public PriceDTO() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public long getAccomodation() {
		return accomodation;
	}

	public void setAccomodation(long accomodation) {
		this.accomodation = accomodation;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
