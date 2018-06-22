package xmlweb.projekat.service.interfaces;

import java.util.List;

import xmlweb.projekat.model.Picture;
import xmlweb.projekat.model.dtos.PictureDTO;

public interface PictureServiceInterface extends ServiceInterface<PictureDTO> {

	List<Picture> findPicturesByAccomodation(long id);
}
