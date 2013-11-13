package com.esi.forum;

import java.util.ArrayList;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import forum.UC_ForumManager;
import processo_juridico.Forum;

@Stateless
@Remote(UC_ForumSessionRemote.class)
public class UC_ForumSession implements UC_ForumSessionRemote {

    public UC_ForumSession() {
    }

	@Override
	public ArrayList<Forum> recuperarTodosForuns() throws Exception {
		UC_ForumManager forumManager = new UC_ForumManager();
		return forumManager.recuperarTodosForuns();
	}

	@Override
	public Forum recuperarForumWithID(int id) throws Exception {
		UC_ForumManager forumManager = new UC_ForumManager();
		return forumManager.recuperarForumWithID(id);
	}

}
