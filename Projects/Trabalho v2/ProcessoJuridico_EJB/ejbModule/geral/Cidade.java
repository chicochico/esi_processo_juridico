package geral;

import java.io.Serializable;

public class Cidade implements Serializable{
	public final static long serialVersionUID = 1;

	private int id;
	private String nome;
	private Estado estado;
	
	public Cidade() {
		super();
		this.id = -1;
		this.nome = "";
		this.setEstado(new Estado());
	}

	public Cidade(String nome, Estado estado) {
		super();
		this.nome = nome;
		this.setEstado(estado);
	}

	public Cidade(int id, String nome, Estado estado) {
		super();
		this.id = id;
		this.nome = nome;
		this.setEstado(estado);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
