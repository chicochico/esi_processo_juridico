package geral;

import java.io.Serializable;
import java.util.Date;

public class PessoaJuridica extends Pessoa implements Serializable{
	public final static long serialVersionUID = 1;

	private String cnpj;

	public PessoaJuridica() {
		super();
		this.cnpj = "";
	}

	public PessoaJuridica(String nome, Date nascimento, Telefone telefone,
			EnderecoEspecifico enderecoEspecifico) {
		super(nome, nascimento, telefone, enderecoEspecifico);
	}

	public PessoaJuridica(String cnpj) {
		super();
		this.cnpj = cnpj;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
}
