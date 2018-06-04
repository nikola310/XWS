package xmlweb.projekat.service.impl;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xmlweb.projekat.model.Picture;
import xmlweb.projekat.model.dtos.PictureDTO;
import xmlweb.projekat.repository.PictureRepository;
import xmlweb.projekat.service.interfaces.PictureServiceInterface;

@Service
@Transactional
public class PictureService implements PictureServiceInterface {

	private PictureRepository repository;

	@Autowired
	public PictureService(PictureRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public boolean Create(PictureDTO dto) {
		try {
			ModelMapper mapper = new ModelMapper();
			Picture pic = mapper.map(dto, Picture.class);
			repository.save(pic);

			return true;
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return false;
	}

	@Override
	public PictureDTO Read(long id) {
		try {
			Picture pic = repository.getOne(id);
			ModelMapper mapper = new ModelMapper();
			PictureDTO dto = mapper.map(pic, PictureDTO.class);
			return dto;
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<PictureDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<Picture> listEntities = (ArrayList<Picture>) repository.findAll();
		ArrayList<PictureDTO> listDTO = new ArrayList<PictureDTO>();

		for (Picture tmp : listEntities) {
			try {
				PictureDTO dto = mapper.map(tmp, PictureDTO.class);
				listDTO.add(dto);
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	@Override
	public boolean Update(PictureDTO dto) {
		Picture toUpdate = repository.getOne(dto.getId());

		try {
			if (toUpdate.getVersion() != dto.getVersion()) {
				return false;
			}
			toUpdate.setContent(dto.getContent());
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
