package geral;

import java.io.Serializable;

public class Estado implements Serializable{
	public final static long serialVersionUID = 1;

	private int id;
	private String nome;
	private String sigla;
	
	public Estado() {
		super();
		this.id = -1;
		this.nome = "";
		this.sigla = "";
	}
	
	public Estado(String nome, String sigla) {
		super();
		this.nome = nome;
		this.sigla = sigla;
	}
	
	public Estado(int id, String nome, String sigla) {
		super();
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
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
	
	public String getSigla() {
		return sigla;
	}
	
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
}
