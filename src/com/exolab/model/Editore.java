package com.exolab.model;


public class Editore {

	private int id;
	private String nome;
	private String indirizzo;
	private String sito;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getSito() {
		return sito;
	}
	public void setSito(String sito) {
		this.sito = sito;
	}

	@Override
	public String toString() {
		return "Editore [id=" + id + ", nome=" + nome + ", indirizzo=" + indirizzo + ", sito=" + sito + "]";
	}
  
	
	
}
