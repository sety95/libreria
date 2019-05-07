package com.sampa.library.controller.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sampa.library.controller.dto.DtoEditori;
import com.sampa.library.models.pojos.Editore;

public interface EditoreMapper {

	final static String INSERISCI_EDITORE = "INSERT INTO editore(nome, indirizzo, sito) VALUE(#{nome}, #{indirizzo}, #{sito})";
	final static String ELIMINA_EDITORE = "DELETE FROM editore WHERE id = #{id} ";
	final static String MODIFICA_EDITORE = "UPDATE editore SET nome = #{nome}, indirizzo = #{indirizzo}, sito = #{sito} WHERE id = #{id} ";
	final static String SELEZIONA_TUTTI_GLI_EDITORI = "SELECT * FROM editore";
	final static String SELEZIONA_EDITORE = "SELECT * FROM editore WHERE ID = #{id}";
	
	@Select(SELEZIONA_TUTTI_GLI_EDITORI)
	public List<DtoEditori> getAll();
	
	@Select(SELEZIONA_EDITORE)
	public Editore find(String nome);
	
	@Insert(INSERISCI_EDITORE)
	public void inserisci(Editore autore);
	
	@Update(MODIFICA_EDITORE)
	public void modifica(Editore autore);
	
	@Delete(ELIMINA_EDITORE)
	public void delete(int id);
	
}
