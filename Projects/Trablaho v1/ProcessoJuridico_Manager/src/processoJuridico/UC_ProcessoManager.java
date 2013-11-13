package processoJuridico;

import forum.UC_ForumManager;
import infra.ConexaoBD;
import infra.GlobalApp;

import java.util.ArrayList;
import java.util.Date;

import advogado.UC_AdvogadoManager;
import cliente.UC_ClienteManager;
import col_processoJuridico.Col_ProcessoJuridico;
import processo_juridico.Advogado;
import processo_juridico.Cliente;
import processo_juridico.Forum;
import processo_juridico.ProcessoJuridico;
import processo_juridico.TipoProcesso;
import processo_juridico.TipoTramite;
import processo_juridico.Tramite;
import tramite.UC_TramiteManager;

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

		UC_TramiteManager tramiteManager = new UC_TramiteManager();
		Tramite tramite = new Tramite("Tramite de abertura", new Date(), new TipoTramite("Abertura"));
		processo.getTramites().add(0, tramite);
		for(int i=0; i < processo.getTramites().size(); i++) {
			tramiteManager. addTramiteAoProcessoNumero(processo.getTramites().get(i), processo.getNumero());
		}
		conexao.commit();
		conexao.close();
		return processo;
	}
	
	public ProcessoJuridico alterarProcessoJuridico(ProcessoJuridico processo) throws Exception {
		ConexaoBD conexao = GlobalApp.getConexaoBD();
		Col_ProcessoJuridico colProcesso = new Col_ProcessoJuridico(conexao);

		processo = colProcesso.alterarProcessoJuridico(processo);
		
		
		UC_TramiteManager tramiteManager = new UC_TramiteManager();
		Tramite tramite = new Tramite("Lancamento da Situacao", new Date(), new TipoTramite(processo.getSituacao().getNome()));
		processo.getTramites().add(tramite);
		for(int i=0; i < processo.getTramites().size(); i++) {
			tramiteManager.addTramiteAoProcessoNumero(processo.getTramites().get(i), processo.getNumero());
		}
		
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
		
		UC_ClienteManager ucm = new UC_ClienteManager();
		UC_ForumManager ufm = new UC_ForumManager();
		UC_AdvogadoManager uam = new UC_AdvogadoManager();
		
		Cliente cliente = ucm.recuperarClienteWithID(processo.getCliente().getId());
		Forum forum = ufm.recuperarForumWithID(processo.getForum().getId());
		Advogado responsavel = uam.recuperarAdvogadoWithID(processo.getResponsavel().getId());
		
		processo.setCliente(cliente);
		processo.setForum(forum);
		processo.setResponsavel(responsavel);
		
		return processo;
	}
	
	public ArrayList<ProcessoJuridico> recuperarTodosProcessos() throws Exception {
		ConexaoBD conexao = GlobalApp.getConexaoBD();
		Col_ProcessoJuridico colProcesso = new Col_ProcessoJuridico(conexao);
		ArrayList<ProcessoJuridico> processos = new ArrayList<>();
		processos = colProcesso.recuperarTodosProcessos();
		conexao.close();
		
		for (ProcessoJuridico processo : processos) {
			UC_ClienteManager ucm = new UC_ClienteManager();
			UC_ForumManager ufm = new UC_ForumManager();
			UC_AdvogadoManager uam = new UC_AdvogadoManager();
			
			Cliente cliente = ucm.recuperarClienteWithID(processo.getCliente().getId());
			Forum forum = ufm.recuperarForumWithID(processo.getForum().getId());
			Advogado responsavel = uam.recuperarAdvogadoWithID(processo.getResponsavel().getId());
			
			processo.setCliente(cliente);
			processo.setForum(forum);
			processo.setResponsavel(responsavel);
		}
		return processos;
	}
	
}
