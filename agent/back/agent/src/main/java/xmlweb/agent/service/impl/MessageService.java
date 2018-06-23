package xmlweb.agent.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xmlweb.agent.model.Message;
import xmlweb.agent.repository.MessageRepository;
import xmlweb.agent.repository.UserRepository;
import xmlweb.agent.service.interfaces.MessageServiceInterface;

@Service
public class MessageService implements MessageServiceInterface {
	
	@Autowired
	private MessageRepository repository;

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Message> readAll() {
		return repository.findAll();
	}

	@Override
	public List<Message> getByUsername(String username) {
		List<Message> all = repository.findAll();
		List<Message> ret = new ArrayList<Message>();
		for(Message m : all) {
			if(m.getReciever().getUserName().equals(username) || m.getSender().getUserName().equals(username))
				ret.add(m);
		}
		
		return ret;
	}

	@Override
	public Message getById(Long id) {
		Optional<Message> m = repository.findById(id);
		
		if(m.isPresent())
			return m.get();
		else
			return null;
	}

	@Override
	public boolean Create(Message m) {
		try {
			repository.save(m);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean Update(Message m) {
		try {
			repository.save(m);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}


}
