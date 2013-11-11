package geral;

import java.io.Serializable;

public class EnderecoEspecifico implements Serializable{
	public final static long serialVersionUID = 1;

	private int id;
	private Endereco endereco;

	public EnderecoEspecifico() {
		super();
		this.id = -1;
		this.endereco = new Endereco();
	}

	public EnderecoEspecifico(Endereco endereco) {
		super();
		this.endereco = endereco;
	}

	public EnderecoEspecifico(int id, int numero, String complemento,
			Endereco endereco) {
		super();
		this.id = id;
		this.endereco = endereco;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
