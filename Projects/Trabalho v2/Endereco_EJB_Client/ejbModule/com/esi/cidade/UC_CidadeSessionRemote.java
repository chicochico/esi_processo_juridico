package com.esi.cidade;

import geral.Cidade;

import javax.ejb.Remote;

@Remote
public interface UC_CidadeSessionRemote {

	public Cidade getCidadeWithNameAndidEstado(Cidade cidade) throws Exception;
	public Cidade getCidadeWithID(Cidade cidade) throws Exception;
	public Cidade addCidade(Cidade cidade) throws Exception;
	
}
