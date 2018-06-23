package xmlweb.projekat.service.interfaces;

import java.util.List;

import xmlweb.projekat.model.User;
import xmlweb.projekat.model.UserType;
import xmlweb.projekat.model.dtos.AgentRequestDTO;
import xmlweb.projekat.model.dtos.UserDTO;

public interface UserServiceInterface extends ServiceInterface<UserDTO> {

	public List<UserDTO> getUserByType(UserType type);

	public boolean manageAgent(AgentRequestDTO dto, long id);

	public boolean manageUser(long id, int version, boolean status);
	
	public User getRegularUserByUsername(String username);
	
	public User getAdminByUsername(String username);
	
}
