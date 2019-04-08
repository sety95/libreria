package com.sampa.library.view.beans;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.DragDropEvent;

import com.sampa.library.controller.dao.AutoreDao;
import com.sampa.library.controller.dto.DtoAutori;
import com.sampa.library.models.pojos.Autore;


@Named
@SessionScoped
public class BeanAutore implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private List<DtoAutori> autori;
	private AutoreDao dao;
	private Autore autore;
	private String nomeDiRicerca;
	
	@PostConstruct
	public void init() {
	dao = new AutoreDao();
	autore = new Autore();
	setAutori();
	}

	public List<DtoAutori> getAutori() {
		return autori;
	}
	
	public void setAutori() {
		try {
			autori = dao.selectAll();
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Lista non inizializzata",null));
			e.printStackTrace();
		}
	}
	
	public Autore getAutore() {
		return autore;
	}

	public void setAutore(Autore autore) {
		this.autore = autore;
	}
	
	public String getNomeDiRicerca() {
		return nomeDiRicerca;
	}

	public void setNomeDiRicerca(String nomeDiRicerca) {
		this.nomeDiRicerca = nomeDiRicerca;
	}
	
	public void creaAutore() throws SQLException {
		this.autore = searchAutore(nomeDiRicerca);
	}

	public void inserisciAutore() {
		try {
			dao.insert(autore);
			setAutori();
			FacesMessage msg = new FacesMessage("Successo :D",  "e' andato tutto bene, hai appena aggiunto : " + autore.getNome() + " " + autore.getCognome());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (SQLException e) {
			FacesMessage msg = new FacesMessage("Fallimento >.<",  "qualcosa e' andato storto");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.printStackTrace();
		}
	}

	public void deleteAutori(DragDropEvent ddEvent) {
		
		DtoAutori autore = (DtoAutori) ddEvent.getData();
		try {
			dao.delete(autore.getId());
			setAutori();
			FacesMessage msg = new FacesMessage("Successo :D",  "e' andato tutto bene, hai appena eliminato : " + autore.getNomeCompleto());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (SQLException e) {
			FacesMessage msg = new FacesMessage("Fallimento >.<",  "qualcosa e' andato storto");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.printStackTrace();
		}
	}
	
	public void modifyAutore() throws SQLException {
//		System.out.println("autore in modify: " + autore.toString());
//		System.out.println("autoreTemp in modify: " + searchAutore(nome).toString());
		try {
			dao.update(autore,searchAutore(nomeDiRicerca).getId());
			setAutori();
			FacesMessage msg = new FacesMessage("Successo :D",  "e' andato tutto bene, modifica effettuata con successo");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (SQLException e) {
			FacesMessage msg = new FacesMessage("Fallimento >.<",  "qualcosa e' andato storto");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.printStackTrace();
		}
	}
	
	private Autore searchAutore(String parametro) throws SQLException {
		Autore autoreTemp;
		autoreTemp = dao.findByParams("nome", parametro);
		return autoreTemp;
	}
}