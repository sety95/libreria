package com.sampa.library.controller.dto;

import java.io.Serializable;

public class DtoLibri implements Serializable {

	private static final long serialVersionUID = 1940436817120425488L;
	
	
	private String titolo;
	private String serie;
	private String collana;
//	private String genere;
	private String lingua;
	private int pagine;
	private int anno;
	private String isbn;
	private double prezzo;
	private String autore;
	private String editore;
	
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getCollana() {
		return collana;
	}
	public void setCollana(String collana) {
		this.collana = collana;
	}
//	public String getGenere() {
//		return genere;
//	}
//	public void setGenere(String genere) {
//		this.genere = genere;
//	}
	public String getLingua() {
		return lingua;
	}
	public void setLingua(String lingua) {
		this.lingua = lingua;
	}
	public int getPagine() {
		return pagine;
	}
	public void setPagine(int pagine) {
		this.pagine = pagine;
	}
	public int getAnno() {
		return anno;
	}
	public void setAnno(int anno) {
		this.anno = anno;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public String getAutore() {
		return autore;
	}
	public void setAutore(String autore) {
		this.autore = autore;
	}
	public String getEditore() {
		return editore;
	}
	public void setEditore(String editore) {
		this.editore = editore;
	}
	@Override
	public String toString() {
		return "DtoLibri [titolo=" + titolo + ", serie=" + serie + ", collana=" + collana
				+ ", lingua=" + lingua + ", pagine=" + pagine + ", anno=" + anno + ", isbn=" + isbn + ", prezzo="
				+ prezzo + ", autore=" + autore + ", editore=" + editore + "]";
	}
	
	
}
