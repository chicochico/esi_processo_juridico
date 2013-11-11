package processo_juridico;

import java.io.Serializable;

import geral.Pessoa;

public class Cliente implements Serializable{
	public final static long serialVersionUID = 1;

	private int id;
	private Pessoa  pessoa;
	
	public Cliente() {
		super();
		this.id = -1;
		this.pessoa = null;
	}

	public Cliente(int id, Pessoa pessoa) {
		super();
		this.id = id;
		this.pessoa = pessoa;
	}

	public Cliente(Pessoa pessoa) {
		super();
		this.pessoa = pessoa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
