package xmlweb.projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import xmlweb.projekat.model.User;
import xmlweb.projekat.model.UserType;

/**
 * @author Nikola
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	public List<User> getUserByUserType(UserType type);
}
