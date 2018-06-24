package xmlweb.agent.service.interfaces;

import java.util.List;

import xmlweb.agent.model.User;

public interface UserServiceInterface {
	
	public List<User> getAll();
	public User getByUsername(String username);
	public User getById(Long id);
	boolean Create(User u);
	boolean Update(User u);

}
