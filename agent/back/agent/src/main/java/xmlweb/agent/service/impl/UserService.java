package xmlweb.agent.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xmlweb.agent.model.User;
import xmlweb.agent.repository.LocationRepository;
import xmlweb.agent.repository.UserRepository;
import xmlweb.agent.service.interfaces.UserServiceInterface;

@Service
public class UserService implements UserServiceInterface {

	@Autowired
	private UserRepository repository;

	@Autowired
	private LocationRepository locationRepo;

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public User getByUsername(String username) {
		List<User> users = repository.findAll();
		for(User u : users) {
			if(u.getUserName().equals(username))
				return u;
		}
		return null;
	}

	@Override
	public User getById(Long id) {
		Optional<User> u = repository.findById(id);
		if(u.isPresent())
			return u.get();
		else
		 	return null;
	}

	@Override
	public boolean Create(User u) {
		try {
			repository.save(u);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean Update(User u) {
		try {
			if(getById(u.getId()) == null)
				return false;
			
			repository.save(u);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}





}
