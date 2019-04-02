package com.sampa.library.controller.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sampa.library.models.pojos.Genere;
import com.sampa.library.utility.Connessione;

public class GenereDao {

	public boolean insert(Genere genere) throws SQLException {

		String query;
		PreparedStatement st;

		query = "INSERT INTO genere(nome, sottoGenere) VALUE (?, ?)";
		st = Connessione.getConnection().prepareStatement(query);

		st.setString(1, genere.getNome());
		st.setString(2, genere.getSottoGenere());		
		
		return st.execute();
	}

//	public boolean delete(String isbn) throws SQLException {
//
//		String query;
//		Statement st;
//
//		query = "DELETE FROM libro WHERE isbn = '" + isbn + "'";
//		st = Connessione.getConnection().createStatement();
//		return st.executeUpdate(query) > 0;
//	}

	public Genere findByParams(String campo, String valore) throws SQLException {

		Genere genere = new Genere();;

		Statement st;
		ResultSet rs;

		
		String query = "SELECT * from genere WHERE " + campo + " = '" + valore + "'";
		
		st = Connessione.getConnection().createStatement();
		rs = st.executeQuery(query);

		while(rs.next()) {
		
		genere.setNome(rs.getString("nome"));
		genere.setSottoGenere(rs.getString("sottoGenere"));
		
	}
		return genere;

	}

	public boolean update(Genere genere, int id) throws SQLException {

		//AGGIUNGERE IL GENERE
		
		String query = "UPDATE genere SET nome = ?, sottoGenere = ? WHERE id = ?";
		PreparedStatement st = Connessione.getConnection().prepareStatement(query);
		st.setString(1, genere.getNome());
		st.setString(2, genere.getSottoGenere());
		st.setInt(3, genere.getId());

		return st.executeUpdate() > 0;
	}

	public List<Genere> selectAll() throws SQLException {

		List<Genere> generi = new ArrayList<>();
		
		//AGGIUNGERE IL GENERE		
		String query = "SELECT * FROM genere";
		
		Statement st = Connessione.getConnection().createStatement();
		ResultSet rs = st.executeQuery(query);
		Genere genere = null;
		
		while(rs.next()) {

			genere = new Genere();
			genere.setNome(rs.getString("nome"));
			genere.setSottoGenere(rs.getString("sottoGenere"));

		}
		return generi;
	}
}
