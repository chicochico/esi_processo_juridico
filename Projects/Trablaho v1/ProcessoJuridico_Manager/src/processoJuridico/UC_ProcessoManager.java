package processoJuridico;

import infra.ConexaoBD;
import infra.GlobalApp;

import java.util.ArrayList;

import col_processoJuridico.Col_ProcessoJuridico;

import processo_juridico.ProcessoJuridico;
import processo_juridico.TipoProcesso;

public class UC_ProcessoManager {
	
	public ArrayList<TipoProcesso> recuperarTodosTipoProcesso() throws Exception {
		ConexaoBD conexao = GlobalApp.getConexaoBD();
		Col_ProcessoJuridico colProcesso = new Col_ProcessoJuridico(conexao);
		
		ArrayList<TipoProcesso> tipos = colProcesso.recuperarTodosTipoProcesso();
		
		conexao.close();
		return tipos;
	}
	
	
	public void addTipoProcesso(ProcessoJuridico processo) throws Exception {
		ConexaoBD conexao = GlobalApp.getConexaoBD();
		Col_ProcessoJuridico colProcesso = new Col_ProcessoJuridico(conexao);
		
		colProcesso.addTipoProcesso(processo);

		conexao.commit();
		conexao.close();
	}
	
	
	public ProcessoJuridico cadastrarProcessoJuridico(ProcessoJuridico processo) throws Exception {
		ConexaoBD conexao = GlobalApp.getConexaoBD();
		Col_ProcessoJuridico colProcesso = new Col_ProcessoJuridico(conexao);
		
		processo = colProcesso.cadastrarProcessoJuridico(processo);

		conexao.commit();
		conexao.close();
		return processo;
	}
	
	
	public ProcessoJuridico alterarProcessoJuridico(ProcessoJuridico processo) throws Exception {
		ConexaoBD conexao = GlobalApp.getConexaoBD();
		Col_ProcessoJuridico colProcesso = new Col_ProcessoJuridico(conexao);

		processo = colProcesso.alterarProcessoJuridico(processo);
		
		conexao.commit();
		conexao.close();
		return processo;
	}
	
	public void removerProcessoJuridicoWithNumero(int numero) throws Exception {
		ConexaoBD conexao = GlobalApp.getConexaoBD();
		Col_ProcessoJuridico colProcesso = new Col_ProcessoJuridico(conexao);
		
		colProcesso.removerProcessoJuridicoWithNumero(numero);
		
		conexao.commit();
		conexao.close();
	}
	
	public ProcessoJuridico recuperarProcessoJuridicoWithNumero(int numero) throws Exception {
		ConexaoBD conexao = GlobalApp.getConexaoBD();
		Col_ProcessoJuridico colProcesso = new Col_ProcessoJuridico(conexao);
		
		ProcessoJuridico processo = null;
		
		processo = colProcesso.recuperarProcessoJuridicoWithNumero(numero);

		conexao.close();
		return processo;
	}
	
	public ArrayList<ProcessoJuridico> recuperarTodosProcessos() throws Exception {
		ConexaoBD conexao = GlobalApp.getConexaoBD();
		Col_ProcessoJuridico colProcesso = new Col_ProcessoJuridico(conexao);
		ArrayList<ProcessoJuridico> processos = new ArrayList<>();
		
		processos = colProcesso.recuperarTodosProcessos();
		
		conexao.close();
		return processos;
	}
	
}
