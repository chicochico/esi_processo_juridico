package infra;

import java.io.Serializable;

public class UsuarioBD implements Serializable {

  // declarar opcionalmente. Motivo: objeto Serializable. Ver doc. java
  public final static long serialVersionUID = 1;
	
	
  private String senha;
  private String usuario;
  private String banco;
 
  public UsuarioBD(){  
  }
  
  public UsuarioBD(String usu, String psw, String banco) {
     usuario = usu;
     senha = psw;
     this.banco = banco;
  }
  

  public String getSenha() { 
	  return senha; 
  }
  
  public String getUsuario() { 
	  return usuario; 
  }
  
  public String getBanco() { 
	  return banco; 
  }
  
  public void setSenha(String senha) { 
	  this.senha = senha; 
  }
  
  public void setUsuario(String usr) { 
	  this.usuario = usr; 
  }
  public void setBanco(String bd) { 
	  this.banco = bd; 
  }

}
