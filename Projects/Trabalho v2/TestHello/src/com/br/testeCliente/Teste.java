package com.br.testeCliente;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import processo_juridico.Advogado;

import com.br.hello.HelloRemote;

public class Teste {

	private HelloRemote hello;
	
	public void runThis() throws Exception{
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
		props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");

		props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");

		InitialContext cont = new InitialContext(props);
		
		this.hello = (HelloRemote) cont.lookup("java:global/ProcessoJuridico_EJB_EAR/ProcessoJuridico_EJB/Hello");
		
		Advogado adv = (Advogado) this.hello.recuperarAdvogadoWithID(2);
		
		String usr = this.hello.sayHello(adv.getNome());
		System.out.println(usr);
	}
	
	public static void main(String[] args) {
		Teste teste = new Teste();
		try {
			teste.runThis();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
