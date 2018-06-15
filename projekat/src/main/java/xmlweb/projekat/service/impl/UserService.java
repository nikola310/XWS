package xmlweb.projekat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xmlweb.projekat.model.User;
import xmlweb.projekat.model.UserType;
import xmlweb.projekat.model.dtos.UserDTO;
import xmlweb.projekat.repository.UserRepository;
import xmlweb.projekat.service.interfaces.UserServiceInterface;

@Service
@Transactional
public class UserService implements UserServiceInterface {

	private UserRepository repository;

	@Autowired
	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public boolean Create(UserDTO dto) {
		try {
			ModelMapper mapper = new ModelMapper();
			User user = mapper.map(dto, User.class);
			repository.save(user);

			return true;
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return false;
	}

	@Override
	public UserDTO Read(long id) {
		try {
			User user = repository.getOne(id);
			ModelMapper mapper = new ModelMapper();
			UserDTO dto = mapper.map(user, UserDTO.class);
			return dto;
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<UserDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<User> listEntities = (ArrayList<User>) repository.findAll();
		ArrayList<UserDTO> listDTO = new ArrayList<UserDTO>();

		for (User tmp : listEntities) {
			try {
				UserDTO dto = mapper.map(tmp, UserDTO.class);
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
	public List<UserDTO> getNormalUsers() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<User> listEntities = (ArrayList<User>) repository.getUserByUserType(UserType.USER);
		ArrayList<UserDTO> listDTO = new ArrayList<UserDTO>();

		for (User tmp : listEntities) {
			try {
				UserDTO dto = mapper.map(tmp, UserDTO.class);
				listDTO.add(dto);
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	@Override
	public List<UserDTO> getAgents() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<User> listEntities = (ArrayList<User>) repository.getUserByUserType(UserType.AGENT);
		ArrayList<UserDTO> listDTO = new ArrayList<UserDTO>();

		for (User tmp : listEntities) {
			try {
				UserDTO dto = mapper.map(tmp, UserDTO.class);
				listDTO.add(dto);
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

}
