package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the OglasKorisnik database table.
 * 
 */
@Entity
@NamedQuery(name="OglasKorisnik.findAll", query="SELECT o FROM OglasKorisnik o")
public class OglasKorisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKorisnik;

	private String nickname;

	private String password;

	private String username;

	//bi-directional many-to-one association to Ogla
	@OneToMany(mappedBy="oglasKorisnik")
	private List<Ogla> oglas;

	//bi-directional many-to-one association to OglasPrijava
	@OneToMany(mappedBy="oglasKorisnik")
	private List<OglasPrijava> oglasPrijavas;

	public OglasKorisnik() {
	}

	public int getIdKorisnik() {
		return this.idKorisnik;
	}

	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Ogla> getOglas() {
		return this.oglas;
	}

	public void setOglas(List<Ogla> oglas) {
		this.oglas = oglas;
	}

	public Ogla addOgla(Ogla ogla) {
		getOglas().add(ogla);
		ogla.setOglasKorisnik(this);

		return ogla;
	}

	public Ogla removeOgla(Ogla ogla) {
		getOglas().remove(ogla);
		ogla.setOglasKorisnik(null);

		return ogla;
	}

	public List<OglasPrijava> getOglasPrijavas() {
		return this.oglasPrijavas;
	}

	public void setOglasPrijavas(List<OglasPrijava> oglasPrijavas) {
		this.oglasPrijavas = oglasPrijavas;
	}

	public OglasPrijava addOglasPrijava(OglasPrijava oglasPrijava) {
		getOglasPrijavas().add(oglasPrijava);
		oglasPrijava.setOglasKorisnik(this);

		return oglasPrijava;
	}

	public OglasPrijava removeOglasPrijava(OglasPrijava oglasPrijava) {
		getOglasPrijavas().remove(oglasPrijava);
		oglasPrijava.setOglasKorisnik(null);

		return oglasPrijava;
	}

}