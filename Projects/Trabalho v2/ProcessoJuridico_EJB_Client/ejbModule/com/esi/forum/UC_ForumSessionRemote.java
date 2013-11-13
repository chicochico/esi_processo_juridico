package com.esi.forum;

import java.util.ArrayList;

import javax.ejb.Remote;

import processo_juridico.Forum;

@Remote
public interface UC_ForumSessionRemote {
	public ArrayList<Forum> recuperarTodosForuns() throws Exception;
	public Forum recuperarForumWithID(int id) throws Exception;
}
