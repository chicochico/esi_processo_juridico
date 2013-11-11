package processo_juridico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class ProcessoJuridico implements Serializable{
	public final static long serialVersionUID = 1;

	private int numero;
	private Date abertura;
	private String descricao;
	private Advogado responsavel;
	private Cliente cliente;
	private Forum forum;
	private Situacao situacao;
	private TipoProcesso tipoProcesso;
	private ArrayList<Tramite>tramites;
	
	public ProcessoJuridico() {
		super();
		this.numero = -1;
		this.abertura = new Date();
		this.descricao = "";
		this.responsavel = new Advogado();
		this.cliente = new Cliente();
		this.forum = new Forum();
		this.situacao = new Situacao();
		this.tipoProcesso = new TipoProcesso();
		this.tramites = new ArrayList<>();
	}

	public ProcessoJuridico(int numero, Date abertura, String descricao,
			Advogado responsavel, Cliente cliente, Forum forum,
			Situacao situacao, TipoProcesso tipoProcesso,
			ArrayList<Tramite> tramites) {
		super();
		this.numero = numero;
		this.abertura = abertura;
		this.descricao = descricao;
		this.responsavel = responsavel;
		this.cliente = cliente;
		this.forum = forum;
		this.situacao = situacao;
		this.tipoProcesso = tipoProcesso;
		this.tramites = tramites;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Date getAbertura() {
		return abertura;
	}

	public void setAbertura(Date abertura) {
		this.abertura = abertura;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Advogado getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Advogado responsavel) {
		this.responsavel = responsavel;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public TipoProcesso getTipoProcesso() {
		return tipoProcesso;
	}

	public void setTipoProcesso(TipoProcesso tipoProcesso) {
		this.tipoProcesso = tipoProcesso;
	}

	public ArrayList<Tramite> getTramites() {
		return tramites;
	}

	public void setTramites(ArrayList<Tramite> tramites) {
		this.tramites = tramites;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
