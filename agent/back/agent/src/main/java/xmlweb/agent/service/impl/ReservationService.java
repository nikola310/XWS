package xmlweb.agent.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xmlweb.agent.model.Reservation;
import xmlweb.agent.model.dtos.ReservationDTO;
import xmlweb.agent.repository.AccomodationRepository;
import xmlweb.agent.repository.ReservationRepository;
import xmlweb.agent.repository.UserRepository;
import xmlweb.agent.service.interfaces.ReservationServiceInterface;

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
	public List<ReservationDTO> findReservationsBetweenDates(long checkInDate, long checkOutDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> readAll() {
		return repository.findAll();
	}

	@Override
	public Reservation getById(Long id) {
		Optional<Reservation> r = repository.findById(id);
		if(r.isPresent())
			return r.get();
		else
			return null;
	}

	@Override
	public boolean Create(Reservation r) {
		try {
			repository.save(r);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean Update(Reservation r) {
		try {
			repository.save(r);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}


}
