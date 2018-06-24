package xmlweb.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xmlweb.agent.model.Picture;

public interface AAPictureRepository extends JpaRepository<Picture, Long>{

}
