package geral.Endereco_Negocio;

import geral.Logradouro;
import infra.ConexaoBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ColLogradouro {
	private ConexaoBD conexao;
	private PreparedStatement insert, lastID;
	
	public ColLogradouro(ConexaoBD conexao) {
		this.conexao = conexao;
	}
	
	public Logradouro addLogradouro(Logradouro logradouro) throws Exception {
		this.validateName(logradouro);

		Logradouro exists = this.getLogradouroWithName(logradouro);
		if(exists != null) {
			logradouro = exists;
		} else {
			String sql = "INSERT INTO Logradouro(tipoLogradouro) VALUES (?)";
			
			insert = (PreparedStatement) conexao.prepareStatement(sql);
			insert.setString(1, logradouro.getNome());
			insert.executeUpdate();
			
			lastID = (PreparedStatement) conexao.prepareStatement("SELECT MAX(idLogradouro) FROM Logradouro");
			
			ResultSet rs = lastID.executeQuery();
			if (rs.next())
				logradouro.setId(rs.getInt("MAX(idLogradouro)"));
			else
				throw new Exception("Loading ID Error");
		}
		return logradouro;
	}
	
	public Logradouro getLogradouroWithID(Logradouro logradouro) throws Exception {
		String sql = "SELECT * FROM Logradouro WHERE idLogradouro = " + logradouro.getId();
		ResultSet rs = conexao.execSelect(sql);
		if (rs.next()) {
			logradouro.setNome(rs.getString("nomeLogradouro"));
			return logradouro;
		}
		return null;
	}
	
	public Logradouro getLogradouroWithName(Logradouro logradouro) throws Exception {
		String sql = "SELECT * FROM Logradouro WHERE nomeLogradouro = '"+logradouro.getNome()+"'";
		ResultSet rs = conexao.execSelect(sql);
		if (rs.next()) {
			logradouro.setId(rs.getInt("idLogradouro"));
			logradouro.setNome(rs.getString("nomeLogradouro"));
			return logradouro;
		}
		return null;
	}
	public void validateName(Logradouro logradouro) throws Exception {
		if ((logradouro.getNome() == null) || (logradouro.getNome().equals("")))
			throw new Exception("Missing Logradouro name");
	}

}
