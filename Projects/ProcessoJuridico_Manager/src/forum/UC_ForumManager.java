package forum;

import infra.ConexaoBD;
import infra.GlobalApp;

import java.util.ArrayList;

import processo_juridico.Forum;
import col_forum.Col_Forum;

public class UC_ForumManager {
	public Forum recuperarForumWithID(int id) throws Exception {
		ConexaoBD conexao = GlobalApp.getConexaoBD();
		Col_Forum colForum = new Col_Forum(conexao);
		Forum forum = colForum.recuperarForumWithID(id);
		conexao.close();
		return forum;
	}
	
	public ArrayList<Forum> recuperarTodosForuns() throws Exception {
		ConexaoBD conexao = GlobalApp.getConexaoBD();
		Col_Forum colForum = new Col_Forum(conexao);
		ArrayList<Forum> foruns = colForum.recuperarTodosForuns();
		conexao.close();
		return foruns;
	}
}
