package geral.Endereco_Negocio;

import geral.Endereco;
import geral.EnderecoEspecifico;
import infra.ConexaoBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ColEndereco {
	private ConexaoBD conexao;
	private PreparedStatement insert, lastID;
	
	public ColEndereco(ConexaoBD conexao) {
		this.conexao = conexao;
	}
	
	public EnderecoEspecifico addEndereco(EnderecoEspecifico endereco) throws Exception {
		this.validateEndereco(endereco);

		EnderecoEspecifico exists = this.getEnderecoWithAll(endereco);
		if(exists != null) {
			endereco = exists;
		} else {
			ColBairro colBairro = new ColBairro(conexao);
			ColCidade colCidade = new ColCidade(conexao);
			ColLogradouro colLogradouro = new ColLogradouro(conexao);
			ColRua colRua = new ColRua(conexao);
			
			endereco.getEndereco().setBairro(colBairro.addBairro(endereco.getEndereco().getBairro()));
			endereco.getEndereco().setCidade(colCidade.addCidade(endereco.getEndereco().getCidade()));
			endereco.getEndereco().setLogradouro(colLogradouro.addLogradouro(endereco.getEndereco().getLogradouro()));
			endereco.getEndereco().setRua(colRua.addRua(endereco.getEndereco().getRua()));
			
			String sql = "INSERT INTO Endereco(cep, numero, idCidade, idBairro, idRua, idLogradouro)" +
					" VALUES (?, ?, ?, ?, ?, ?)";
			
			insert = (PreparedStatement) conexao.prepareStatement(sql);
			insert.setString(1, endereco.getEndereco().getCep());
			insert.setInt(3, endereco.getEndereco().getCidade().getId());
			insert.setInt(4, endereco.getEndereco().getBairro().getId());
			insert.setInt(5, endereco.getEndereco().getRua().getId());
			insert.setInt(6, endereco.getEndereco().getLogradouro().getId());
			insert.executeUpdate();
			
			lastID = (PreparedStatement) conexao.prepareStatement("SELECT MAX(idEndereco) FROM Endereco");
			
			ResultSet rs = lastID.executeQuery();
			if (rs.next()) {
				endereco.setId(rs.getInt("MAX(idEndereco)"));
			} else  {
				throw new Exception("Loading ID Error");
			}
		}
		return endereco;
	}
	
	public EnderecoEspecifico getEnderecoWithAll(EnderecoEspecifico enderecoEspecifico) throws Exception {
		ColBairro colBairro = new ColBairro(conexao);
		ColCidade colCidade = new ColCidade(conexao);
		ColLogradouro colLogradouro = new ColLogradouro(conexao);
		ColRua colRua = new ColRua(conexao);
		
		Endereco endereco = enderecoEspecifico.getEndereco();
		
		String sql = String.format("SELECT * FROM Endereco WHERE cep = '%s'" +
				" AND idCidade = %d AND idBairro = %d AND idRua = %d AND idLogradouro = %d",
				endereco.getCep(), endereco.getCidade().getId(), endereco.getBairro().getId(),
				endereco.getRua().getId(), endereco.getLogradouro().getId());
		
		
		ResultSet rs = conexao.execSelect(sql);
		if (rs.next()) {
			enderecoEspecifico.setId(rs.getInt("idEndereco"));
			endereco.setCep(rs.getString("cep"));
			endereco.getBairro().setId(rs.getInt("idBairro"));
			endereco.getCidade().setId(rs.getInt("idCidade"));
			endereco.getRua().setId(rs.getInt("idRua"));
			endereco.getLogradouro().setId(rs.getInt("idLogradouro"));
			
			endereco.setBairro(colBairro.getBairroWithID(endereco.getBairro()));
			endereco.setCidade(colCidade.getCidadeWithID(endereco.getCidade()));
			endereco.setRua(colRua.getRuaWithID(endereco.getRua()));
			endereco.setLogradouro(colLogradouro.getLogradouroWithID(endereco.getLogradouro()));
			enderecoEspecifico.setEndereco(endereco);
			return enderecoEspecifico;
		}
		return null;
	}
	
	public EnderecoEspecifico getEnderecoWithID(int id) throws Exception {
		ColBairro colBairro = new ColBairro(conexao);
		ColCidade colCidade = new ColCidade(conexao);
		ColLogradouro colLogradouro = new ColLogradouro(conexao);
		ColRua colRua = new ColRua(conexao);
		
		String sql = String.format("SELECT * FROM Endereco WHERE idEndereco = %d", id);
		ResultSet rs = conexao.execSelect(sql);
		EnderecoEspecifico endereco = null;
		if (rs.next()) {
			endereco = new EnderecoEspecifico();
			endereco.setId(id);
			endereco.getEndereco().setCep(rs.getString("cep"));
			endereco.getEndereco().getBairro().setId(rs.getInt("idBairro"));
			endereco.getEndereco().getCidade().setId(rs.getInt("idCidade"));
			endereco.getEndereco().getRua().setId(rs.getInt("idRua"));
			endereco.getEndereco().getLogradouro().setId(rs.getInt("idLogradouro"));
			
			endereco.getEndereco().setBairro(colBairro.getBairroWithID(endereco.getEndereco().getBairro()));
			endereco.getEndereco().setCidade(colCidade.getCidadeWithID(endereco.getEndereco().getCidade()));
			endereco.getEndereco().setRua(colRua.getRuaWithID(endereco.getEndereco().getRua()));
			endereco.getEndereco().setLogradouro(colLogradouro.getLogradouroWithID(endereco.getEndereco().getLogradouro()));
		}
		return endereco;
	}
	
	public ArrayList<EnderecoEspecifico> getEnderecoWithCEP(String cep) throws Exception {
		ColBairro colBairro = new ColBairro(conexao);
		ColCidade colCidade = new ColCidade(conexao);
		ColLogradouro colLogradouro = new ColLogradouro(conexao);
		ColRua colRua = new ColRua(conexao);
		
		String sql = String.format("SELECT * FROM Endereco WHERE cep = '%s'", cep);
		ResultSet rs = conexao.execSelect(sql);
		ArrayList<EnderecoEspecifico> enderecos = new ArrayList<EnderecoEspecifico>();
		
		while (rs.next()) {
			EnderecoEspecifico endereco = new EnderecoEspecifico();
			endereco.setId(rs.getInt("idEndereco"));
			endereco.getEndereco().setCep(rs.getString("cep"));
			endereco.getEndereco().getBairro().setId(rs.getInt("idBairro"));
			endereco.getEndereco().getCidade().setId(rs.getInt("idCidade"));
			endereco.getEndereco().getRua().setId(rs.getInt("idRua"));
			endereco.getEndereco().getLogradouro().setId(rs.getInt("idLogradouro"));
			
			endereco.getEndereco().setBairro(colBairro.getBairroWithID(endereco.getEndereco().getBairro()));
			endereco.getEndereco().setCidade(colCidade.getCidadeWithID(endereco.getEndereco().getCidade()));
			endereco.getEndereco().setRua(colRua.getRuaWithID(endereco.getEndereco().getRua()));
			endereco.getEndereco().setLogradouro(colLogradouro.getLogradouroWithID(endereco.getEndereco().getLogradouro()));
			enderecos.add(endereco);
		}
		return enderecos;
	}

	private void validateEndereco(EnderecoEspecifico endereco) throws Exception {
		ColBairro colBairro = new ColBairro(conexao);
		ColCidade colCidade = new ColCidade(conexao);
		ColLogradouro colLogradouro = new ColLogradouro(conexao);
		ColRua colRua = new ColRua(conexao);
		
		colBairro.validateName(endereco.getEndereco().getBairro());
		colCidade.validateName(endereco.getEndereco().getCidade());
		colLogradouro.validateName(endereco.getEndereco().getLogradouro());
		colRua.validateName(endereco.getEndereco().getRua());
		
		if ((endereco.getEndereco().getCep() == null) || (endereco.getEndereco().getCep() == ""))
			throw new Exception("Missing CEP");
	}
}
