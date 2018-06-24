package xmlweb.agent.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xmlweb.agent.model.Accomodation;
import xmlweb.agent.model.BonusService;
import xmlweb.agent.repository.AccomodationRepository;
import xmlweb.agent.repository.BonusServiceRepository;
import xmlweb.agent.service.interfaces.AccomodationBonusServiceServiceInterface;

@Service
public class AccomodationBonusServiceService implements AccomodationBonusServiceServiceInterface {

	@Autowired
	private AccomodationRepository accomodationRepo;
	
	@Autowired
	private BonusServiceRepository bonusRepo;
	
	@Override
	public boolean Create(long accId, long bonusId) {
		try {
			Accomodation a = accomodationRepo.getOne(accId);
			a.getBonusServices().add(bonusRepo.getOne(bonusId));
			accomodationRepo.save(a);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean Update(long accId, long bonusId) {
		try {
			if(accomodationRepo.getOne(accId) == null)
				return false;
			Accomodation a = accomodationRepo.getOne(accId);
			a.getBonusServices().add(bonusRepo.getOne(bonusId));
			accomodationRepo.save(a);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean Delete(long accId, long bonusId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public HashMap<Long, ArrayList<Long>> findAll() {
		HashMap<Long, ArrayList<Long>> ret = new HashMap<Long, ArrayList<Long>>(); 

		List<Accomodation> ac = accomodationRepo.findAll();
		for(Accomodation a : ac) {
			List<BonusService> b = a.getBonusServices();
			ArrayList<Long> bkeys = new ArrayList<Long>();
			for(BonusService bb : b)
				bkeys.add(bb.getId());
			
			ret.put(a.getId(), bkeys);
		}
		
		return ret;
	}

	
	
}
