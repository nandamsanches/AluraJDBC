package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.jdbc.modelo.Produto;
//classe/instancia trabalha com o banco de dados relativo a produtos]
//acessa o banco de dados de produtos
public class ProdutosDAO {
	
	private final Connection con;
	
	public ProdutosDAO(Connection con) {
		this.con = con;
	}
	

	public void salva(Produto produto) throws SQLException {
		String sql = "insert into Produto(nome, descricao) values (?, ?)";
		//pega o statement
		try(PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			//salva
			statement.setString(1,  produto.getNome());
			statement.setString(2, produto.getDescricao());
			
			statement.execute();
			//busca o id
			try(ResultSet rs = statement.getGeneratedKeys()){
				if(rs.next()) {
					int id = rs.getInt("id");
					produto.setId(id);						
				}
			}
			
		}
	}


	public List<Produto> lista() throws SQLException{
		List<Produto> produtos = new ArrayList<>();
		String sql = "select * from Produto";
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.execute();
			
			try(ResultSet rs = stmt.getResultSet()){
				while(rs.next()) {
					//le os produtos
					int id = rs.getInt("id");
					String nome = rs.getString("nome");
					String descricao = rs.getString("descricao");
					Produto produto = new Produto(nome, descricao);
					//cria o produto
					produto.setId(id);
					//adiciona na lista
					produtos.add(produto);
					
					
				}
			}
			
			
		}
		return produtos;
		
	}


}
