package col_advogado;

import java.sql.ResultSet;
import java.util.ArrayList;

import processo_juridico.Advogado;

import geral.Sexo;
import infra.ConexaoBD;

public class Col_Advogado {

	private ConexaoBD conexao;

	public Col_Advogado() {
		super();
		this.conexao = null;
	}

	public Col_Advogado(ConexaoBD conexao) {
		super();
		this.conexao = conexao;
	}
	
	public Advogado recuperarAdvogadoWithID(int id) throws Exception {
		String sql = String.format("SELECT * FROM Advogado WHERE idAdvogado = %d", id);
		ResultSet rs = conexao.execSelect(sql);
		
		Advogado advogado = null;
		if (rs.next()) {
			advogado = new Advogado();
			advogado.setId(id);
			advogado.setNome(rs.getString("nomeAdvogado"));
			advogado.setCpf(rs.getString("cpf"));
			advogado.setRg(rs.getString("rg"));
			advogado.setOab(rs.getString("oab"));
			advogado.setSexo(new Sexo(rs.getString("sexo")));
			advogado.setNascimento(rs.getDate("nascimento"));
			advogado.getEnderecoEspecifico().setId(rs.getInt("idEndereco"));
		}
		return advogado;
	}
	
	public ArrayList<Advogado> recuperarTodosAdvogados() throws Exception {
		String sql = "SELECT * FROM Advogado";
		ResultSet rs = conexao.execSelect(sql);
		ArrayList<Advogado> advogados = new ArrayList<Advogado>();
		
		while (rs.next()) {
			Advogado advogado = new Advogado();
			advogado.setId(rs.getInt("idAdvogado"));
			advogado.setNome(rs.getString("nomeAdvogado"));
			advogado.setCpf(rs.getString("cpf"));
			advogado.setRg(rs.getString("rg"));
			advogado.setOab(rs.getString("oab"));
			advogado.setSexo(new Sexo(rs.getString("sexo")));
			advogado.setNascimento(rs.getDate("nascimento"));
			advogado.getEnderecoEspecifico().setId(rs.getInt("idEndereco"));
			advogados.add(advogado);
		}
		return advogados;
	}
}
