package com.esi.logradouro;

import geral.Logradouro;

import javax.ejb.Remote;

@Remote
public interface UC_LogradouroSessionRemote {

	public Logradouro getLogradouroWithName(Logradouro logradouro) throws Exception;
	public Logradouro getLogradouroWithID(Logradouro logradouro) throws Exception;
	public Logradouro addLogradouro(Logradouro logradouro) throws Exception;
	
}
