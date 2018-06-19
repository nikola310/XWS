package xmlweb.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xmlweb.agent.model.Picture;

/**
 * @author Nikola
 *
 */
public interface PictureRepository extends JpaRepository<Picture, Long> {

}
