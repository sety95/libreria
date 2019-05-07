package com.sampa.library.controller.dto;


public class DtoAutori {

	private String nome;
	private String cognome;
	private String nazionalita;
	private int anno_nascita;
	private int id;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNazionalita() {
		return nazionalita;
	}
	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}
	public int getAnno_nascita() {
		return anno_nascita;
	}
	public void setAnno_nascita(int anno_nascita) {
		this.anno_nascita = anno_nascita;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomeCompleto() {
		return nome + " " + cognome;
	}
	@Override
	public String toString() {
		return "DtoAutori [nome=" + nome + ", cognome=" + cognome + ", nazionalita=" + nazionalita + ", anno_nascita=" + anno_nascita
				+ ", id=" + id + "]";
	}

}
