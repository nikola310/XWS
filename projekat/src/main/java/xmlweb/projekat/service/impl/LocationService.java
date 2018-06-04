package xmlweb.projekat.service.impl;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xmlweb.projekat.model.Location;
import xmlweb.projekat.model.dtos.LocationDTO;
import xmlweb.projekat.repository.LocationRepository;
import xmlweb.projekat.service.interfaces.LocationServiceInterface;

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
	public boolean Create(LocationDTO dto) {
		try {
			ModelMapper mapper = new ModelMapper();
			Location loc = mapper.map(dto, Location.class);
			repository.save(loc);

			return true;
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return false;
	}

	@Override
	public LocationDTO Read(long id) {
		try {
			Location loc = repository.getOne(id);
			ModelMapper mapper = new ModelMapper();
			LocationDTO dto = mapper.map(loc, LocationDTO.class);
			return dto;
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<LocationDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<Location> listEntities = (ArrayList<Location>) repository.findAll();
		ArrayList<LocationDTO> listDTO = new ArrayList<LocationDTO>();

		for (Location tmp : listEntities) {
			try {
				LocationDTO dto = mapper.map(tmp, LocationDTO.class);
				listDTO.add(dto);
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	@Override
	public boolean Update(LocationDTO dto) {
		Location toUpdate = repository.getOne(dto.getId());

		try {
			if (toUpdate.getVersion() != dto.getVersion()) {
				return false;
			}
			toUpdate.setCity(dto.getCity());
			toUpdate.setState(dto.getState());
			toUpdate.setStreetName(dto.getStreetName());
			toUpdate.setStreetNumber(dto.getStreetNumber());
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
