package xmlweb.agent.service.interfaces;

import java.util.List;

import xmlweb.agent.model.User;
import xmlweb.agent.model.UserType;
import xmlweb.agent.model.dtos.AgentRequestDTO;
import xmlweb.agent.model.dtos.UserDTO;

public interface UserServiceInterface extends ServiceInterface<UserDTO> {

	public List<UserDTO> getUserByType(UserType type);

	public boolean manageAgent(AgentRequestDTO dto, long id);

	public boolean manageUser(long id, int version, boolean status);
	
	public User getRegularUserByUsername(String username);
	
	public User getAdminByUsername(String username);
}
