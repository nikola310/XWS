package xmlweb.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xmlweb.projekat.model.User;

/**
 * @author Nikola
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
