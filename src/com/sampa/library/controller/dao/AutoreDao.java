package com.sampa.library.controller.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sampa.library.controller.dto.DtoAutori;
import com.sampa.library.controller.mappers.AutoreMapper;
import com.sampa.library.controller.mybatis.SqlMapFactory;
import com.sampa.library.models.pojos.Autore;


public class AutoreDao implements AutoreMapper, Serializable{
	
	private static final long serialVersionUID = -2444855428170355257L;

	private static AutoreMapper getMapper() {
		return SqlMapFactory.instance().getMapper(AutoreMapper.class);
	}

	@Override
	public void insert(Autore autore) {
		
		SqlMapFactory.instance().openSession();
		AutoreDao.getMapper().insert(autore);
		SqlMapFactory.instance().closeSession();
	}

	@Override
	public Autore getByName(String nome) {
		SqlMapFactory.instance().openSession();
		Autore autore = AutoreDao.getMapper().getByName(nome);
		SqlMapFactory.instance().closeSession();
		return autore;
	}

	@Override
	public List<DtoAutori> getAll() {
		List<DtoAutori> lista = new ArrayList<DtoAutori>();
		SqlMapFactory.instance().openSession();
		AutoreDao.getMapper().getAll();
		SqlMapFactory.instance().closeSession();
		return lista;
	}

	@Override
	public void delete(int id) {
		SqlMapFactory.instance().openSession();
		AutoreDao.getMapper().delete(id);
		SqlMapFactory.instance().closeSession();
	}

	@Override
	public void update(Autore autore) {
		SqlMapFactory.instance().openSession();
		AutoreDao.getMapper().update(autore);
		SqlMapFactory.instance().closeSession();
	}

//	public boolean insert(Autore autore) throws SQLException {
//
//		String query;
//		PreparedStatement st;
//
//		query = "INSERT INTO autore(nome, cognome,anno_nascita,nazionalita) VALUE( ?, ?, ?, ?);";
//		st = Connessione.getConnection().prepareStatement(query);
//		st.setString(1, autore.getNome());
//		st.setString(2, autore.getCognome());
//		st.setInt(3, autore.getAnnoDiNascita());
//		st.setString(4, autore.getNazionalita());
////		System.out.println("-----------");
////		System.out.println(autore.toString());
////		System.out.println("-----------");
//		return st.execute();
//	}
//
//	public boolean delete(int id) throws SQLException {
//
//		String query;
//		Statement st;
//
//		query = "DELETE FROM autore WHERE id = " + id + ";";
//		st = Connessione.getConnection().prepareStatement(query);
//		return st.executeUpdate(query) > 0;
//	}
//
//	public Autore findByParams(String campo, String valore) throws SQLException {
//
//		Autore autore = new Autore();
//;
//
//		Statement st;
//		ResultSet rs;
//
//
//		String query = "SELECT a.nome,a.cognome, a.nazionalita, a.anno_nascita,a.id FROM autore a WHERE " +campo+" = '"+ valore + "'";
//		st = Connessione.getConnection().createStatement();
//		
//		rs = st.executeQuery(query);
//		if(rs.next()) {
//
//			
//		autore.setNome(rs.getString("nome"));
//		autore.setCognome(rs.getString("cognome"));
//		autore.setAnnoDiNascita(rs.getInt("anno_nascita"));
//		autore.setNazionalita(rs.getString("nazionalita"));
//		autore.setId(rs.getInt("id"));
//		}
//		return autore;
//	}
//
//	public boolean update(Autore autore, int id) throws SQLException {
//
//		String query = "UPDATE autore SET nome = ?, cognome = ?, anno_nascita = ?,nazionalita = ? WHERE id = ?";
//		PreparedStatement st = Connessione.getConnection().prepareStatement(query);
//		st.setString(1, autore.getNome());
//		st.setString(2, autore.getCognome());
//		st.setInt(3, autore.getAnnoDiNascita());
//		st.setString(4, autore.getNazionalita());
//		st.setLong(5, id);
//		
//		return st.executeUpdate() > 0;
//	}
//
//	public List<DtoAutori> selectAll() throws SQLException {
//
//		List<DtoAutori> autori = new ArrayList<>();
//
//		String query = "SELECT * FROM autore;";
//
//		Statement st = Connessione.getConnection().createStatement();
//		ResultSet rs = st.executeQuery(query);
//
//		DtoAutori autore;
//
//		while(rs.next()) {
//
//			autore = new DtoAutori();
//			autore.setCognome(rs.getString("cognome"));
//			autore.setEta(rs.getInt("anno_nascita"));
//			autore.setNazionalita(rs.getString("nazionalita"));
//			autore.setNome(rs.getString("nome"));
//			autore.setId(rs.getInt("id"));
//			autore.setNomeCompleto();
//			autori.add(autore);
//		}
//		return autori;
//	}
}
