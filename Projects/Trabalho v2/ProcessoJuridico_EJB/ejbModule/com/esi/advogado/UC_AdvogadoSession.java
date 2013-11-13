package com.esi.advogado;

import java.util.ArrayList;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import advogado.UC_AdvogadoManager;
import processo_juridico.Advogado;

@Stateless
@Remote(UC_AdvogadoSessionRemote.class)
public class UC_AdvogadoSession implements UC_AdvogadoSessionRemote {

    public UC_AdvogadoSession() {
    }

	@Override
	public Advogado obterAdvogado(int id) throws Exception {
		UC_AdvogadoManager advogadoManager = new UC_AdvogadoManager();
		return advogadoManager.recuperarAdvogadoWithID(id);
	}

	@Override
	public ArrayList<Advogado> recuperarTodosAdvogados() throws Exception {
		UC_AdvogadoManager advogadoManager = new UC_AdvogadoManager();
		return advogadoManager.recuperarTodosAdvogados();
	}


}
