package tramite;

import infra.ConexaoBD;
import infra.GlobalApp;

import java.util.ArrayList;

import processo_juridico.Tramite;
import col_tramite.Col_Tramite;

public class UC_TramiteManager {
	public ArrayList<Tramite> recuperarTodosTramites(int numeroProcesso) throws Exception {
		ConexaoBD conexao = GlobalApp.getConexaoBD();
		Col_Tramite colTramite = new Col_Tramite(conexao);
		ArrayList<Tramite> tramites = colTramite.recuperarTramitesWithNumeroProcesso(numeroProcesso);
		conexao.close();
		return tramites;
	}
}
