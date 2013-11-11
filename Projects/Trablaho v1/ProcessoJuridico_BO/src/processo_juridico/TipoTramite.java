package processo_juridico;

import java.io.Serializable;

public class TipoTramite implements Serializable{
	public final static long serialVersionUID = 1;

	private int id;
	private String nome;
	
	public TipoTramite() {
		super();
		this.id = -1;
		this.nome = "";
	}

	public TipoTramite(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public TipoTramite(String nome) {
		super();
		this.nome = nome;
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
}
