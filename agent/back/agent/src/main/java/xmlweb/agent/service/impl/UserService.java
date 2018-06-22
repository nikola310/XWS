package xmlweb.agent.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xmlweb.agent.model.Location;
import xmlweb.agent.model.User;
import xmlweb.agent.model.UserType;
import xmlweb.agent.model.dtos.AgentRequestDTO;
import xmlweb.agent.model.dtos.UserDTO;
import xmlweb.agent.repository.LocationRepository;
import xmlweb.agent.repository.UserRepository;
import xmlweb.agent.service.interfaces.UserServiceInterface;

@Service
@Transactional
public class UserService implements UserServiceInterface {

	private UserRepository repository;

	@Autowired
	private LocationRepository locationRepo;

	@Autowired
	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public boolean Create(UserDTO dto) {
		try {
			ModelMapper mapper = new ModelMapper();
			User user = mapper.map(dto, User.class);
			Location loc = locationRepo.getOne(dto.getAgentLocation());
			user.setAgentLocation(loc);	
			
			String username = user.getUserName();
			List<User> users = repository.findAll();
			boolean flag = false;
			for(User u : users){
				if(u.getUserName().equals(username)){
					flag = true;
					break;
				}
			}
			
			if(flag==false){
				repository.save(user);
				return true;
			}
			

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return false;
	}

	@Override
	public UserDTO Read(long id) {
		try {
			User tmp = repository.getOne(id);
			UserDTO dto = new UserDTO();
			dto.setFirstName(tmp.getFirstName());
			dto.setId(tmp.getId());
			dto.setLastName(tmp.getLastName());
			dto.setPassword(tmp.getPassword());
			dto.setPid(tmp.getPid());
			dto.setUserName(tmp.getUserName());
			dto.setUserType(tmp.getUserType());
			dto.setVersion(tmp.getVersion());
			dto.setActive(tmp.getActive());
			if (tmp.getAgentLocation() != null)
				dto.setAgentLocation(tmp.getAgentLocation().getId());
			return dto;
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<UserDTO> ReadAll() {
		ArrayList<User> listEntities = (ArrayList<User>) repository.findAll();
		ArrayList<UserDTO> listDTO = new ArrayList<UserDTO>();

		for (User tmp : listEntities) {
			try {
				UserDTO dto = new UserDTO();
				dto.setFirstName(tmp.getFirstName());
				dto.setId(tmp.getId());
				dto.setActive(tmp.getActive());
				dto.setLastName(tmp.getLastName());
				dto.setPassword(tmp.getPassword());
				dto.setPid(tmp.getPid());
				dto.setUserName(tmp.getUserName());
				dto.setUserType(tmp.getUserType());
				dto.setVersion(tmp.getVersion());
				if (tmp.getAgentLocation() != null)
					dto.setAgentLocation(tmp.getAgentLocation().getId());
				listDTO.add(dto);
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	@Override
	public boolean Update(UserDTO dto) {
		User toUpdate = repository.getOne(dto.getId());

		try {
			if (toUpdate.getVersion() != dto.getVersion()) {
				return false;
			}
			toUpdate.setUserName(dto.getUserName());
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

	@Override
	public List<UserDTO> getUserByType(UserType type) {
		ModelMapper mapper = new ModelMapper();
		ArrayList<User> listEntities = (ArrayList<User>) repository.getUserByUserType(type);
		ArrayList<UserDTO> listDTO = new ArrayList<UserDTO>();

		try {
			for (User tmp : listEntities) {

				UserDTO dto = mapper.map(tmp, UserDTO.class);
				if (tmp.getAgentLocation() != null)
					dto.setAgentLocation(tmp.getAgentLocation().getId());
				listDTO.add(dto);

			}
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
		return listDTO;
	}

	@Override
	public boolean manageAgent(AgentRequestDTO dto, long id) {

		try {
			User u = repository.getOne(id);
			if (dto.isAccept()) {
				u.setUserType(UserType.AGENT);
				Location loc = locationRepo.getOne(dto.getAddress());
				u.setAgentLocation(loc);
				u.setPid(dto.getPid());
			} else {
				u.setUserType(UserType.USER);
			}
			repository.save(u);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean manageUser(long id, int version, boolean status) {
		try {
			User u = repository.getOne(id);
			if (u.getVersion() != version)
				return false;

			u.setActive(status);

			repository.save(u);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public User getRegularUserByUsername(String username) {
		// TODO Auto-generated method stub
		return repository.findRegularUserByUserName(username);
	}

	@Override
	public User getAdminByUsername(String username) {
		return repository.findAdminByUserName(username);
	}
	


}
