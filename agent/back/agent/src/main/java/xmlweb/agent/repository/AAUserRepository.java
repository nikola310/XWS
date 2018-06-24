package xmlweb.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import xmlweb.agent.model.User;

public interface AAUserRepository extends JpaRepository<User, Long>{
	
	@Query("SELECT u FROM User u where u.userName = ?1 and u.userType=1")
	public User findAdminByUsername(String username);
	
}
