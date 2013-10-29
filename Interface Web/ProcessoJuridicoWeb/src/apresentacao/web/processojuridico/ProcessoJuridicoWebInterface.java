package apresentacao.web.processojuridico;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cliente.UC_ClienteManager;
import forum.UC_ForumManager;
import advogado.UC_AdvogadoManager;
import processoJuridico.UC_ProcessoManager;
import processo_juridico.Advogado;
import processo_juridico.Cliente;
import processo_juridico.Forum;
import processo_juridico.ProcessoJuridico;
import processo_juridico.Situacao;
import processo_juridico.Tramite;


@WebServlet("/ProcessoJuridicoWebInterface")
public class ProcessoJuridicoWebInterface extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    public ProcessoJuridicoWebInterface() {    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		UC_AdvogadoManager uc_advogado = new UC_AdvogadoManager();
		UC_ClienteManager uc_cliente = new UC_ClienteManager();
		UC_ForumManager uc_forum = new UC_ForumManager();
		
		try {
			ArrayList<Advogado> advogados = uc_advogado.recuperarTodosAdvogados();
			ArrayList<Cliente> clientes = uc_cliente.recuperarTodosClientes();
			ArrayList<Forum> forums = uc_forum.recuperarTodosForuns();
			request.setAttribute("lista_advogados", advogados);
			request.setAttribute("lista_cliente", clientes);
			request.setAttribute("lista_forum", forums);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UC_AdvogadoManager uc_advogado = new UC_AdvogadoManager();
		UC_ClienteManager uc_cliente = new UC_ClienteManager();
		UC_ForumManager uc_forum = new UC_ForumManager();
		
		try {
			ArrayList<Advogado> advogados = uc_advogado.recuperarTodosAdvogados();
			ArrayList<Cliente> clientes = uc_cliente.recuperarTodosClientes();
			ArrayList<Forum> forums = uc_forum.recuperarTodosForuns();
			request.setAttribute("lista_advogados", advogados);
			request.setAttribute("lista_cliente", clientes);
			request.setAttribute("lista_forum", forums);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SimpleDateFormat date = new SimpleDateFormat("YYYY-MM-dd");	
		
		int numero_processo = Integer.valueOf(request.getParameter("numero_processo"));
		try {
			Date data = date.parse(request.getParameter("data_abertura"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String descricao = request.getParameter("descricao_processo");
		String tipo = request.getParameter("tipo_processo");
		
		
		String advogado = request.getParameter("name_advogado");
		String cliente = request.getParameter("name_cliente");
		String forum = request.getParameter("name_forum");
		Situacao situacao = new Situacao();
		situacao.setId(0);
		ArrayList<Tramite> tramites = new ArrayList(); 
		
		UC_ProcessoManager uc_processo = new UC_ProcessoManager();
		//ProcessoJuridico processo = new ProcessoJuridico(numero_processo, data, descricao, advogado, cliente, forum, situacao, tipo, tramites);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	

}
