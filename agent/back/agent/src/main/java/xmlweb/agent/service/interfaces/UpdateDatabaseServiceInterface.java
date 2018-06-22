package xmlweb.agent.service.interfaces;

public interface UpdateDatabaseServiceInterface {

	public void SyncDB();
	
	public void UpdateLocations();
	public void UpdateMessages();
	public void UpdateUsers();
	public void UpdatePictures();
	public void UpdatePrices();
	public void UpdateReservations();
	public void UpdateAccomodationTypes();
	public void UpdateBonusServices();
	public void UpdateAccomodations();
	public void UpdateAccomodationBonusServices();
	public void UpdateAccomodationAgents();
	public void UpdateComments();
	
}
