package geral.Endereco_Negocio;

import geral.Cidade;
import geral.Estado;

import infra.ConexaoBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ColCidade {
	
	private ConexaoBD conexao;
	private PreparedStatement insert, lastID;
	
	public ColCidade(ConexaoBD conexao) {
		this.conexao = conexao;
	}
	
	public Cidade addCidade(Cidade cidade) throws Exception {
		this.validateName(cidade);
		this.validateEstado(cidade);
		
		Cidade exists = this.getCidadeWithNameAndidEstado(cidade);
		if(exists != null) {
			cidade = exists;
		} else {
			String sql = "INSERT INTO Cidade(nomeCidade, idEstado) VALUES (?, ?)";
			
			insert = (PreparedStatement) conexao.prepareStatement(sql);
			insert.setString(1, cidade.getNome());
			insert.setInt(2, cidade.getEstado().getId());
			insert.executeUpdate();
			
			lastID = (PreparedStatement) conexao.prepareStatement("SELECT MAX(idCidade) FROM Cidade");
			
			ResultSet rs = lastID.executeQuery();
			if (rs.next()) {
				cidade.setId(rs.getInt("MAX(idCidade)"));
			} else  {
				throw new Exception("Loading ID Error");
			}
		}
		return cidade;
	}
	
	public Cidade getCidadeWithID(Cidade cidade) throws Exception {
		String sql = "SELECT * FROM Cidade WHERE idCidade = " + cidade.getId();
		ResultSet rs = conexao.execSelect(sql);
		if (rs.next()) {
			cidade.setId(cidade.getId());
			cidade.setNome(rs.getString("nomeCidade"));
			ColEstado colEstado = new ColEstado(conexao);
			Estado estado = new Estado();
			estado.setId(rs.getInt("idEstado"));
			cidade.setEstado(colEstado.getEstadoWithID(estado));
			return cidade;
		}
		return null;
	}
	
	public Cidade getCidadeWithNameAndidEstado(Cidade cidade) throws Exception {
		String sql = String.format("SELECT * FROM Cidade WHERE nomeCidade = '%s' AND idEstado = %d", 
				cidade.getNome(), cidade.getEstado().getId());

		ResultSet rs = conexao.execSelect(sql);
		if (rs.next()) {
			cidade.setId(rs.getInt("idCidade"));
			cidade.setNome(rs.getString("nomeCidade"));
			ColEstado colEstado = new ColEstado(conexao);
			Estado estado = new Estado();
			estado.setId(rs.getInt("idEstado"));
			cidade.setEstado(colEstado.getEstadoWithID(estado));
			return cidade;
		}
		return null;
	}
	
	public void validateName(Cidade cidade) throws Exception {
		if ((cidade.getNome() == null) || (cidade.getNome().equals("")))
			throw new Exception("Missing Cidade name");
	}
	
	public Estado validateEstado(Cidade cidade) throws Exception {
		ColEstado estado = new ColEstado(conexao);
		if (cidade.getEstado() != null) {
			try {
				return estado.getEstadoWithID(cidade.getEstado());
			} catch (Exception error) {
				throw new Exception("Estado don't exists");
			}
		}
		return null;
	}
}
