package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the OglasPrijava database table.
 * 
 */
@Entity
@NamedQuery(name="OglasPrijava.findAll", query="SELECT o FROM OglasPrijava o")
public class OglasPrijava implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPrijava;

	private String text;

	//bi-directional many-to-one association to Ogla
	@ManyToOne
	@JoinColumn(name="idOglas")
	private Ogla ogla;

	//bi-directional many-to-one association to OglasKorisnik
	@ManyToOne
	@JoinColumn(name="idKorisnik")
	private OglasKorisnik oglasKorisnik;

	public OglasPrijava() {
	}

	public int getIdPrijava() {
		return this.idPrijava;
	}

	public void setIdPrijava(int idPrijava) {
		this.idPrijava = idPrijava;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Ogla getOgla() {
		return this.ogla;
	}

	public void setOgla(Ogla ogla) {
		this.ogla = ogla;
	}

	public OglasKorisnik getOglasKorisnik() {
		return this.oglasKorisnik;
	}

	public void setOglasKorisnik(OglasKorisnik oglasKorisnik) {
		this.oglasKorisnik = oglasKorisnik;
	}

}