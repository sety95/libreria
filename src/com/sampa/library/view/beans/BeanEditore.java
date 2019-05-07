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

import com.sampa.library.controller.dao.EditoreDao;
import com.sampa.library.controller.dto.DtoEditori;
import com.sampa.library.models.pojos.Editore;

@Named
@SessionScoped
public class BeanEditore implements Serializable{


	private static final long serialVersionUID = -2334676012107311662L;
	
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
			editori = dao.getAll();
		} catch (Exception e) {
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
	

	public void inserisciEditore() {
		try {
			dao.inserisci(editore);
			setEditori();
			FacesMessage msg = new FacesMessage("Successo :D",  "e' andato tutto bene, hai appena aggiunto: " + editore.getNome());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
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
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage("Fallimento >.<",  "qualcosa e' andato storto");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.printStackTrace();
		}
	}
	
	public void modifyEditore() throws SQLException {

		try {
			dao.modifica(editore);
			setEditori();
			FacesMessage msg = new FacesMessage("Successo :D",  "e' andato tutto bene, modifica effettuata con successo");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage("Fallimento >.<",  "qualcosa e' andato storto");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.printStackTrace();
		}
	}
	
	public void creaEditore() {
		editore = dao.find(nome);
	}
}
