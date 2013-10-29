package geral;

import java.io.Serializable;

public class Telefone implements Serializable{
	public final static long serialVersionUID = 1;

	private int numero;

	public Telefone() {
		super();
		this.numero = -1;
	}

	public Telefone(int numero) {
		super();
		this.numero = numero;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
}
