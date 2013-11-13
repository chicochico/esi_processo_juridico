package col_forum;

import infra.ConexaoBD;

import java.sql.ResultSet;
import java.util.ArrayList;

import processo_juridico.Forum;

public class Col_Forum {
	private ConexaoBD conexao;

	public Col_Forum() {
		super();
		this.conexao = null;
	}

	public Col_Forum(ConexaoBD conexao) {
		super();
		this.conexao = conexao;
	}
	
	public Forum recuperarForumWithID(int id) throws Exception {
		String sql = String.format("SELECT * FROM Forum WHERE idForum = %d", id);
		ResultSet rs = conexao.execSelect(sql);
		Forum forum = new Forum();
		if (rs.next()) {
			forum.setId(id);
			forum.setNome(rs.getString("nomeForum"));
			forum.setCnpj(rs.getString("cnpj"));
			forum.getEnderecoEspecifico().setId(rs.getInt("idEndereco"));
		}
		return forum;
	}
	
	public ArrayList<Forum> recuperarTodosForuns() throws Exception {
		String sql = "SELECT * FROM Forum";
		ResultSet rs = conexao.execSelect(sql);
		ArrayList<Forum> foruns = new ArrayList<Forum>();
		
		while (rs.next()) {
			Forum forum = new Forum();
			forum.setId(rs.getInt("idForum"));
			forum.setNome(rs.getString("nomeForum"));
			forum.setCnpj(rs.getString("cnpj"));
			forum.getEnderecoEspecifico().setId(rs.getInt("idEndereco"));
			foruns.add(forum);
		}
		return foruns;
	}
}
