package xmlweb.projekat.model.dtos;

public class AccomodationBonusDTO {

	private long accomodationId;
	private long bonusId;
	
	public AccomodationBonusDTO() {}
	
	public AccomodationBonusDTO (long accomodationId, long bonusId) {
		this.accomodationId = accomodationId;
		this.bonusId = bonusId;
	}

	public long getAccomodationId() {
		return accomodationId;
	}

	public void setAccomodationId(long accomodationId) {
		this.accomodationId = accomodationId;
	}

	public long getBonusId() {
		return bonusId;
	}

	public void setBonusId(long bonusId) {
		this.bonusId = bonusId;
	}
	
}
