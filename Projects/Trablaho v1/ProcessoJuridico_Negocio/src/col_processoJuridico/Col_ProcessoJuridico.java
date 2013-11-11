package col_processoJuridico;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import processo_juridico.Advogado;
import processo_juridico.ProcessoJuridico;
import processo_juridico.Situacao;
import processo_juridico.TipoProcesso;
import processo_juridico.TipoTramite;
import processo_juridico.Tramite;

import col_advogado.Col_Advogado;
import col_cliente.Col_Cliente;
import col_forum.Col_Forum;
import col_tramite.Col_Tramite;

import com.mysql.jdbc.PreparedStatement;

import infra.ConexaoBD;

public class Col_ProcessoJuridico {
	private ConexaoBD conexao;

	public Col_ProcessoJuridico() {
		super();
		this.conexao = null;
	}

	public Col_ProcessoJuridico(ConexaoBD conexao) {
		super();
		this.conexao = conexao;
	}
	
	
	public ArrayList<TipoProcesso> recuperarTodosTipoProcesso() throws Exception {
		String sql = "SELECT * FROM TipoProcesso";
		ResultSet rs = conexao.execSelect(sql);
		ArrayList<TipoProcesso> tipos = new ArrayList<>();
		while(rs.next()) {
			tipos.add(new TipoProcesso(rs.getInt("idTipoProcesso"), rs.getString("nomeTipoProcesso")));
		}
		return tipos;
	}
	
	public void addTipoProcesso(ProcessoJuridico processo) throws Exception {
		String sql = "SELECT * FROM TipoProcesso WHERE nomeTipoProcesso = '" + processo.getTipoProcesso().getNome() + "'";
		ResultSet rs = conexao.execSelect(sql);
		if (rs.next()) {
			processo.getTipoProcesso().setId(rs.getInt("idTipoProcesso"));
		} else {
			sql = "INSERT INTO TipoProcesso(nomeTipoProcesso) VALUES ('" + processo.getTipoProcesso().getNome() + "')";
			conexao.execSQL(sql);
			rs = conexao.execSelect("SELECT LAST_INSERT_ID()");
			rs.last();
			processo.getTipoProcesso().setId(rs.getInt(1));
		}
	}
	
	
	public ProcessoJuridico cadastrarProcessoJuridico(ProcessoJuridico processo) throws Exception {
		this.validateProcessoJuridico(processo);
		this.validateAdvogado(processo.getResponsavel());
		this.validateCliente(processo);
		this.addTipoProcesso(processo);
		SimpleDateFormat date = new SimpleDateFormat("YYYY/MM/dd");
		String sql = String.format("INSERT INTO Processo(abertura, descricao, situacao," +
				"idForum, idCliente, idAdvogado, idTipoProcesso) VALUES ('%s', '%s', 0, %d, %d, %d, %d)",
				date.format(processo.getAbertura()), processo.getDescricao(), 
				processo.getForum().getId(), processo.getCliente().getId(),
				processo.getResponsavel().getId(), processo.getTipoProcesso().getId());
		conexao.execSQL(sql);
		ResultSet rs = conexao.execSelect("SELECT LAST_INSERT_ID()");
		rs.last();
		processo.setNumero(rs.getInt(1));
		
		Col_Tramite colTramite = new Col_Tramite(conexao);
		Tramite tramite = new Tramite("Tramite de abertura", new Date(), new TipoTramite("Abertura"));
		processo.getTramites().add(0, tramite);
		for(int i=0; i < processo.getTramites().size(); i++) {
			colTramite.addTramiteAoProcessoNumero(processo.getTramites().get(i), processo.getNumero());
		}
		
		return processo;
	}
	
	
	
	public ProcessoJuridico alterarProcessoJuridico(ProcessoJuridico processo) throws Exception {
		this.validateProcessoJuridico(processo);
		this.validateTramite(processo);
		
		SimpleDateFormat date = new SimpleDateFormat("YYYY/MM/dd");
		String sql = String.format("UPDATE Processo SET abertura=%s, descricao='%s', situacao=%d," +
				"idForum=%d, idCliente=%d, idAdvogado=%d, idTipoProcesso=%d WHERE numeroProcesso=%d",
				date.format(processo.getAbertura()), processo.getDescricao(), 
				processo.getSituacao().getId(), processo.getForum().getId(), processo.getCliente().getId(),
				processo.getResponsavel().getId(), processo.getTipoProcesso().getId(), processo.getNumero());
		conexao.execSQL(sql);
		ResultSet rs = conexao.execSelect("SELECT LAST_INSERT_ID()");
		rs.last();
		processo.setNumero(rs.getInt(1));
		
		Col_Tramite colTramite = new Col_Tramite(conexao);
		Tramite tramite = new Tramite("Lancamento da Situacao", new Date(), new TipoTramite(processo.getSituacao().getNome()));
		processo.getTramites().add(tramite);
		for(int i=0; i < processo.getTramites().size(); i++) {
			colTramite.addTramiteAoProcessoNumero(processo.getTramites().get(i), processo.getNumero());
		}
		
		return processo;
	}
	
	
	
	
	public void removerProcessoJuridicoWithNumero(int numero) throws Exception {
		this.validateID(numero);
		String sql = String.format("DELETE FROM Processo WHERE numeroProcesso = %d", numero);
		PreparedStatement query = (PreparedStatement) conexao.prepareStatement(sql);
		query.executeUpdate();
	}
	
	
	
	
	public ProcessoJuridico recuperarProcessoJuridicoWithNumero(int numero) throws Exception {
		this.validateID(numero);
		String sql = String.format("SELECT * FROM Processo WHERE numeroProcesso = %d", numero);
		ResultSet rs = conexao.execSelect(sql);
		ProcessoJuridico processo = null;
		if (rs.next()) {
			processo = new ProcessoJuridico();
			
			Col_Cliente colCliente = new Col_Cliente(conexao);
			Col_Advogado colAdvogado = new Col_Advogado(conexao);
			Col_Forum colForum = new Col_Forum(conexao);
			Col_Tramite colTramite = new Col_Tramite(conexao);
			
			processo.setAbertura(rs.getDate("abertura"));
			processo.setCliente(colCliente.recuperarClienteWithID(rs.getInt("idCliente")));
			processo.setDescricao(rs.getString("descricao"));
			processo.setForum(colForum.recuperarForumWithID(rs.getInt("idForum")));
			processo.setResponsavel(colAdvogado.recuperarAdvogadoWithID(rs.getInt("idAdvogado")));
			processo.setTramites(colTramite.recuperarTramitesWithNumeroProcesso(processo.getNumero()));
			
			Situacao situacao = new Situacao();
			situacao.setId(rs.getInt("situacao"));
			processo.setSituacao(situacao);
			
			sql = String.format("SELECT * FROM TipoProcesso WHERE idTipoProcesso = %d", rs.getInt("idTipoProcesso"));
			ResultSet rs1 = conexao.execSelect(sql);
			if (rs1.next()) {
				TipoProcesso tipoProcesso = new TipoProcesso(rs1.getInt("idTipoProcesso"), rs1.getString("nomeTipoProcesso"));
				processo.setTipoProcesso(tipoProcesso);
			}
		}
		
		return processo;
	}
	
	
	
	
	public ArrayList<ProcessoJuridico> recuperarTodosProcessos() throws Exception {
		ArrayList<ProcessoJuridico> processos = new ArrayList<>();
		String sql = "SELECT * FROM Processo";
		ResultSet rs = conexao.execSelect(sql);
		
		while (rs.next()) {
			Col_Cliente colCliente = new Col_Cliente(conexao);
			Col_Advogado colAdvogado = new Col_Advogado(conexao);
			Col_Forum colForum = new Col_Forum(conexao);
			Col_Tramite colTramite = new Col_Tramite(conexao);
			
			ProcessoJuridico processo = new ProcessoJuridico();
			processo.setAbertura(rs.getDate("abertura"));
			processo.setCliente(colCliente.recuperarClienteWithID(rs.getInt("idCliente")));
			processo.setDescricao(rs.getString("descricao"));
			processo.setForum(colForum.recuperarForumWithID(rs.getInt("idForum")));
			processo.setResponsavel(colAdvogado.recuperarAdvogadoWithID(rs.getInt("idAdvogado")));
			processo.setTramites(colTramite.recuperarTramitesWithNumeroProcesso(processo.getNumero()));
			
			Situacao situacao = new Situacao();
			situacao.setId(rs.getInt("situacao"));
			processo.setSituacao(situacao);
			
			sql = String.format("SELECT * FROM TipoProcesso WHERE idTipoProcesso = %d", rs.getInt("idTipoProcesso"));
			ResultSet rs1 = conexao.execSelect(sql);
			if (rs1.next()) {
				TipoProcesso tipoProcesso = new TipoProcesso(rs.getInt("idTipoProceso"), rs.getString("nomeTipoProcesso"));
				processo.setTipoProcesso(tipoProcesso);
			}
			processos.add(processo);
		}
		return processos;
	}
	
	
	
	public void validateID(int numero) throws Exception {
		if (numero <= 0)
			throw new Exception("Numero de processo invalido");
	}
	
	
	
	public void validateProcessoJuridico(ProcessoJuridico processo) throws Exception {
		if (processo.getAbertura() == null)
			throw new Exception("Data de abertura invalida");
	}
	
	
	
	public void validateAdvogado(Advogado advogado) throws Exception {
		if (advogado.getNome() == null || advogado.getNome() == "" || advogado.getNome() == " ")
			throw new Exception("Nome de advogado invalido");
		
		String sql = String.format("SELECT * FROM Processo WHERE idAdvogado = %d AND situacao = 0", advogado.getId());
		ResultSet rs = conexao.execSelect(sql);
		int cont = 0;
		while(rs.next()) {
			cont++;
		}
		if (cont > 3)
			throw new Exception("Esse advogado nao pode abrir mais processos, pois ja possui 3 processos em aberto.");
		if (advogado.getOab() == null || advogado.getOab() == "")
			throw new Exception(String.format("%s nao possui o numero na OAB registrado.", advogado.getNome()));
	}
	
	
	
	public void validateCliente(ProcessoJuridico processo) throws Exception {
		String sql = String.format("SELECT idCliente, idTipoProcesso, abertura FROM Processo WHERE idCliente=%d" +
				" AND IdTipoProcesso=%d ORDER BY 2 DESC", processo.getCliente().getId(), processo.getTipoProcesso().getId());
		
		ResultSet rs = conexao.execSelect(sql);
		while (rs.next()) {
			long diff = Math.abs(processo.getAbertura().getTime() - rs.getDate("abertura").getTime());
			long diffDays = diff / (24 * 60 * 60 * 1000);
			if (diffDays < 30)
				throw new Exception("Ja existe um processo desse tipo, cadastrado em menos de 30 dias.");
		}
	}
	
	
	
	public void validateTramite(ProcessoJuridico processo) throws SQLException, Exception {
		String sql = String.format("SELECT situacao FROM Processo WHERE numeroProcesso=%d", processo.getNumero());
		ResultSet rs = conexao.execSelect(sql);
		
		if (rs.next()) {
			if (rs.getInt("situacao") != 0)
				throw new Exception("Tramite nao esta em aberto");
		}
		sql = String.format("SELECT * FROM Tramite WHERE numeroProcesso=%d", processo.getNumero());
		rs = conexao.execSelect(sql);
		while (rs.next()) {
			if (rs.getInt("idTipoTramite") != 0)
				throw new Exception("Esse processo nao pode ser alterado, pois ja possui tramites.");
		}
	}
	
	
	public void validateInsertTramite(ProcessoJuridico processo) throws Exception {
		String sql = String.format("SELECT * FROM Tramite WHERE numeroProcesso=%d", processo.getNumero());
		ResultSet rs = conexao.execSelect(sql);
		if (rs.next())
			rs.last();
			if ((rs.getInt("idTipoTramite") != 0) || (rs.getInt("idTipoTramite") != 1)) 
				throw new Exception("Tramite nao pode ser incluido. O processo deve estar em Aberto ou em Julgamento para realizar essa operacao.");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}