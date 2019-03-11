package com.exolab.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.exolab.dto.DtoEditori;
import com.exolab.model.Editore;
import com.exolab.utility.Connessione;

public class EditoreDao {

	public boolean insert(Editore editore) throws SQLException {

		String query;
		PreparedStatement st;

		query = "INSERT INTO editore(nome, indirizzo, sito) VALUE( ?, ?, ?);";
		st = Connessione.getConnection().prepareStatement(query);
		st.setString(1, editore.getNome());
		st.setString(2, editore.getIndirizzo());
		st.setString(3, editore.getSito());
		
//		System.out.println("-----------");
//		System.out.println(editore.toString());
//		System.out.println("-----------");
		return st.execute();
	}
	
	public boolean delete(int id) throws SQLException {

		String query;
		Statement st;

		query = "DELETE FROM editore WHERE id =  " + id;
		st = Connessione.getConnection().prepareStatement(query);
		return st.executeUpdate(query) > 0;
	}
	
	public Editore findByParams(String campo, String valore) throws SQLException {

		Editore editore = new Editore();

		Statement st;
		ResultSet rs;

		
		String query = "SELECT * FROM editore WHERE " + campo + " = '" + valore + "'";
		
		st = Connessione.getConnection().createStatement();
		rs = st.executeQuery(query);
//		st.setString(1, campo);
//		st.setString(2, valore);
		if(rs.next()) {
		editore.setNome(rs.getString("nome"));
		editore.setIndirizzo(rs.getString("indirizzo"));
		editore.setSito(rs.getString("sito"));
		editore.setId(rs.getInt("id"));
		}
		return editore;
	}
	
	public boolean update(Editore editore, int id) throws SQLException {

		String query = "UPDATE editore SET nome = ?, indirizzo = ?, sito = ? WHERE id = ?";
		PreparedStatement st = Connessione.getConnection().prepareStatement(query);
		st.setString(1, editore.getNome());
		st.setString(2, editore.getIndirizzo());
		st.setString(3, editore.getSito());
		st.setInt(4, id);
		
		return st.executeUpdate() > 0;
	}
	
	public List<DtoEditori> selectAll() throws SQLException {

		List<DtoEditori> editori = new ArrayList<>();
		
		String query = "SELECT * FROM editore;";
		
		Statement st = Connessione.getConnection().createStatement();
		ResultSet rs = st.executeQuery(query);
		
		DtoEditori editore;
		
		while(rs.next()) {
			
			editore = new DtoEditori();
			editore.setId(rs.getInt("id"));
			editore.setNome(rs.getString("nome"));
			editore.setIndirizzo(rs.getString("indirizzo"));
			editore.setSito(rs.getString("sito"));
			editori.add(editore);
		}
		return editori;
	}
}
