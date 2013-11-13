package com.esi.processoJuridico;

import java.util.ArrayList;

import javax.ejb.Remote;

import processo_juridico.ProcessoJuridico;
import processo_juridico.TipoProcesso;

@Remote
public interface UC_ProcessoJuridicoSessionRemote {
	public ArrayList<ProcessoJuridico> recuperarTodosProcessos() throws Exception;
	public ProcessoJuridico recuperarProcessoJuridicoWithNumero(int numero) throws Exception;
	public void removerProcessoJuridicoWithNumero(int numero) throws Exception;
	public ProcessoJuridico alterarProcessoJuridico(ProcessoJuridico processo) throws Exception;
	public ProcessoJuridico cadastrarProcessoJuridico(ProcessoJuridico processo) throws Exception;
	public void addTipoProcesso(ProcessoJuridico processo) throws Exception;
	public ArrayList<TipoProcesso> recuperarTodosTipoProcesso() throws Exception;
}
