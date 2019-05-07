package com.sampa.library.view.beans;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.DragDropEvent;

import com.sampa.library.controller.dao.LibroDao;
import com.sampa.library.controller.dto.DtoLibri;
import com.sampa.library.models.pojos.Libro;

@Named
@SessionScoped
public class BeanLibro implements Serializable {

	private static final long serialVersionUID = -3415335071697029412L;
	
	private List<DtoLibri> libri;
	private List<DtoLibri> listaFiltrata = new ArrayList<DtoLibri>();
	private LibroDao dao;
	private Libro libro;
//	private String titoloDiRicerca;
	private DtoLibri libroSelezionato;
//	private String titolo;
	
	@PostConstruct
	public void init() throws SQLException {
//	titolo = "";
	dao = new LibroDao();
//	System.out.println("chiamato dao");
	libro = new Libro();
	setLibri();
	}

	public List<DtoLibri> getLibri() {
		return libri;
	}
	
	public void setLibri() throws SQLException {
		libri = dao.getAll();
//			System.out.println("metodo chiamato per libri");
	}
	
	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public String inserisciLibro() {
		try {
			dao.insert(libro);
			setLibri();
			FacesMessage msg = new FacesMessage("Successo :D",  "e' andato tutto bene, hai appena aggiunto : " + libro.getTitolo());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "libri?faces-redirect=true";
		} catch (SQLException e) {
			FacesMessage msg = new FacesMessage("Fallimento >.<",  "qualcosa e' andato storto");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.printStackTrace();
			return "error?faces-redirect=true";
		} 
	}
	
//	public String getTitoloDiRicerca() {
//		return titoloDiRicerca;
//	}
//
//	public void setTitoloDiRicerca(String titoloDiRicerca) {
//		this.titoloDiRicerca = titoloDiRicerca;
//	}
	

	public void deleteLibro(DragDropEvent ddEvent) {
		try {
			DtoLibri libro = (DtoLibri) ddEvent.getData();
			dao.delete(libro.getIsbn());
			setLibri();
			FacesMessage msg = new FacesMessage("Successo :D",  "e' andato tutto bene, hai appena eliminato : " + libro.getTitolo());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (SQLException e) {
			FacesMessage msg = new FacesMessage("Fallimento >.<",  "qualcosa e' andato storto");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.printStackTrace();
		}
	}
	
	public void modifyLibro() throws SQLException {
//		System.out.println("libro in modify: " + libro.toString());
//		System.out.println("libroTemp in modify: " + searchLibro(titoloDiRicerca).toString());
		try {
			dao.update(libro);
			setLibri();
			FacesMessage msg = new FacesMessage("Successo :D",  "e' andato tutto bene, modifica effettuata con successo");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (SQLException e) {
			FacesMessage msg = new FacesMessage("Fallimento >.<",  "qualcosa e' andato storto");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.printStackTrace();}
	}

	public DtoLibri getLibroSelezionato() {
		return libroSelezionato;
	}

	public void setLibroSelezionato(DtoLibri libroSelezionato) {
		this.libroSelezionato = libroSelezionato;
	}

//	public String getTitolo() {
//		return titolo;
//	}
//
//	public void setTitolo(String titolo) {
//		this.titolo = titolo;
//	}

	public List<DtoLibri> getListaFiltrata() {
		return listaFiltrata;
	}

	public void setListaFiltrata(List<DtoLibri> listaFiltrata) {
		this.listaFiltrata = listaFiltrata;
	}
}
