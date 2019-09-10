package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.hsqldb.jdbc.JDBCPool;

//A pr�tica do Pool de conex�es consiste em deixar um n�mero fixo ou dinamico de conexoes abertas e recicla-las utilizando novas requisi��es
public class ConnectionPool {
	
	private DataSource dataSource;
	
	ConnectionPool() throws SQLException{
				
		JDBCPool pool = new JDBCPool();
		pool.setUrl("jdbc:hsqldb:hsql://localhost/loja-virtual");
		pool.setUser("SA");
		pool.setPassword("");
		this.dataSource = pool;
	}
	
	Connection getConnection() throws SQLException {
		System.out.println("Abrindo conexão com o banco de dados");
		//Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/loja-virtual", "SA", "");
		//reutiliza as conex�es de diversos clientes, abre o banco uma s� vez e faz o que todos os clientes precisam, n�o fica abrindo e fechando v�rias 
		//vezes como na linha de cima
		Connection connection = dataSource.getConnection();
		return connection;
	}	

}
