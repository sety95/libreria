package com.exolab.model;


public class Autore {

	private int annoDiNascita;
	private String nome;
	private String cognome;
	private String nazionalita;
	private int id;

	public int getAnnoDiNascita() {
		return annoDiNascita;
	}
	public void setAnnoDiNascita(int annoDiNascita) {
		this.annoDiNascita = annoDiNascita;
	}
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

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Autore [annoDiNascita=" + annoDiNascita + ", nome=" + nome + ", cognome=" + cognome + ", nazionalita="
				+ nazionalita + "]";
	}
	
}
