
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="advogado.UC_AdvogadoManager" %>
<%@ page import="processo_juridico.Advogado" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="processo_juridico.Cliente" %>
<%@ page import="processo_juridico.Forum" %>
<%@ page import="processo_juridico.ProcessoJuridico" %>
  
 <%!
    void getDate()
    {
        System.out.println( "In getDate() method" );
    }
%>

<%
	/* UC_AdvogadoManager test = new UC_AdvogadoManager();
	Advogado a = test.recuperarAdvogadoWithID(1);
	System.out.println(a.getNome());
	System.out.println("hello");*/
%>
  
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Processo Juridico</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
    
        <!-- NavBar -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Processo Juridico</a>
            </div> 

            <div class="collapse navbar-collapse navbar-ex1-collapse">
                
                <form method="get" action="ProcessoJuridicoWebInterface" class="navbar-form navbar-left" role="search">
                    <div class="form-group">
                    <input name="numero_processo" type="text" class="form-control" placeholder="Numero do Processo">
                    </div>
                    <button type="submit" class="btn btn-default">Atualizar</button>
                </form>
                
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Cadastro<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a data-toggle="modal" href="#modal-novo-processo">Processo Juridico</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
        <!-- end of NavBar -->
        
        <!-- Main content -->
        <div class="container">
            <form role="form">
                <div class="form-group">
                    <label>Número do Processo</label>
                    <p class="form-control-static"> <% out.println(request.getAttribute("numeroProcesso")); %> </p>
                </div>
                <div class="form-group">
                    <label>Data de Abertura</label>
                    <input type="date" class="form-control" >
                </div>
                <div class="form-group">
                    <label>Tipo do Processo</label>
                    <input type="text" class="form-control" name="tipo_processo" placeholder="Tipo do Processo">
                </div>
                <div class="form-group">
                    <label>Descrição</label>
                    <textarea class="form-control" name="descricao_processo" placeholder="Descrição"></textarea>
                </div>
                <div class="form-group">
                    <label>Nome do Advogado</label>
                    <input type="text" class="form-control" placeholder="Advogado">
                </div>
                <div class="form-group">
                    <label>Nome do Cliente</label>
                    <input type="text" class="form-control" placeholder="Cliente">
                </div>
                <div class="form-group">
                    <label>Situação</label>
                    <select class="form-control">
                        <option>Aberto</option>
                        <option>Em Julgamento</option>
                        <option>Julgado</option>
                        <option>Cancelado</option>
                    </select>
                </div>
    <!--                        <button type="submit" class="btn btn-default">Submit</button>-->
            </form>
            
            <label>Trâmites do Processo</label>
            
            <table class="table table-hover">
                <tr>
                    <th>Tipo de Trâmite</th>
                    <th>Observações</th>
                    <th>Data do Trâmite</th>
                </tr>
                
                <tr>
                    <td>row 1, cell 1</td>
                    <td>row 1, cell 2</td>
                    <td>ex</td>
                </tr>
                <tr>
                    <td>row 2, cell 1</td>
                    <td>row 2, cell 2</td>
                    <td>adfasdfas</td>
                </tr>
            </table>
            
            <a data-toggle="modal" href="#modal-novo-tramite" class="btn btn-default">Adicionar Tramite</a>
            
            <!-- modal tramite -->
            <div class="modal fade" id="modal-novo-tramite" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            
            <div class="modal-dialog">
                
                <div class="modal-content">
                    
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Novo Trâmite de Processo</h4>
                    </div>
                    
                    <div class="modal-body">
                        <form role="form">
                            
                            <div class="form-group">
                                <label>Tipo do Trâmite</label>
                                <input type="text" class="form-control" name="tipo_tramite" placeholder="Tipo do Trâmite">
                            </div>
                            
                            <div class="form-group">
                                <label>Observação</label>
                                <input type="text" class="form-control" name="obs_tramite" placeholder="Observações">
                            </div>
                            
                            <div class="form-group">
                                <label>Data do Trâmite</label>
                                <input type="date" class="form-control" name="data_tramite">
                            </div>
                            

                            <button type="submit" class="btn btn-success">Adicionar</button>
                        </form>
                    </div>

                </div>
            </div>
        </div>
        <!-- modal tramite -->
            
            
        </div>
        <!-- end of main content -->
        
        <!-- Modal para cadastro de novo processo -->
        <div class="modal fade" id="modal-novo-processo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            
            <div class="modal-dialog">
                
                <div class="modal-content">
                    
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Novo Processo Jurídico</h4>
                    </div>
                    
                    <div class="modal-body">
                        <form method="post" action="ProcessoJuridicoWebInterface" role="form">
                            
                            <div class="form-group">
                                <label>Número Processo</label>
                                <input type="text" class="form-control" name="numero_processo">
                            </div>
                            
                            <div class="form-group">
                                <label>Data de Abertura</label>
                                <input type="date" class="form-control" name="data_abertura">
                            </div>
                            
                            <div class="form-group">
                                <label>Tipo do Processo</label>
                                <input type="text" class="form-control" name="tipo_processo" placeholder="Tipo do Processo">
                            </div>
                            
                            <div class="form-group">
                                <label>Descrição</label>
                                <textarea class="form-control" name="descricao_processo" placeholder="Descrição"></textarea>
                            </div>
                            
                            <div class="form-group">
                                <label>Nome do Advogado</label>
                                <div class="form-group">
				                    <select name="name_advogado" class="form-control">			                
<%
	ArrayList<Advogado> advogados = (ArrayList<Advogado>)request.getAttribute("lista_advogados");
    for ( int i = 0; i < advogados.size(); i++ ) {
        %>
        <option value=<%= advogados.get(i).getNome() %>>
        	<%= advogados.get(i).getNome() %>
        </option>
        <%
    }
%>
				                    </select>
				                </div>
                            </div>
                            
                            <div class="form-group">
                                <label>Nome do Cliente</label>
                                <div class="form-group">
				                    <select name="name_cliente" class="form-control">			                
<%
	ArrayList<Cliente> clientes = (ArrayList<Cliente>)request.getAttribute("lista_cliente");
    for ( int i = 0; i < clientes.size(); i++ ) {
        %>
        <option value=<%= clientes.get(i).getPessoa().getNome() %>>
        	<%= clientes.get(i).getPessoa().getNome() %>
        </option>
        <%
    }
%>
				                    </select>
				                </div>
                            </div>
                            
                            <div class="form-group">
                                <label>Nome do Forum</label>
                                <div class="form-group">
				                    <select name="name_forum" class="form-control">			                
<%
	ArrayList<Forum> forums = (ArrayList<Forum>)request.getAttribute("lista_forum");
    for ( int i = 0; i < forums.size(); i++ ) {
        %>
        <option value=<%= forums.get(i).getNome() %>>
        	<%= forums.get(i).getNome() %>
        </option>
        <%
    }
%>
				                    </select>
				                </div>
                            </div>
                            
                            <button type="submit" class="btn btn-success">Cadastrar</button>
                        </form>
                    </div>

                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div>
        <!-- /.modal -->
                
        <script src="//code.jquery.com/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
<!--        <script src="js/main.js"></script>-->
        
    </body>
</html>