package com.esi.estado;

import geral.Estado;

import javax.ejb.Remote;

@Remote
public interface UC_EstadoSessionRemote {
	
	public Estado getEstadoWithNameAndSigla(Estado estado) throws Exception;
	public Estado getEstadoWithID(Estado estado) throws Exception;
	public Estado addEstado(Estado estado) throws Exception;
	
}
