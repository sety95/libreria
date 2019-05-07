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
		autori = dao.getAll();
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
		dao.insert(autore);
		setAutori();
		FacesMessage msg = new FacesMessage("Successo :D",  "e' andato tutto bene, hai appena aggiunto : " + autore.getNome() + " " + autore.getCognome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void deleteAutori(DragDropEvent ddEvent) {
		
		DtoAutori autore = (DtoAutori) ddEvent.getData();
		dao.delete(autore.getId());
		setAutori();
		FacesMessage msg = new FacesMessage("Successo :D",  "e' andato tutto bene, hai appena eliminato : " + autore.getNomeCompleto());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void modifyAutore() throws SQLException {
dao.update(autore);
		setAutori();
		FacesMessage msg = new FacesMessage("Successo :D",  "e' andato tutto bene, modifica effettuata con successo");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	private Autore searchAutore(String parametro) throws SQLException {
		Autore autoreTemp;
		autoreTemp = dao.getByName(parametro);
		return autoreTemp;
	}
}