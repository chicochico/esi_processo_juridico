package infra;

public class GlobalApp {
    private static UsuarioBD usuarioBanco;
    private static ConexaoBD conexaoBD = null;

    public GlobalApp() {
    }
    
    public static void setUsuarioBanco(UsuarioBD ub) {
        usuarioBanco = ub;
    }
    
    public static UsuarioBD getUsuarioBanco() {
        return usuarioBanco;
    }

    public static void setConexaoBD(ConexaoBD conexao) {
        conexaoBD = conexao;
    }

    public static ConexaoBD getConexaoBD() throws Exception  {
    	if (conexaoBD == null || conexaoBD.isClosed()) {
            conexaoBD = new ConexaoBD();
            conexaoBD.conectar();
    	}
    	return conexaoBD;
    }
}