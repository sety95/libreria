package com.sampa.library.controller.mappers;

public interface LibroMapper {
	
	final static String INSERIMENTO_LIBRO = "INSERT INTO libro(titolo, serie, collana, lingua, anno_pubblicazione, pagine, prezzo, isbn, autore_id, editore_id) VALUE ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	final static String RIMOZIONE_LIBRO = "DELETE FROM libro WHERE isbn = #{isbn,jdbcType=VARCHAR}";
	final static String SELEZIONE_LIBRO_SPECIFICO = "SELECT * from libro WHERE \" + campo + \" = '\" + valore + \"' = #{isbn,jdbcType=VARCHAR}";
}
