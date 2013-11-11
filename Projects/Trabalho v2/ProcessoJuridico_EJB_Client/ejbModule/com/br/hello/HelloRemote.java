package com.br.hello;

import javax.ejb.Remote;

import processo_juridico.Advogado;

@Remote
public interface HelloRemote {
	public String sayHello(String nome);
	public Advogado recuperarAdvogadoWithID(int id) throws Exception;
}
