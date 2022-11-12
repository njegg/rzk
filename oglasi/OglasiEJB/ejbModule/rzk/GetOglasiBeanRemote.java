package rzk;

import java.util.List;

import javax.ejb.Remote;

import model.Ogla;
import model.OglasPrijava;

@Remote
public interface GetOglasiBeanRemote {
	Ogla getOglas(int idOglas);
	List<Ogla> getAllOglasi();
	List<OglasPrijava> getOglasPrijave(int idOglas);
}
