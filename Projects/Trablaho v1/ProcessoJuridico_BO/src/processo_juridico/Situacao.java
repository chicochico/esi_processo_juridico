package processo_juridico;

public class Situacao {

	private int id;
	private String nome;
	
	public Situacao() {
		super();
		this.id = -1;
		this.nome = "";
	}

	public Situacao(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Situacao(String nome) {
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
