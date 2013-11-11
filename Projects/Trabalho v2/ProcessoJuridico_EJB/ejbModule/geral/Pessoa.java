package geral;

import java.io.Serializable;
import java.util.Date;

public abstract class Pessoa  implements Serializable{
	public final static long serialVersionUID = 1;

	private String nome;
	private Date nascimento;
	private Telefone telefone;
	private EnderecoEspecifico enderecoEspecifico;
	
	public Pessoa() {
		super();
		this.nome = "";
		this.nascimento = null;
		this.telefone = new Telefone();
		this.enderecoEspecifico = new EnderecoEspecifico();
	}

	public Pessoa(String nome, Date nascimento, Telefone telefone,
			EnderecoEspecifico enderecoEspecifico) {
		super();
		this.nome = nome;
		this.nascimento = nascimento;
		this.telefone = telefone;
		this.enderecoEspecifico = enderecoEspecifico;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public EnderecoEspecifico getEnderecoEspecifico() {
		return enderecoEspecifico;
	}

	public void setEnderecoEspecifico(EnderecoEspecifico enderecoEspecifico) {
		this.enderecoEspecifico = enderecoEspecifico;
	}	
}
