package xmlweb.projekat.service.interfaces;

import java.util.List;

import xmlweb.projekat.model.dtos.BonusServiceDTO;

public interface BonusServiceInterface {

	List<BonusServiceDTO> findAllBonusServices();
}
