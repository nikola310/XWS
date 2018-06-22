package xmlweb.projekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import xmlweb.projekat.model.Picture;

/**
 * @author Nikola
 *
 */
public interface PictureRepository extends JpaRepository<Picture, Long> {
	
	@Query("SELECT p FROM Picture p WHERE p.accomodation.id = ?1")
	List<Picture> findPicturesByAccomodation(long id);
}
