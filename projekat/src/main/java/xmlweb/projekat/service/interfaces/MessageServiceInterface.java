package xmlweb.projekat.service.interfaces;

import java.util.List;

import xmlweb.projekat.model.Message;
import xmlweb.projekat.model.dtos.MessageDTO;

public interface MessageServiceInterface extends ServiceInterface<MessageDTO> {
	
	List<Message> findMessagesByUser(long id);
}
