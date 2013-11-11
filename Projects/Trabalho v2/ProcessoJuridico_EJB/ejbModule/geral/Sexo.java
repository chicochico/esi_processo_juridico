package geral;

import java.io.Serializable;

public class Sexo implements Serializable{
	public final static long serialVersionUID = 1;

	private String nome;

	public Sexo() {
		super();
		this.nome = "";
	}

	public Sexo(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
