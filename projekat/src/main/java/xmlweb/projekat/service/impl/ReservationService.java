package xmlweb.projekat.service.impl;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xmlweb.projekat.model.Reservation;
import xmlweb.projekat.model.dtos.ReservationDTO;
import xmlweb.projekat.repository.ReservationRepository;
import xmlweb.projekat.service.interfaces.ReservationServiceInterface;

/**
 * @author Nikola
 *
 */
@Service
@Transactional
public class ReservationService implements ReservationServiceInterface {

	private ReservationRepository repository;

	@Autowired
	public ReservationService(ReservationRepository repository) {
		this.repository = repository;
	}

	@Override
	public boolean Create(ReservationDTO dto) {
		try {
			ModelMapper mapper = new ModelMapper();
			Reservation res = mapper.map(dto, Reservation.class);
			repository.save(res);

			return true;
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return false;
	}

	@Override
	public ReservationDTO Read(long id) {
		try {
			Reservation res = repository.getOne(id);
			ModelMapper mapper = new ModelMapper();
			ReservationDTO dto = mapper.map(res, ReservationDTO.class);
			return dto;
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<ReservationDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<Reservation> listEntities = (ArrayList<Reservation>) repository.findAll();
		ArrayList<ReservationDTO> listDTO = new ArrayList<ReservationDTO>();

		for (Reservation tmp : listEntities) {
			try {
				ReservationDTO dto = mapper.map(tmp, ReservationDTO.class);
				listDTO.add(dto);
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	@Override
	public boolean Update(ReservationDTO dto) {
		Reservation toUpdate = repository.getOne(dto.getId());

		try {
			if (toUpdate.getVersion() != dto.getVersion()) {
				return false;
			}
			
			toUpdate.setNumberOfPersons(dto.getNumberOfPersons());
			toUpdate.setStartDate(dto.getStartDate());
			toUpdate.setEndDate(dto.getEndDate());
			
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
