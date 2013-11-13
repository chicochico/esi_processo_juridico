package com.esi.rua;

import geral.Rua;

import javax.ejb.Remote;

@Remote
public interface UC_RuaSessionRemote {

	public Rua getRuaWithName(Rua rua) throws Exception;
	public Rua getRuaWithID(Rua rua) throws Exception;
	public Rua addRua(Rua rua) throws Exception;
	
}
