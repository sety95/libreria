package com.sampa.library.utility;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import com.sampa.library.controller.dto.DtoLibri;
import com.sampa.library.view.beans.BeanLibro;

@Named
@SessionScoped
public class LibroConverter implements Serializable, Converter{

	@Inject
	BeanLibro libro;
	
	private static final long serialVersionUID = -9073046995912795481L;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if(value == null || value.isEmpty()) {
		return null;
		}
		
		return libro.getLibroSelezionato();
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if(value == null) {
		return null;
		}
		
		return String.valueOf(((DtoLibri)value).getIsbn());
	}

}
