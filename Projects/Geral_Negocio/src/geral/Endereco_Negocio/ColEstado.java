package geral.Endereco_Negocio;

import geral.Estado;
import infra.ConexaoBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ColEstado {
	private ConexaoBD conexao;
	private PreparedStatement insert, lastID;
	
	public ColEstado(ConexaoBD conexao) {
		this.conexao = conexao;
	}
	
	public Estado addEstado(Estado estado) throws Exception {
		this.validateName(estado);
		
		Estado exists = this.getEstadoWithNameAndSigla(estado);
		if(exists != null) {
			estado = exists;
		} else {
			String sql = "INSERT INTO Estado(nomeEstado, siglaEstado) VALUES (?, ?)";
			
			insert = (PreparedStatement) conexao.prepareStatement(sql);
			insert.setString(1, estado.getNome());
			insert.setString(2, estado.getSigla());
			insert.executeUpdate();
			
			lastID = (PreparedStatement) conexao.prepareStatement("SELECT MAX(idEstado) FROM Estado");
			
			ResultSet rs = lastID.executeQuery();
			if (rs.next())
				estado.setId(rs.getInt("MAX(idEstado)"));
			else
				throw new Exception("Loading ID Error");
		}
		return estado;
	}
	
	public Estado getEstadoWithID(Estado estado) throws Exception {
		String sql = "SELECT * FROM Estado WHERE idEstado = " + estado.getId();
		ResultSet rs = conexao.execSelect(sql);
		if (rs.next()) {
			estado.setNome(rs.getString("nomeEstado"));
			return estado;
		}
		return null;
	}
	
	public Estado getEstadoWithNameAndSigla(Estado estado) throws Exception {
		String sql = String.format("SELECT * FROM Estado WHERE nomeEstado = '%s' and siglaEstado = '%s'",
					 estado.getNome(),estado.getSigla());
		ResultSet rs = conexao.execSelect(sql);
		if (rs.next()) {
			estado.setId(rs.getInt("idEstado"));
			estado.setNome(rs.getString("nomeEstado"));
			estado.setSigla(rs.getString("siglaEstado"));
			return estado;
		}
		return null;
	}
	public void validateName(Estado estado) throws Exception {
		if ((estado.getNome() == null) || (estado.getNome().equals("")))
			throw new Exception("Missing Estado name");
		if ((estado.getSigla() == null) || (estado.getSigla().equals("")))
			throw new Exception("Missing Estado sigla");
	}
}
