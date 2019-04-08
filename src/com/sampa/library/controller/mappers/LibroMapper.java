package com.sampa.library.controller.mappers;

import org.apache.ibatis.annotations.Insert;

import com.sampa.library.models.pojos.Libro;

public interface LibroMapper {

	final static String INSERIMENTO_LIBRO = "INSERT INTO libro(titolo, serie, collana, lingua, anno_pubblicazione, pagine, prezzo, isbn, autore_id, editore_id) "
			+ "	VALUES ( #{titolo}, #{serie}, #{collana}, #{lingua}, #{anno}, #{pagine}, #{prezzo}, #{isbn}, #{id_autore}, #{id_editore})";
	final static String RIMOZIONE_LIBRO = "DELETE FROM libro WHERE isbn = #{isbn}";
	final static String SELEZIONE_LIBRO_SPECIFICO = "SELECT * from libro WHERE titolo = #{titolo}";
//	final static String SELEZIONE_LIBRO_SPECIFICO = "SELECT * from libro WHERE \" + campo + \" = '\" + valore + \"' = #{isbn,jdbcType=VARCHAR}";


	@Insert(INSERIMENTO_LIBRO)
	public void insert(Libro libro);
}
