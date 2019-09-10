package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.hsqldb.jdbc.JDBCPool;

//A prática do Pool de conexões consiste em deixar um número fixo ou dinamico de conexoes abertas e recicla-las utilizando novas requisições
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
		System.out.println("Abrindo conexÃ£o com o banco de dados");
		//Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/loja-virtual", "SA", "");
		//reutiliza as conexões de diversos clientes, abre o banco uma só vez e faz o que todos os clientes precisam, não fica abrindo e fechando várias 
		//vezes como na linha de cima
		Connection connection = dataSource.getConnection();
		return connection;
	}	

}
