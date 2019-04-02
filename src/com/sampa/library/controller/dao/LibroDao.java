package com.sampa.library.controller.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sampa.library.controller.dto.DtoLibri;
import com.sampa.library.models.pojos.Libro;
import com.sampa.library.utility.Connessione;

public class LibroDao {


	public boolean insert(Libro libro) throws SQLException {

		String query;
		PreparedStatement st;
		//AGGIUNGERE IL GENERE
		query = "INSERT INTO libro(titolo, serie, collana, lingua, anno_pubblicazione, pagine, prezzo, isbn, autore_id, editore_id) "+
						 "VALUE ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		st = Connessione.getConnection().prepareStatement(query);

		st.setString(1, libro.getTitolo());
		st.setString(2, libro.getSerie());		
		st.setString(3, libro.getCollana());
		st.setString(4, libro.getLingua());
		st.setInt(5, libro.getAnno());
		st.setInt(6, libro.getPagine());
		st.setDouble(7, libro.getPrezzo());
		st.setString(8, libro.getIsbn());
//		st.setInt(9, libro.genere());
		st.setInt(9, libro.getId_autore());
		st.setInt(10, libro.getId_editore());
		
//		System.out.println("-----------");
//		System.out.println(libro.toString());
//		System.out.println("-----------");
		return st.execute();
	}

	public boolean delete(String isbn) throws SQLException {

		String query;
		Statement st;

		query = "DELETE FROM libro WHERE isbn = '" + isbn + "'";
		st = Connessione.getConnection().createStatement();
		return st.executeUpdate(query) > 0;
	}

	public Libro findByParams(String campo, String valore) throws SQLException {

		Libro libro = new Libro();;

		Statement stLibro;
		ResultSet rsLibro;

		
		String query = "SELECT * from libro WHERE " + campo + " = '" + valore + "'";
		
		stLibro = Connessione.getConnection().createStatement();
		rsLibro = stLibro.executeQuery(query);

		while(rsLibro.next()) {
		
		libro.setAnno(rsLibro.getInt("anno_pubblicazione"));
		libro.setCollana(rsLibro.getString("collana"));
		libro.setIsbn(rsLibro.getString("isbn"));
		libro.setLingua(rsLibro.getString("lingua"));
		libro.setPagine(rsLibro.getInt("pagine"));
		libro.setPrezzo(rsLibro.getDouble("prezzo"));
		libro.setSerie(rsLibro.getString("serie"));
		libro.setTitolo(rsLibro.getString("titolo"));
		libro.setId_autore(rsLibro.getInt("autore_id"));
		libro.setId_editore(rsLibro.getInt("editore_id"));
//		libro.setGenere(rsLibro.getString("genere"));
	}
		return libro;

	}

	public boolean update(Libro libro, String isbn) throws SQLException {

		//AGGIUNGERE IL GENERE
		
		String query = "UPDATE libro SET titolo = ?, serie = ?, collana = ?, anno_pubblicazione = ? , lingua = ?, "
					 + "pagine = ?, prezzo = ?, editore_id = ?, autore_id = ? WHERE isbn = ?";
		PreparedStatement st = Connessione.getConnection().prepareStatement(query);
		st.setString(1, libro.getTitolo());
		st.setString(2, libro.getSerie());
		st.setString(3, libro.getCollana());
		st.setInt(4, libro.getAnno());
		st.setString(5, libro.getLingua());
		st.setInt(6, libro.getPagine());
		st.setDouble(7, libro.getPrezzo());
		st.setInt(8, libro.getId_editore());
		st.setInt(9, libro.getId_autore());
		st.setString(10, isbn);

		return st.executeUpdate() > 0;
	}

	public List<DtoLibri> selectAll() throws SQLException {

		List<DtoLibri> libri = new ArrayList<>();
		
		//AGGIUNGERE IL GENERE		
		String query = "SELECT l.titolo,CONCAT(a.nome, ' ', a.cognome) AS autore, l.isbn, l.anno_pubblicazione,l.lingua," + 
										  " l.serie, l.collana,l.pagine,l.prezzo,e.nome editore FROM libro l JOIN " + 
										  "autore a ON a.id = l.autore_id JOIN editore e ON l.editore_id = e.id";
		
		Statement stLibro = Connessione.getConnection().createStatement();
		ResultSet rsLibro = stLibro.executeQuery(query);
		DtoLibri libro = null;
		
		while(rsLibro.next()) {

			libro = new DtoLibri();
			libro.setIsbn(rsLibro.getString("isbn"));
			libro.setAnno(rsLibro.getInt("anno_pubblicazione"));
			libro.setCollana(rsLibro.getString("collana"));
			libro.setLingua(rsLibro.getString("lingua"));
			libro.setPagine(rsLibro.getInt("pagine"));
			libro.setPrezzo(rsLibro.getDouble("prezzo"));
			libro.setSerie(rsLibro.getString("serie"));
			libro.setTitolo(rsLibro.getString("titolo"));
			libro.setAutore(rsLibro.getString("autore"));
			libro.setEditore(rsLibro.getString("editore"));
//			libro.setGenere(rsLibro.getString("genere"));
			libri.add(libro);
		}
		return libri;
	}
}
