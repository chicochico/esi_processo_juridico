package col_tramite;

import infra.ConexaoBD;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import col_processoJuridico.Col_ProcessoJuridico;

import processo_juridico.ProcessoJuridico;
import processo_juridico.TipoTramite;
import processo_juridico.Tramite;

public class Col_Tramite {
	private ConexaoBD conexao;

	public Col_Tramite() {
		super();
		this.conexao = null;
	}

	public Col_Tramite(ConexaoBD conexao) {
		super();
		this.conexao = conexao;
	}
	
	public ArrayList<Tramite> recuperarTramitesWithNumeroProcesso(int numeroProcesso) throws Exception {
		String sql = String.format("SELECT * FROM Tramite WHERE numeroProcesso = %d", numeroProcesso);
		ResultSet rs = conexao.execSelect(sql);
		ArrayList<Tramite> tramites = new ArrayList<>();
		while (rs.next()) {
			Tramite tramite = new Tramite();
			tramite.setObs(rs.getString("obs"));
			tramite.setData(rs.getDate("data"));
			sql = String.format("SELECT * FROM TipoTramite " +
					"			 WHERE idTipoTramite = %i", rs.getInt("idTipoTramite"));
			ResultSet rs1 = conexao.execSelect(sql);
			if (rs1.next()) {
				TipoTramite tipo = new TipoTramite(rs1.getInt("idTipoTramite"),rs1.getString("nomeTipoTramite"));
				tramite.setTipo(tipo);
			}
			tramites.add(tramite);
		}
		return tramites;
	}
	
	public void addTramiteAoProcessoNumero(Tramite tramite, int numeroProcesso) throws Exception {
		this.validateTramite(tramite, numeroProcesso);
		
		String sql = String.format("SELECT * FROM TipoTramite WHERE nomeTipoTramite='%s'", tramite.getTipo().getNome());
		ResultSet rs = conexao.execSelect(sql);
		if (rs.next())
			tramite.getTipo().setId(rs.getInt("idTipoTramite"));
		else {
			sql = String.format("INSERT INTO TipoTramite(nomeTipoTramite) VALUES('%s')", tramite.getTipo().getNome());
			rs = conexao.execSelect("SELECT LAST_INSERT_ID()");
			rs.last();
			tramite.getTipo().setId(rs.getInt(1));
		}
		
		sql = String.format("SELECT * FROM Tramite WHERE idTipoTramite=%d AND numeroProcesso=%d", 
				tramite.getTipo().getId(), numeroProcesso);
		rs = conexao.execSelect(sql);
		while (rs.next()) {
			if (!(rs.getDate("data") == tramite.getData() && rs.getString("obs") == tramite.getObs())) {
				
				SimpleDateFormat date = new SimpleDateFormat("YYYY/MM/dd");
				sql = String.format("INSERT INTO Tramite(numeroProcesso, idTipoTramite, data, obs) " +
										   "VALUES (%d, %d, '%s', '%s')", 
						numeroProcesso, tramite.getTipo().getId(), date.format(tramite.getData()), tramite.getObs());
				conexao.execSQL(sql);
			}
		}
	}
	
	
	public void validateTramite(Tramite tramite, int numeroProcesso) throws Exception {
		if (tramite.getData() == null)
			throw new Exception("Data invalida");
		if (tramite.getTipo() == null)
			throw new Exception("Tipo do tramite invalido");
		
		Col_ProcessoJuridico colProcesso = new Col_ProcessoJuridico(conexao);
		ProcessoJuridico processo = colProcesso.recuperarProcessoJuridicoWithNumero(numeroProcesso);
		if (processo != null) {
			Date abertura = processo.getAbertura();
			
			if (abertura.after(tramite.getData()))
				throw new Exception("Data do tramite deve ser posterior a data de abertura do processo");
				
			String sql = String.format("SELECT data FROM Tramite WHERE numeroProcesso=%d ORDER BY 1 DESC", numeroProcesso); 
			ResultSet rs = conexao.execSelect(sql);
			if (rs.next()) {
				Date ultimoTramite = rs.getDate("data");
				if (ultimoTramite.after(tramite.getData()))
					throw new Exception("Data do tramite deve ser posterior a data do ultimo tramite do processo");
			}
		}
	}
}
