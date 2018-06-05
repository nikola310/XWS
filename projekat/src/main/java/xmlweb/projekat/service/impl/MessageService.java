package xmlweb.projekat.service.impl;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xmlweb.projekat.model.Message;
import xmlweb.projekat.model.User;
import xmlweb.projekat.model.dtos.MessageDTO;
import xmlweb.projekat.repository.MessageRepository;
import xmlweb.projekat.repository.UserRepository;
import xmlweb.projekat.service.interfaces.MessageServiceInterface;

@Service
@Transactional
public class MessageService implements MessageServiceInterface {

	private MessageRepository repository;

	@Autowired
	private UserRepository userRepo;

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
			User sender = userRepo.getOne(dto.getSender());
			User receiver = userRepo.getOne(dto.getReceiver());

			msg.setSender(sender);
			msg.setReciever(receiver);
			System.out.println(msg.getSender());
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
			if (msg == null)
				return null;
			MessageDTO dto = new MessageDTO(msg.getId(), msg.getSender().getId(), msg.getReciever().getId(),
					msg.getContent(), msg.getVersion());
			return dto;
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<MessageDTO> ReadAll() {
		ArrayList<Message> listEntities = (ArrayList<Message>) repository.findAll();
		ArrayList<MessageDTO> listDTO = new ArrayList<MessageDTO>();

		for (Message tmp : listEntities) {
			try {
				MessageDTO dto = new MessageDTO(tmp.getId(), tmp.getSender().getId(), tmp.getReciever().getId(),
						tmp.getContent(), tmp.getVersion());
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
