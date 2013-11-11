package geral;

import java.io.Serializable;
import java.util.Date;

public class PessoaFisica extends Pessoa  implements Serializable{
	public final static long serialVersionUID = 1;

	private String rg;
	private String cpf;
	private Sexo sexo;
	
	public PessoaFisica() {
		super();
		this.rg = "";
		this.cpf = "";
		this.sexo = new Sexo();
	}
	
	public PessoaFisica(String nome, Date nascimento, Telefone telefone,
			EnderecoEspecifico enderecoEspecifico) {
		super(nome, nascimento, telefone, enderecoEspecifico);
	}

	public PessoaFisica(String rg, String cpf, Sexo sexo) {
		super();
		this.rg = rg;
		this.cpf = cpf;
		this.sexo = sexo;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
}
