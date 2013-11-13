package infra;

import java.sql.*;

public class ConexaoBD {
    protected Connection connection;
    protected ResultSet tabela;
    
	// *************************************************
    public ConexaoBD(){
    }
        
    // *************************************************    
    public void conectar() throws Exception {
    	ConnectionJDBC conJDBC = new ConnectionJDBC();
    	connection = conJDBC.getConnection();
    	connection.setAutoCommit(false);
    }
    
    // *************************************************
    public void close()throws Exception{
    	connection.close();
    }
    
    // *************************************************
    public boolean isClosed() throws SQLException{
        return connection.isClosed();
    }
    
    // *************************************************
    public ResultSet execSelect(String sql) throws Exception{
        Statement s = connection.createStatement();
    	ResultSet result = s.executeQuery(sql);
    	return result;
    }
    
    // *************************************************
    public void execSQL(String sql) throws Exception{
    	Statement s = connection.createStatement();
        s.execute(sql);
    }
   
    // *************************************************
    public Connection getConnection() {
            return connection;
    }

    // *************************************************
    public void setConnection(Connection connection) {
            this.connection = connection;
    }

    // *************************************************
    public void commit() throws SQLException{
        connection.commit();
    }
    
    // *************************************************
    public void rollback() throws SQLException{
        connection.rollback();
    }
    
    // *************************************************
    public PreparedStatement prepareStatement(String sql) throws SQLException{  
        return connection.prepareStatement(sql);
    } 	
}