package col_cliente;

import geral.PessoaFisica;
import geral.PessoaJuridica;
import geral.Sexo;
import infra.ConexaoBD;

import java.sql.ResultSet;
import java.util.ArrayList;

import processo_juridico.Cliente;

public class Col_Cliente {
	private ConexaoBD conexao;

	public Col_Cliente() {
		super();
		this.conexao = null;
	}

	public Col_Cliente(ConexaoBD conexao) {
		super();
		this.conexao = conexao;
	}
	
	public Cliente recuperarClienteWithID(int id) throws Exception {
		String sql = String.format("SELECT * FROM Cliente WHERE idCliente = %d", id);
		ResultSet rs = conexao.execSelect(sql);
		Cliente cliente = new Cliente();
		if (rs.next()) {
			cliente.setId(id);
			if (rs.getString("cnpj") == null) {
				PessoaFisica pf = new PessoaFisica();
				pf.setCpf(rs.getString("cpf"));
				pf.setRg(rs.getString("rg"));
				pf.setSexo(new Sexo(rs.getString("sexo")));
				cliente.setPessoa((PessoaFisica)pf);
			} else {
				PessoaJuridica pj = new PessoaJuridica();
				pj.setCnpj(rs.getString("cnpj"));
				cliente.setPessoa((PessoaJuridica)pj);
			}
			cliente.getPessoa().setNome(rs.getString("nomeCliente"));
			cliente.getPessoa().setNascimento(rs.getDate("nascimento"));
			cliente.getPessoa().getEnderecoEspecifico().setId(rs.getInt("idEndereco"));
		}
		return cliente;
	}
	
	
	public ArrayList<Cliente> recuperarTodosClientes() throws Exception {
		String sql = "SELECT * FROM Cliente";
		ResultSet rs = conexao.execSelect(sql);
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		while (rs.next()) {
			Cliente cliente = new Cliente();
			cliente.setId(rs.getInt("idCliente"));
			if (rs.getString("cnpj") == null) {
				PessoaFisica pf = new PessoaFisica();
				pf.setCpf(rs.getString("cpf"));
				pf.setRg(rs.getString("rg"));
				pf.setSexo(new Sexo(rs.getString("sexo")));
				cliente.setPessoa((PessoaFisica)pf);
			} else {
				PessoaJuridica pj = new PessoaJuridica();
				pj.setCnpj(rs.getString("cnpj"));
				cliente.setPessoa((PessoaJuridica)pj);
			}
			cliente.getPessoa().setNome(rs.getString("nomeCliente"));
			cliente.getPessoa().setNascimento(rs.getDate("nascimento"));
			cliente.getPessoa().getEnderecoEspecifico().setId(rs.getInt("idEndereco"));
			clientes.add(cliente);
		}
		return clientes;
	}
}
