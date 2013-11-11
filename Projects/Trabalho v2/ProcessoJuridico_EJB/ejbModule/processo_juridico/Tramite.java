package processo_juridico;

import java.io.Serializable;
import java.util.Date;

public class Tramite implements Serializable{
	public final static long serialVersionUID = 1;

	private String obs;
	private Date data;
	private TipoTramite tipo;
	
	public Tramite() {
		super();
		this.obs = "";
		this.data = null;
		this.tipo = null;
	}

	public Tramite(int id, String obs, Date data, TipoTramite tipo) {
		super();
		this.obs = obs;
		this.data = data;
		this.setTipo(tipo);
	}

	public Tramite(String obs, Date data, TipoTramite tipo) {
		super();
		this.obs = obs;
		this.data = data;
		this.setTipo(tipo);
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public TipoTramite getTipo() {
		return tipo;
	}

	public void setTipo(TipoTramite tipo) {
		this.tipo = tipo;
	}
}
