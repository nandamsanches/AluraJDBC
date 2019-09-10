package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {

	public static void main(String[] args) throws SQLException {
		//com esse try, precisa fechar o statement
		try(Connection connection = new ConnectionPool().getConnection()){
			//para nao adicionar um por um
			connection.setAutoCommit(false);
			try {
				String sql = "insert into produto (nome, descricao) values (?, ?)";
				PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
				adiciona("TV LCD", "32 polegadas", statement);
				adiciona("Blueray", "Full HDMI", statement);
				
				//se tiver erro, comita um por um 
				connection.commit();
				connection.close();
			}catch(Exception ex) {
				ex.printStackTrace();
				//se der erro, voltar atras
				connection.rollback();
				System.out.println("Rollback efetuado!");
			}
				
		}
		
	}

	private static void adiciona(String nome, String descricao, PreparedStatement statement) throws SQLException {
		
		if(nome.equals("Blueray")) {
			throw new IllegalArgumentException("Problema ocorrido");
		}
		statement.setString(1, nome);
		statement.setString(2, descricao);
		boolean resultado = statement.execute();
		System.out.println(resultado);
		ResultSet generatedKeys = statement.getGeneratedKeys();
		
		while(generatedKeys.next()) {
			long id = generatedKeys.getLong("id");
			System.out.println("id gerado: " + id);
		}

		generatedKeys.close();
	}
}
