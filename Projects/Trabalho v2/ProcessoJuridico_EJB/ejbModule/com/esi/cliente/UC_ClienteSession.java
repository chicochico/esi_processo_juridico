package com.esi.cliente;

import java.util.ArrayList;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import cliente.UC_ClienteManager;
import processo_juridico.Cliente;


@Stateless
@Remote(UC_ClienteSessionRemote.class)
public class UC_ClienteSession implements UC_ClienteSessionRemote {

    public UC_ClienteSession() {
    }

	@Override
	public ArrayList<Cliente> recuperarTodosClientes() throws Exception {
		UC_ClienteManager clienteManager = new UC_ClienteManager();
		return clienteManager.recuperarTodosClientes();
	}

	@Override
	public Cliente recuperarClienteWithID(int id) throws Exception {
		UC_ClienteManager clienteManager = new UC_ClienteManager();
		return clienteManager.recuperarClienteWithID(id);
	}

}
