package com.esi.processoJuridico;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import processoJuridico.UC_ProcessoManager;
import processo_juridico.ProcessoJuridico;
import processo_juridico.TipoProcesso;


@Stateless
@Remote(UC_ProcessoJuridicoSessionRemote.class)
public class UC_ProcessoJuridicoSession implements UC_ProcessoJuridicoSessionRemote {
	
    public UC_ProcessoJuridicoSession() {
    }

	@Override
	public ArrayList<ProcessoJuridico> recuperarTodosProcessos() throws Exception {
		UC_ProcessoManager processoManager = new UC_ProcessoManager();
		return processoManager.recuperarTodosProcessos();
	}

	@Override
	public ProcessoJuridico recuperarProcessoJuridicoWithNumero(int numero)	throws Exception {
		UC_ProcessoManager processoManager = new UC_ProcessoManager();
		return processoManager.recuperarProcessoJuridicoWithNumero(numero);
	}

	@Override
	public void removerProcessoJuridicoWithNumero(int numero) throws Exception {
		UC_ProcessoManager processoManager = new UC_ProcessoManager();
		processoManager.removerProcessoJuridicoWithNumero(numero);
	}

	@Override
	public ProcessoJuridico alterarProcessoJuridico(ProcessoJuridico processo) throws Exception {
		UC_ProcessoManager processoManager = new UC_ProcessoManager();
		return processoManager.alterarProcessoJuridico(processo);
	}

	@Override
	public ProcessoJuridico cadastrarProcessoJuridico(ProcessoJuridico processo) throws Exception {
		UC_ProcessoManager processoManager = new UC_ProcessoManager();
		return processoManager.cadastrarProcessoJuridico(processo);
	}

	@Override
	public void addTipoProcesso(ProcessoJuridico processo) throws Exception {
		UC_ProcessoManager processoManager = new UC_ProcessoManager();
		processoManager.addTipoProcesso(processo);		
	}

	@Override
	public ArrayList<TipoProcesso> recuperarTodosTipoProcesso()	throws Exception {
		UC_ProcessoManager processoManager = new UC_ProcessoManager();
		return processoManager.recuperarTodosTipoProcesso();
	}

}
