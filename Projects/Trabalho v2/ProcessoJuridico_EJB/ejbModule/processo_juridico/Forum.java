package processo_juridico;

import java.io.Serializable;
import java.util.Date;

import geral.EnderecoEspecifico;
import geral.PessoaJuridica;
import geral.Telefone;

public class Forum extends PessoaJuridica implements Serializable {
	public final static long serialVersionUID = 1;

	private int id;

	public Forum() {
		super();
		this.id = -1;
	}

	public Forum(String nome, Date nascimento, Telefone telefone,
			EnderecoEspecifico enderecoEspecifico) {
		super(nome, nascimento, telefone, enderecoEspecifico);
	}

	public Forum(String cnpj) {
		super(cnpj);
	}

	public Forum(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
