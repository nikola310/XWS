package xmlweb.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xmlweb.agent.model.BonusService;

/**
 * @author Nikola
 *
 */
public interface BonusServiceRepository extends JpaRepository<BonusService, Long> {

}
