package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Oglas database table.
 * 
 */
@Entity
@Table(name="Oglas")
@NamedQuery(name="Ogla.findAll", query="SELECT o FROM Ogla o")
public class Ogla implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idOglas;

	private int brojPregleda;

	private String text;

	//bi-directional many-to-one association to OglasKorisnik
	@ManyToOne
	@JoinColumn(name="idKorisnik")
	private OglasKorisnik oglasKorisnik;

	//bi-directional many-to-one association to OglasPrijava
	@OneToMany(mappedBy="ogla")
	private List<OglasPrijava> oglasPrijavas;

	public Ogla() {
	}

	public int getIdOglas() {
		return this.idOglas;
	}

	public void setIdOglas(int idOglas) {
		this.idOglas = idOglas;
	}

	public int getBrojPregleda() {
		return this.brojPregleda;
	}

	public void setBrojPregleda(int brojPregleda) {
		this.brojPregleda = brojPregleda;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public OglasKorisnik getOglasKorisnik() {
		return this.oglasKorisnik;
	}

	public void setOglasKorisnik(OglasKorisnik oglasKorisnik) {
		this.oglasKorisnik = oglasKorisnik;
	}

	public List<OglasPrijava> getOglasPrijavas() {
		return this.oglasPrijavas;
	}

	public void setOglasPrijavas(List<OglasPrijava> oglasPrijavas) {
		this.oglasPrijavas = oglasPrijavas;
	}

	public OglasPrijava addOglasPrijava(OglasPrijava oglasPrijava) {
		getOglasPrijavas().add(oglasPrijava);
		oglasPrijava.setOgla(this);

		return oglasPrijava;
	}

	public OglasPrijava removeOglasPrijava(OglasPrijava oglasPrijava) {
		getOglasPrijavas().remove(oglasPrijava);
		oglasPrijava.setOgla(null);

		return oglasPrijava;
	}

}