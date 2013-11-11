package com.br.hello;

import infra.ConexaoBD;
import infra.GlobalApp;

import javax.ejb.Stateless;

import col_advogado.Col_Advogado;
import processo_juridico.Advogado;



@Stateless
public class Hello implements HelloRemote {
	
    public Hello() {
    }

	@Override
	public String sayHello(String nome) {
		return "Hello " + nome + ", seja bem-vindo!";
	}
	
	@Override
	public Advogado recuperarAdvogadoWithID(int id) throws Exception {
		ConexaoBD conexao = GlobalApp.getConexaoBD();
		Col_Advogado colAdvogado = new Col_Advogado(conexao);
		Advogado advogado = colAdvogado.recuperarAdvogadoWithID(id);
		conexao.close();
		return advogado;
	}
	
	
}
