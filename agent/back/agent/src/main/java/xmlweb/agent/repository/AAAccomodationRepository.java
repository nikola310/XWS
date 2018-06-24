package xmlweb.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xmlweb.agent.model.Accomodation;

public interface AAAccomodationRepository extends JpaRepository<Accomodation, Long>{

	Accomodation findById(long id);
}
