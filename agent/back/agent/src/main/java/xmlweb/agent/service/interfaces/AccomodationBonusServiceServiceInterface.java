package xmlweb.agent.service.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

public interface AccomodationBonusServiceServiceInterface {

	boolean Create(long accId, long bonusId);
	boolean Update(long accId, long bonusId);
	boolean Delete(long accId, long bonusId);
	HashMap<Long, ArrayList<Long>> findAll();
	
}
