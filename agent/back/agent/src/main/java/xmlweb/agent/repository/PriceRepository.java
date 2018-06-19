package xmlweb.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xmlweb.agent.model.Price;

/**
 * @author Nikola
 *
 */
public interface PriceRepository extends JpaRepository<Price, Long> {

}
