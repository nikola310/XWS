package xmlweb.projekat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xmlweb.projekat.model.Accomodation;
import xmlweb.projekat.model.Reservation;
import xmlweb.projekat.model.User;
import xmlweb.projekat.model.dtos.ReservationDTO;
import xmlweb.projekat.repository.AccomodationRepository;
import xmlweb.projekat.repository.ReservationRepository;
import xmlweb.projekat.repository.UserRepository;
import xmlweb.projekat.service.interfaces.ReservationServiceInterface;

/**
 * @author Nikola
 *
 */
@Service
@Transactional
public class ReservationService implements ReservationServiceInterface {

	private ReservationRepository repository;

	private UserRepository userRepo;

	private AccomodationRepository accRepo;

	@Autowired
	public ReservationService(ReservationRepository repository, UserRepository userRepo,
			AccomodationRepository accRepo) {
		this.repository = repository;
		this.userRepo = userRepo;
		this.accRepo = accRepo;
	}

	@Override
	public boolean Create(ReservationDTO dto) {
		try {
			ModelMapper mapper = new ModelMapper();
			Reservation res = mapper.map(dto, Reservation.class);
			User u = userRepo.getOne(dto.getUser());
			Accomodation acc = accRepo.getOne(dto.getAccomodation());
			res.setUser(u);
			res.setAccomodation(acc);
			res.setRealized(null);
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
			ReservationDTO dto = new ReservationDTO(res.getId(), res.getUser().getId(), res.getAccomodation().getId(),
					res.getNumberOfPersons(), res.getStartDate(), res.getEndDate(), res.getRealized(),
					res.getVersion());
			return dto;
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<ReservationDTO> ReadAll() {
		ArrayList<Reservation> listEntities = (ArrayList<Reservation>) repository.findAll();
		ArrayList<ReservationDTO> listDTO = new ArrayList<ReservationDTO>();

		for (Reservation res : listEntities) {
			try {
				ReservationDTO dto = new ReservationDTO(res.getId(), res.getUser().getId(),
						res.getAccomodation().getId(), res.getNumberOfPersons(), res.getStartDate(), res.getEndDate(),
						res.getRealized(), res.getVersion());
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
			toUpdate.setRealized(dto.getRealized());

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

	@Override
	public List<ReservationDTO> findReservationsBetweenDates(long checkInDate, long checkOutDate) {
		List<Reservation> reservations = repository.findReservationsBetweenDates(checkInDate, checkOutDate);
		List<ReservationDTO> reservationsDTO = new ArrayList<ReservationDTO>();

		for (Reservation r : reservations) {
			ReservationDTO rdto = new ReservationDTO(r.getId(), r.getUser().getId(), r.getAccomodation().getId(),
					r.getNumberOfPersons(), r.getStartDate(), r.getEndDate(), r.getRealized(), r.getVersion());
			reservationsDTO.add(rdto);
		}

		return reservationsDTO;
	}

}
