package xmlweb.agent.service.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xmlweb.agent.model.Location;
import xmlweb.agent.repository.LocationRepository;
import xmlweb.agent.service.interfaces.LocationServiceInterface;

@Service
@Transactional
public class LocationService implements LocationServiceInterface {

	private LocationRepository repository;

	@Autowired
	public LocationService(LocationRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public boolean Create(Location loc) {
		try {
			repository.save(loc);
			return true;
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return false;
	}

	@Override
	public Location Read(long id) {
		Optional<Location> loc = (Optional<Location>) repository.findById(id);
		if(loc.isPresent())
			return loc.get();
		else
			return null;
	}

	@Override
	public ArrayList<Location> ReadAll() {
		ArrayList<Location> listEntities = (ArrayList<Location>) repository.findAll();
		return listEntities;
	}

	@Override
	public boolean Update(Location loc) {
		Location toUpdate = repository.getOne(loc.getId());

		try {
			if (toUpdate.getVersion() != loc.getVersion()) {
				return false;
			}
			toUpdate.setCity(loc.getCity());
			toUpdate.setState(loc.getState());
			toUpdate.setStreetName(loc.getStreetName());
			toUpdate.setStreetNumber(loc.getStreetNumber());
			repository.save(toUpdate);

		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean Delete(long id) {
		try {
			repository.deleteById(id);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}

		return true;
	}

}
