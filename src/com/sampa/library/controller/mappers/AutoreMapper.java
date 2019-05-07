package com.sampa.library.controller.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sampa.library.controller.dto.DtoAutori;
import com.sampa.library.models.pojos.Autore;

public interface AutoreMapper {
	
	final static String INSERISCI_AUTORE = "INSERT INTO autore(nome, cognome,anno_nascita,nazionalita) VALUE(#{nome}, #{cognome}, #{annoDiNascita}, #{nazionalita})";
	final static String ELIMINA_AUTORE = "DELETE FROM autore WHERE id = #{id}";
	final static String MODIFICA_AUTORE = "UPDATE autore SET nome = #{nome}, cognome = #{cognome}, anno_nascita = #{annoDiNascita},nazionalita = #{nazionalita} WHERE id = #{id}";
	final static String SELEZIONA_TUTTI_GLI_AUTORI = "SELECT * FROM autore";
	final static String SELEZIONA_AUTORE = "SELECT a.nome,a.cognome, a.nazionalita, a.anno_nascita,a.id FROM autore a WHERE nome = #{nome}";
	
	@Insert(INSERISCI_AUTORE)
	public void insert(Autore autore);
	
	@Select(SELEZIONA_AUTORE)
	public Autore getByName(String nome);
	
	@Select(SELEZIONA_TUTTI_GLI_AUTORI)
	public List<DtoAutori> getAll();
	
	@Delete(ELIMINA_AUTORE)
	public void delete(int id);
	
	@Update(MODIFICA_AUTORE)
	public void update(Autore autore);

}
