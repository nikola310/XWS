package xmlweb.projekat.service.interfaces;

import java.util.List;

import xmlweb.projekat.model.AccomodationType;

public interface AccomodationTypeServiceInterface {
	
	List<AccomodationType> findAllAccomodationTypes();
	
	public boolean Create(String  name);

	public boolean Update(AccomodationType acc);

	public boolean Delete(long id);
}
