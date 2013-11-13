package com.esi.tramite;

import java.util.ArrayList;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import processo_juridico.Tramite;
import tramite.UC_TramiteManager;

@Stateless
@Remote(UC_TramiteSessionRemote.class)
public class UC_TramiteSession implements UC_TramiteSessionRemote {

    public UC_TramiteSession() {
    }

	@Override
	public ArrayList<Tramite> recuperarTodosTramites(int numeroProcesso) throws Exception {
		UC_TramiteManager tramiteManager = new UC_TramiteManager();
		return tramiteManager.recuperarTodosTramites(numeroProcesso);
	}

}
