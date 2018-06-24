package xmlweb.agent.service.interfaces;

import java.util.ArrayList;

import xmlweb.agent.model.Picture;

public interface PictureServiceInterface {

	ArrayList<Picture> readAll();
	Picture readOne(Long id);
	boolean Create(Picture p);
	boolean Update(Picture p);
}
