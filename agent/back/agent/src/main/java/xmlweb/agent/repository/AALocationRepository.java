package xmlweb.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xmlweb.agent.model.Location;

public interface AALocationRepository  extends JpaRepository<Location, Long>{

	Location findById(long id);
}
