package xmlweb.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xmlweb.agent.model.Price;

public interface AAPriceRepository extends JpaRepository<Price, Long>{

}
