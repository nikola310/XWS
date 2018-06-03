package xmlweb.projekat.service.impl;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xmlweb.projekat.model.User;
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
		// ModelMapper mapper = new ModelMapper();
		// User user;
		System.out.println("sadasdsad -> " + dto.getFirstName());
		System.out.println(dto.getLastName());
		System.out.println(dto.getPassword());
		try {
			/*
			 * user = mapper.map(dto, User.class); if (token == null) { return false; }
			 * user.setUserPhone(new Integer(dto.getUserPhone())); Date expiration = new
			 * Date(); expiration.setTime(expiration.getTime() + (24 * 60 * 60 * 1000));
			 * 
			 * user.setUserToken(token); user.setUserExpiration(expiration);
			 * user.setUserCreationDate(new Date()); user.setUserRank(0);
			 * user.setUserAdmin('N');
			 */
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Update(UserDTO dto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Delete(long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
