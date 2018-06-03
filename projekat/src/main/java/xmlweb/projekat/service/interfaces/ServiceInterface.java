package xmlweb.projekat.service.interfaces;

import java.util.ArrayList;

public interface ServiceInterface<T> {

	public boolean Create(T dto);

	public T Read(long id);

	public ArrayList<T> ReadAll();

	public boolean Update(T dto);

	public boolean Delete(long id);
}
