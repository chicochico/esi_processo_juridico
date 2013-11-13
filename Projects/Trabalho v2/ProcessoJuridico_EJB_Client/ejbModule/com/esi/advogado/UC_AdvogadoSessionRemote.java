package com.esi.advogado;

import java.util.ArrayList;

import javax.ejb.Remote;

import processo_juridico.Advogado;


@Remote
public interface UC_AdvogadoSessionRemote {
	public Advogado obterAdvogado(int id) throws Exception;
	public ArrayList<Advogado> recuperarTodosAdvogados() throws Exception;
}
