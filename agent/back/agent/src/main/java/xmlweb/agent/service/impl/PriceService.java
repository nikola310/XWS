package xmlweb.agent.service.impl;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xmlweb.agent.model.Accomodation;
import xmlweb.agent.model.Price;
import xmlweb.agent.model.dtos.PriceDTO;
import xmlweb.agent.repository.AccomodationRepository;
import xmlweb.agent.repository.PriceRepository;
import xmlweb.agent.service.interfaces.PriceServiceInterface;

@Service
@Transactional
public class PriceService implements PriceServiceInterface {

	private PriceRepository repository;

	@Autowired
	private AccomodationRepository accomodationRepo;

	@Autowired
	public PriceService(PriceRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public boolean Create(PriceDTO dto) {
		try {
			ModelMapper mapper = new ModelMapper();
			Price price = mapper.map(dto, Price.class);
			Accomodation acc = accomodationRepo.getOne(dto.getAccomodation());
			price.setAccomodation(acc);
			repository.save(price);

			return true;
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return false;
	}

	@Override
	public PriceDTO Read(long id) {
		try {
			Price pr = repository.getOne(id);
			PriceDTO dto = new PriceDTO(pr.getId(), pr.getPrice(), pr.getStartDate(), pr.getEndDate(),
					pr.getAccomodation().getId(), pr.getVersion());
			return dto;
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<PriceDTO> ReadAll() {
		ArrayList<Price> listEntities = (ArrayList<Price>) repository.findAll();
		ArrayList<PriceDTO> listDTO = new ArrayList<PriceDTO>();

		for (Price pr : listEntities) {
			try {
				PriceDTO dto = new PriceDTO(pr.getId(), pr.getPrice(), pr.getStartDate(), pr.getEndDate(),
						pr.getAccomodation().getId(), pr.getVersion());
				listDTO.add(dto);
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	@Override
	public boolean Update(PriceDTO dto) {
		Price toUpdate = repository.getOne(dto.getId());

		try {
			if (toUpdate.getVersion() != dto.getVersion()) {
				return false;
			}
			toUpdate.setEndDate(dto.getEndDate());
			toUpdate.setPrice(dto.getPrice());
			toUpdate.setStartDate(dto.getStartDate());
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
