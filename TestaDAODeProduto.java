package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.caelum.jdbc.dao.ProdutosDAO;
import br.com.caelum.jdbc.modelo.Produto;

public class TestaDAODeProduto {

	public static void main(String[] args) throws SQLException {
		//cria produto
		Produto mesa = new Produto("Mesa Vermelha", "Mesa com 4 pés");
		//abre conexao
		try(Connection connection = new ConnectionPool().getConnection()){
			//cria o objeto que acessa os produtos, usa o DAO para se comunicar com o banco
			ProdutosDAO dao = new ProdutosDAO(connection);
			dao.salva(mesa);
			
			List<Produto> produtos = dao.lista();
			for (Produto produto : produtos) {
				System.out.println("Existe o produto: " +produto);
			}
		}
		
		System.out.println("A mesa foi inserida com sucesso " + mesa);
		
	}
}
