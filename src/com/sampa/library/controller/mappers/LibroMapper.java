package com.sampa.library.controller.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sampa.library.controller.dto.DtoLibri;
import com.sampa.library.models.pojos.Libro;

public interface LibroMapper {

	final static String RIMOZIONE_LIBRO = "DELETE FROM libro WHERE isbn = #{isbn}";
	final static String SELEZIONE_LIBRO_SPECIFICO = "SELECT * from libro WHERE titolo = #{titolo}";
	final static String MODIFICA_LIBRO = "UPDATE libro SET titolo = #{titolo}, serie = #{serie}, collana = #{collana}, anno_pubblicazione = #{anno} , lingua = #{lingua},"
			+ "pagine = #{pagine}, prezzo = #{prezzo}, editore_id = #{id_editore}, autore_id = #{id_autore} WHERE isbn = #{isbn}";
	final static String INSERIMENTO_LIBRO = "INSERT INTO libro(titolo, serie, collana, lingua, anno_pubblicazione, pagine, prezzo, isbn, autore_id, editore_id) "
					+ "	VALUES ( #{titolo}, #{serie}, #{collana}, #{lingua}, #{anno}, #{pagine}, #{prezzo}, #{isbn}, #{id_autore}, #{id_editore})";
	final static String SELEZIONE_TUTTI_I_LIBRI = "SELECT l.titolo,CONCAT(a.nome, ' ', a.cognome) AS autore, l.isbn, l.anno_pubblicazione,l.lingua,"
			+ "l.serie, l.collana,l.pagine,l.prezzo,e.nome editore FROM libro l JOIN autore a ON a.id = l.autore_id JOIN editore e ON l.editore_id = e.id";


	@Insert(INSERIMENTO_LIBRO)
	public void insert(Libro libro);
	
	@Select(SELEZIONE_LIBRO_SPECIFICO)
	public Libro getByTitle(String titolo);
	
	@Select(SELEZIONE_TUTTI_I_LIBRI)
	public List<DtoLibri> getAll();
	
	@Delete(RIMOZIONE_LIBRO)
	public void delete(String isbn);
	
	@Update(MODIFICA_LIBRO)
	public void update(Libro libro);
}
