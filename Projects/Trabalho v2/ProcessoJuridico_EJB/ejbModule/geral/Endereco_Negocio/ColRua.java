package geral.Endereco_Negocio;

import geral.Rua;
import infra.ConexaoBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ColRua {
	private ConexaoBD conexao;
	private PreparedStatement insert, lastID;
	
	public ColRua(){
	}
	
	public ColRua(ConexaoBD conexao) {
		this.conexao = conexao;
	}
	
	public Rua addRua(Rua rua) throws Exception {
		this.validateName(rua);
		
		Rua exists = this.getRuaWithName(rua);
		if(exists != null) {
			rua = exists;
		} else {
			String sql = "INSERT INTO Rua(nomeRua) VALUES (?)";
			
			insert = (PreparedStatement) conexao.prepareStatement(sql);
			insert.setString(1, rua.getNome());
			insert.executeUpdate();
			
			lastID = (PreparedStatement) conexao.prepareStatement("SELECT MAX(idRua) FROM Rua");
			
			ResultSet rs = lastID.executeQuery();
			if (rs.next())
				rua.setId(rs.getInt("MAX(idRua)"));
			else
				throw new Exception("Loading ID Error");
		}
		return rua;
	}
	
	public Rua getRuaWithID(Rua rua) throws Exception {
		String sql = "SELECT * FROM Rua WHERE idRua = " + rua.getId();
		ResultSet rs = conexao.execSelect(sql);
		if (rs.next()) {
			rua.setNome(rs.getString("nomeRua"));
			return rua;
		}
		return null;
	}
	
	public Rua getRuaWithName(Rua rua) throws Exception {
		String sql = "SELECT * FROM Rua WHERE nomeRua = '"+rua.getNome()+"'";
		ResultSet rs = conexao.execSelect(sql);
		if (rs.next()) {
			rua.setId(rs.getInt("idRua"));
			rua.setNome(rs.getString("nomeRua"));
			return rua;
		}
		return null;
	}
	
	public void validateName(Rua rua) throws Exception {
		if ((rua.getNome() == null) || (rua.getNome().equals("")))
			throw new Exception("Missing Rua name");
	}
}
