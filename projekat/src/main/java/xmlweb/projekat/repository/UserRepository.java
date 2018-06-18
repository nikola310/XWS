package xmlweb.projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import xmlweb.projekat.model.User;
import xmlweb.projekat.model.UserType;

/**
 * @author Nikola
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	public List<User> getUserByUserType(UserType type);
	
	@Query("SELECT u FROM User u where u.userName = ?1 and u.userType=0")
	public User findRegularUserByUsername(String username);
	
	@Query("SELECT u FROM User u where u.userName = ?1 and u.userType=2")
	public User findAdminByUsername(String username);
}
