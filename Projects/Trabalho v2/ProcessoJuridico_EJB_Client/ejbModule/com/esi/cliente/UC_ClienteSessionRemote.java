package com.esi.cliente;

import java.util.ArrayList;

import javax.ejb.Remote;

import processo_juridico.Cliente;

@Remote
public interface UC_ClienteSessionRemote {

	public ArrayList<Cliente> recuperarTodosClientes() throws Exception;
	public Cliente recuperarClienteWithID(int id) throws Exception;
}
