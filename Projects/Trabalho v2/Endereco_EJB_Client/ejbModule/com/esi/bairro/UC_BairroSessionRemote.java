package com.esi.bairro;

import geral.Bairro;

import javax.ejb.Remote;

@Remote
public interface UC_BairroSessionRemote {
	public Bairro getBairroWithName(Bairro bairro) throws Exception;
	public Bairro getBairroWithID(Bairro bairro) throws Exception;
	public Bairro addBairro(Bairro bairro) throws Exception;
}
