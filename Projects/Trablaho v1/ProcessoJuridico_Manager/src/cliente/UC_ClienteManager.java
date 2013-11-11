package cliente;

import infra.ConexaoBD;
import infra.GlobalApp;

import java.util.ArrayList;

import processo_juridico.Cliente;
import col_cliente.Col_Cliente;

public class UC_ClienteManager {
	public Cliente recuperarClienteWithID(int id) throws Exception {
		ConexaoBD conexao = GlobalApp.getConexaoBD();
		Col_Cliente colCliente = new Col_Cliente(conexao);
		Cliente cliente = colCliente.recuperarClienteWithID(id);
		conexao.close();
		return cliente;
	}
	
	public ArrayList<Cliente> recuperarTodosClientes() throws Exception {
		ConexaoBD conexao = GlobalApp.getConexaoBD();
		Col_Cliente colCliente = new Col_Cliente(conexao);
		ArrayList<Cliente> clientes = colCliente.recuperarTodosClientes();
		conexao.close();
		return clientes;
	}
}
