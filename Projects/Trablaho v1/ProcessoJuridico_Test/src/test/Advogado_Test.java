package test;

import java.util.ArrayList;

import processo_juridico.Advogado;
import advogado.UC_AdvogadoManager;

public class Advogado_Test {


	public static void main(String[] args) throws Exception {
		UC_AdvogadoManager adm = new UC_AdvogadoManager();
		Advogado adv = adm.recuperarAdvogadoWithID(4);	
		
		System.out.println(adv.getNome());
		
		ArrayList<Advogado> array = adm.recuperarTodosAdvogados();
		System.out.println(array.size());
	}

}
