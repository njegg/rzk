package rzk;

import java.util.List;

import javax.ejb.Remote;

import model.Ogla;
import model.OglasPrijava;

@Remote
public interface OglasiBeanRemote {
	int login(String username, String password);
	List<Ogla> searchOglas(String text);
	boolean addOglas(String text);
	OglasPrijava applyToOglas(int oglasID, String text);

	void remove();
}
