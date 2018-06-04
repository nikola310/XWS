package xmlweb.projekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xmlweb.projekat.model.Price;

/**
 * @author Nikola
 *
 */
public interface PriceRepository extends JpaRepository<Price, Long> {

}
