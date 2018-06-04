package xmlweb.projekat.service.impl;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xmlweb.projekat.model.Price;
import xmlweb.projekat.model.dtos.PriceDTO;
import xmlweb.projekat.repository.PriceRepository;
import xmlweb.projekat.service.interfaces.PriceServiceInterface;

@Service
@Transactional
public class PriceService implements PriceServiceInterface {

	private PriceRepository repository;

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
			ModelMapper mapper = new ModelMapper();
			PriceDTO dto = mapper.map(pr, PriceDTO.class);
			return dto;
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<PriceDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<Price> listEntities = (ArrayList<Price>) repository.findAll();
		ArrayList<PriceDTO> listDTO = new ArrayList<PriceDTO>();

		for (Price tmp : listEntities) {
			try {
				PriceDTO dto = mapper.map(tmp, PriceDTO.class);
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
