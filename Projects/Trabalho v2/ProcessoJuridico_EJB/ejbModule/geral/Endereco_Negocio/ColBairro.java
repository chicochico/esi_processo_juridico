package geral.Endereco_Negocio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import geral.Bairro;
import infra.ConexaoBD;

public class ColBairro {

	private ConexaoBD conexao;
	private PreparedStatement insert, lastID;
	
	public ColBairro(){
	}
	
	public ColBairro(ConexaoBD conexao) {
		this.conexao = conexao;
	}
	
	public Bairro addBairro(Bairro bairro) throws Exception {
		this.validateName(bairro);
		Bairro exists = this.getBairroWithName(bairro);
		if(exists != null) {
			bairro = exists;
		} else {
			String sql = "INSERT INTO Bairro(nomeBairro) VALUES (?)";
			
			insert = (PreparedStatement) conexao.prepareStatement(sql);
			insert.setString(1, bairro.getNome());
			insert.executeUpdate();
			lastID = (PreparedStatement) conexao.prepareStatement("SELECT MAX(idBairro) FROM Bairro");
			
			ResultSet rs = lastID.executeQuery();
			if (rs.next()) 
				bairro.setId(rs.getInt("MAX(idBairro)"));
		}
		return bairro;
	}
	
	public Bairro getBairroWithID(Bairro bairro) throws Exception {
		String sql = "SELECT * FROM Bairro WHERE idBairro = " + bairro.getId();
		ResultSet rs = conexao.execSelect(sql);
		if (rs.next()) {
			bairro.setNome(rs.getString("nomeBairro"));
			return bairro;
		}
		return null;
	}
	
	public Bairro getBairroWithName(Bairro bairro) throws Exception {
		String sql = "SELECT * FROM Bairro WHERE nomeBairro = '"+bairro.getNome()+"'";
		ResultSet rs = conexao.execSelect(sql);
		if (rs.next()) {
			bairro.setId(rs.getInt("idBairro"));
			bairro.setNome(rs.getString("nomeBairro"));
			return bairro;
		}
		return null;
	}
		
	public void validateName(Bairro bairro) throws Exception {
		if ((bairro.getNome() == null) || (bairro.getNome().equals("")))
			throw new Exception("Missing Bairro name");
	}
}
