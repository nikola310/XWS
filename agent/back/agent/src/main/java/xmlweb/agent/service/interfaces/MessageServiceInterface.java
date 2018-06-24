package xmlweb.agent.service.interfaces;

import java.util.List;

import xmlweb.agent.model.Message;

public interface MessageServiceInterface {

	List<Message> readAll();
	List<Message> getByUsername(String username);
	Message getById(Long id);
	boolean Create(Message m);
	boolean Update(Message m);
	
}
