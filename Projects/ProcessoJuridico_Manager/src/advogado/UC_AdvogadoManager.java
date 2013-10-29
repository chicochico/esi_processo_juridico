package advogado;

import infra.ConexaoBD;
import infra.GlobalApp;

import java.util.ArrayList;

import col_advogado.Col_Advogado;

import processo_juridico.Advogado;

public class UC_AdvogadoManager {

	public Advogado recuperarAdvogadoWithID(int id) throws Exception {
		ConexaoBD conexao = GlobalApp.getConexaoBD();
		Col_Advogado colAdvogado = new Col_Advogado(conexao);
		Advogado advogado = colAdvogado.recuperarAdvogadoWithID(id);
		conexao.close();
		return advogado;
	}
	
	public ArrayList<Advogado> recuperarTodosAdvogados() throws Exception {
		ConexaoBD conexao = GlobalApp.getConexaoBD();
		Col_Advogado colAdvogado = new Col_Advogado(conexao);
		ArrayList<Advogado> advogado = colAdvogado.recuperarTodosAdvogados();
		conexao.close();
		return advogado;
	}
	
}
