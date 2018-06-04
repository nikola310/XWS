package xmlweb.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xmlweb.projekat.model.Picture;

/**
 * @author Nikola
 *
 */
public interface PictureRepository extends JpaRepository<Picture, Long> {

}
