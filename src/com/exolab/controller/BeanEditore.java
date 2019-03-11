package com.exolab.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.DragDropEvent;

import com.exolab.dao.EditoreDao;
import com.exolab.dto.DtoEditori;
import com.exolab.model.Editore;

@Named
@SessionScoped
public class BeanEditore implements Serializable{


	private static final long serialVersionUID = 1L;

	private List<DtoEditori> editori;
	private EditoreDao dao;
	private Editore editore;
	private String nome;
	
	@PostConstruct
	public void init() {
		
		dao = new  EditoreDao();
		editore = new Editore();
		setEditori();
	}

	public List<DtoEditori> getEditori() {
		return editori;
	}

	public void setEditori() {
		
		try {
			editori = dao.selectAll();
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"lista non inizializzata",null));
			e.printStackTrace();
		}
	}
	
	public Editore getEditore() {
		return editore;
	}

	public void setEditore(Editore editore) {
		this.editore = editore;
	}
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void creaEditore() throws SQLException {
		this.editore = searchEditore(nome);
	}

	public void inserisciEditore() {
		try {
			dao.insert(editore);
			setEditori();
			FacesMessage msg = new FacesMessage("Successo :D",  "e' andato tutto bene, hai appena aggiunto: " + editore.getNome());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (SQLException e) {
			FacesMessage msg = new FacesMessage("Fallimento >.<",  "qualcosa e' andato storto");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.printStackTrace();
		}
	}

	public void deleteEditori(DragDropEvent ddEvent) {
		
		DtoEditori editore = (DtoEditori) ddEvent.getData();
		try {
			dao.delete(editore.getId());
			setEditori();
			FacesMessage msg = new FacesMessage("Successo :D",  "e' andato tutto bene, hai appena eliminato : " + editore.getNome());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (SQLException e) {
			FacesMessage msg = new FacesMessage("Fallimento >.<",  "qualcosa e' andato storto");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.printStackTrace();
		}
	}
	
	public void modifyEditore() throws SQLException {

		try {
			dao.update(editore,searchEditore(nome).getId());
			setEditori();
			FacesMessage msg = new FacesMessage("Successo :D",  "e' andato tutto bene, modifica effettuata con successo");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (SQLException e) {
			FacesMessage msg = new FacesMessage("Fallimento >.<",  "qualcosa e' andato storto");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.printStackTrace();
		}
	}
	
	private Editore searchEditore(String parametro) throws SQLException {
		Editore editoreTemp;
		editoreTemp = dao.findByParams("nome", parametro);
		return editoreTemp;
	}
}
