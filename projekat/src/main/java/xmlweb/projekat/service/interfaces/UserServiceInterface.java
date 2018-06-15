package xmlweb.projekat.service.interfaces;

import java.util.List;

import xmlweb.projekat.model.dtos.UserDTO;

public interface UserServiceInterface extends ServiceInterface<UserDTO> {

	public List<UserDTO> getNormalUsers();
	
	public List<UserDTO> getAgents();
}
