package processo_juridico;

import java.io.Serializable;
import java.util.Date;

import geral.EnderecoEspecifico;
import geral.PessoaFisica;
import geral.Telefone;

public class Advogado extends PessoaFisica implements Serializable{
	public final static long serialVersionUID = 1;

	private int id;
	private String oab;
	
	public Advogado() {
		super();
		this.id = -1;
		this.oab = "";
	}
	
	public Advogado(String nome, Date nascimento, Telefone telefone,
			EnderecoEspecifico enderecoEspecifico) {
		super(nome, nascimento, telefone, enderecoEspecifico);
	}

	public Advogado(int id, String oab) {
		super();
		this.id = id;
		this.oab = oab;
	}

	public Advogado(String oab) {
		super();
		this.oab = oab;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOab() {
		return oab;
	}

	public void setOab(String oab) {
		this.oab = oab;
	}
}
