package jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionJDBC {
	
	public Connection getConnection(){
		try {
			Context ic = new InitialContext();
			DataSource dataSource = (DataSource) ic.lookup("java:jboss/datasources/es2");
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}
}
