package com.esi.endereco;

import geral.EnderecoEspecifico;

import java.util.ArrayList;

import javax.ejb.Remote;

@Remote
public interface UC_EnderecoSessionRemote {

	public EnderecoEspecifico obterEnderecoPorID(int id) throws Exception;
	public ArrayList<EnderecoEspecifico> obterEnderecoPorCEP(String cep) throws Exception;
	public EnderecoEspecifico getEnderecoWithAll(EnderecoEspecifico enderecoEspecifico) throws Exception;
	public EnderecoEspecifico addEndereco(EnderecoEspecifico endereco) throws Exception;
	
}
