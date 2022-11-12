package rzk;

import java.util.List;

import javax.ejb.Remote;

import model.Ogla;
import model.OglasPrijava;

@Remote
public interface OglasiBeanRemote {
	int login(String username, String password);
	boolean dodajOglas(String text);
	List<Ogla> pretraziOglase(String text);
	OglasPrijava prijavaNaOglas(int oglasID, String text);

	void remove();
}
