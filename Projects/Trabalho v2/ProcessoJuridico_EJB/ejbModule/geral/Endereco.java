package geral;

import java.io.Serializable;

public class Endereco implements Serializable{
	public final static long serialVersionUID = 1;

	private String cep;
	private Bairro bairro;
	private Cidade cidade;
	private Rua rua;
	private Logradouro logradouro;
	
	public Endereco() {
		super();
		this.cep = "";
		this.bairro = new Bairro();
		this.cidade = new Cidade();
		this.rua = new Rua();
		this.logradouro = new Logradouro();
	}
	
	public Endereco(String cep, Bairro bairro, Cidade cidade, Rua rua,
			Logradouro logradouro) {
		super();
		this.cep = cep;
		this.bairro = bairro;
		this.cidade = cidade;
		this.rua = rua;
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Rua getRua() {
		return rua;
	}

	public void setRua(Rua rua) {
		this.rua = rua;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
