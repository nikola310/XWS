package xmlweb.projekat.service.impl;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xmlweb.projekat.model.Message;
import xmlweb.projekat.model.dtos.MessageDTO;
import xmlweb.projekat.repository.MessageRepository;
import xmlweb.projekat.service.interfaces.MessageServiceInterface;

@Service
@Transactional
public class MessageService implements MessageServiceInterface {

	private MessageRepository repository;

	@Autowired
	public MessageService(MessageRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public boolean Create(MessageDTO dto) {
		try {
			ModelMapper mapper = new ModelMapper();
			Message msg = mapper.map(dto, Message.class);
			repository.save(msg);

			return true;
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return false;
	}

	@Override
	public MessageDTO Read(long id) {
		try {
			Message msg = repository.getOne(id);
			ModelMapper mapper = new ModelMapper();
			MessageDTO dto = mapper.map(msg, MessageDTO.class);
			return dto;
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<MessageDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<Message> listEntities = (ArrayList<Message>) repository.findAll();
		ArrayList<MessageDTO> listDTO = new ArrayList<MessageDTO>();

		for (Message tmp : listEntities) {
			try {
				MessageDTO dto = mapper.map(tmp, MessageDTO.class);
				listDTO.add(dto);
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	@Override
	public boolean Update(MessageDTO dto) {
		Message toUpdate = repository.getOne(dto.getId());

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
