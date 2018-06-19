package xmlweb.agent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import xmlweb.agent.model.User;
import xmlweb.agent.model.UserType;

/**
 * @author Nikola
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	public List<User> getUserByUserType(UserType type);
}
