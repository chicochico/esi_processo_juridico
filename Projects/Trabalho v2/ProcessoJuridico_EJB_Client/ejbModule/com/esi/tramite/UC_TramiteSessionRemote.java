package com.esi.tramite;

import java.util.ArrayList;

import javax.ejb.Remote;

import processo_juridico.Tramite;

@Remote
public interface UC_TramiteSessionRemote {
	public ArrayList<Tramite> recuperarTodosTramites(int numeroProcesso) throws Exception;
}
