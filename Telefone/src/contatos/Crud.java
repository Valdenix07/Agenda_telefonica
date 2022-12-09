package contatos;

import java.sql.*;
import java.util.Scanner;

public class Crud {
	
	Connection con = null;
	Scanner scan = new Scanner(System.in);
	int id = 0;
	String nome;
	String numero;
	
	
	public void create(Contato contato) {
		
		try {
		
		con = DriverManager.getConnection(
				"jdbc:mysql://localhost/agenda", "root", "");
		
		
		String sql = "INSERT INTO contatos (nome,numero) VALUES(?,?)";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		
		System.out.println("Digite o nome do contato:");
		nome = scan.nextLine();
		contato.setNome(nome);
		
		System.out.println("Digite o número do contato:");
		numero = scan.nextLine();
		contato.setNumero(numero);
		
		stmt.setNString(1, contato.getNome());
		stmt.setNString(2, contato.getNumero());
		
		stmt.execute();
		stmt.close();
		
		System.out.println("Contato Salvo");
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void select(Contato contato) {
		
		String sql = "SELECT * FROM contatos";
		
		try {
			
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/agenda", "root", "");
			
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
				while(rs.next()){
					
					System.out.println("Id:" + rs.getInt("id") + "\nNome:" + rs.getNString("nome") + "\nNúmero:" + rs.getNString("numero") + "\n");
				}
				
				rs.close();
				stmt.close();
				
		}catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
	
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void update(Contato contato) {
		
		String sql = "UPDATE contatos SET nome = ?, numero = ? " + 
		"WHERE id = ?";
		
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/agenda", "root", "");
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			Crud crud = new Crud();
			crud.select(contato);
			
			System.out.println("Digite o id do contato que deseja editar:");
			id = scan.nextInt();
			contato.setId(id);
			
			System.out.println("Digite o novo nome do contato:");
			nome = scan.nextLine(); //precisou de dois por causa do "NextInt"
			nome = scan.nextLine();
			contato.setNome(nome);
			
			System.out.println("Digite o novo número do contato:");
			numero = scan.nextLine();
			contato.setNumero(numero);
			
			stmt.setNString(1, contato.getNome());
			stmt.setNString(2, contato.getNumero());
			
			stmt.setInt(3, contato.getId());
			
			stmt.execute();
		
			
			System.out.println("Contato Atualizado");
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	public void delete(int id) {
		
		String sql = "DELETE FROM contatos WHERE id = ?";
		
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/agenda", "root", "");
			
			PreparedStatement stmt = con.prepareStatement(sql);
			
			Crud crud = new Crud();
			Contato contato = new Contato();
			crud.select(contato);
			
			System.out.println("Digite o id do contato que deseja deletar:");
			id = scan.nextInt();
			
			stmt.setInt(1, id);
			
			stmt.execute();
			
			System.out.println("Contato Deletado");	
		}catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if(con != null) {
					con.close();
				}
				
				}catch (SQLException e) {
					throw new RuntimeException(e);
				}
		
			}
		
	}
		
			
	}
