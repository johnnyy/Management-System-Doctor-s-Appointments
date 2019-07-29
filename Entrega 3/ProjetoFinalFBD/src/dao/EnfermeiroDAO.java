package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexao.Conexao_Postgres;
import entidade.Enfermeiro;


public class EnfermeiroDAO {
	
	private Connection connection;
	private Conexao_Postgres connection_Postgres;
	
	public EnfermeiroDAO(Conexao_Postgres connection_Postgres){
		this.setConnection_Postgres(connection_Postgres);
		connection = connection_Postgres.getConnection();
	}
	
	public Enfermeiro busca(String cpf) {
		
		Enfermeiro enfermeiro = null;
		String sql = "SELECT * FROM ENFERMEIRO WHERE CPF = ?";
		String sql1 = "SELECT * FROM TELEFONE_ENF WHERE FK_CPF = ?";
		
		ResultSet rs = null;
		
		try {
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, cpf);
			
			rs = ps.executeQuery();
			
			if(!rs.next()) return null;
			
			String nome = rs.getString("nome");			
			double salario = rs.getDouble("salario");
			int carga_horaria = rs.getInt("carga_horaria");
			String logradouro = rs.getString("logradouro");
			String cep = rs.getString("cep");
			String cidade = rs.getString("cidade");
			String coren = rs.getString("coren");
			String formacao = rs.getString("formacao");
			
			ps.close();
			
			ps = connection.prepareStatement(sql1);
			ps.setString(1, cpf);
			
			rs = ps.executeQuery();
			
			ArrayList<String> telefones = new ArrayList<String>();
			while(rs.next()){
				telefones.add(rs.getString("telefone"));
			}
						
			ps.close();
			
			enfermeiro = new Enfermeiro(nome, salario, cpf, carga_horaria, logradouro, cep, cidade, coren, formacao, telefones);
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		return enfermeiro;
	}
	
	public boolean insere(Enfermeiro enfermeiro) {
		boolean retorno = false;
		
		String sql = "INSERT INTO ENFERMEIRO (NOME, SALARIO, CARGA_HORARIA, CPF, COREN, FORMACAO, LOGRADOURO, CEP, CIDADE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String sql1 = "INSERT INTO TELEFONE_ENF (FK_CPF, TELEFONE) VALUES (?, ?)";
		
		try{
		    
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, enfermeiro.getNome());
			ps.setDouble(2, enfermeiro.getSalario());
			ps.setInt(3, enfermeiro.getCarga_horaria());
			ps.setString(4, enfermeiro.getCpf());
			ps.setString(5, enfermeiro.getCoren());
			ps.setString(6, enfermeiro.getFormacao());
			ps.setString(7, enfermeiro.getEndereco().getLogradouro());
			ps.setString(8, enfermeiro.getEndereco().getCep());
			ps.setString(9, enfermeiro.getEndereco().getCidade());
			int rowsAffected = ps.executeUpdate();
			ps.close();
			
			for (int i = 0; i < enfermeiro.getTelefones().size(); i++) {
				ps = connection.prepareStatement(sql1);
				ps.setString(1,enfermeiro.getCpf());
				ps.setString(2, enfermeiro.getTelefones().get(i));
				ps.executeUpdate();
				ps.close();
			}
			
			if(rowsAffected > 0){
				retorno = true;
			}
		}
		catch(SQLException e){
			System.err.println(e.getMessage());
		}	
		
		return retorno;
	}
	
	public boolean deleta (String cpf) {
		
		boolean retorno = false;
		String sql = "DELETE FROM ENFERMEIRO WHERE CPF = ?";
		String sql1 = "DELETE FROM TELEFONE_ENF WHERE FK_CPF = ?";
		String sql2 = "UPDATE MEDICAMENTO SET CPF_ENFERMEIRO = NULL WHERE CPF_ENFERMEIRO = ?";
		
		try{
			
			PreparedStatement ps = connection.prepareStatement(sql1);
			ps.setString(1, cpf);
			ps.executeUpdate();
			ps.close();
			
			ps = connection.prepareStatement(sql2);
			ps.setString(1, cpf);
			ps.executeUpdate();
			ps.close();
			
			ps = connection.prepareStatement(sql);
			ps.setString(1, cpf);
			int rowsAffected = ps.executeUpdate();
			ps.close();
			
			if(rowsAffected > 0){
				retorno = true;
			}
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return retorno;
	}
	
	public boolean atualiza (String cpf, Enfermeiro enfermeiro) {
		boolean retorno = false;
		
		String sql = "UPDATE ENFERMEIRO SET NOME = ?, SALARIO = ?, CARGA_HORARIA = ?, COREN = ?, FORMACAO = ?, LOGRADOURO = ?, CEP = ?, CIDADE = ? WHERE CPF = ?";
		String sql1 = "DELETE FROM TELEFONE_ENF WHERE FK_CPF = ?";
		String sql2 = "INSERT INTO TELEFONE_ENF (FK_CPF, TELEFONE) VALUES (?, ?)";
		
		try{
		    
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, enfermeiro.getNome());
			ps.setDouble(2, enfermeiro.getSalario());
			ps.setInt(3, enfermeiro.getCarga_horaria());
			ps.setString(4, enfermeiro.getCoren());
			ps.setString(5, enfermeiro.getFormacao());
			ps.setString(6, enfermeiro.getEndereco().getLogradouro());
			ps.setString(7, enfermeiro.getEndereco().getCep());
			ps.setString(8, enfermeiro.getEndereco().getCidade());
			ps.setString(9, enfermeiro.getCpf());

			int rowsAffected = ps.executeUpdate();
			ps.close();
			
			ps = connection.prepareStatement(sql1);
			ps.setString(1,enfermeiro.getCpf());
			ps.executeUpdate();
			ps.close();
			
			for (int i = 0; i < enfermeiro.getTelefones().size(); i++) {
				ps = connection.prepareStatement(sql2);
				ps.setString(1,enfermeiro.getCpf());
				ps.setString(2, enfermeiro.getTelefones().get(i));
				ps.executeUpdate();
				ps.close();
			}
			
			if(rowsAffected > 0){
				retorno = true;
			}
		}
		catch(SQLException e){
			System.err.println(e.getMessage());
		}	
		
		return retorno;
	}
	
	public ArrayList<Enfermeiro> lista() {
		
		ArrayList<Enfermeiro> enfermeiros = new ArrayList<Enfermeiro>();
		Enfermeiro enfermeiro = null;
		String sql = "SELECT * FROM ENFERMEIRO ORDER BY NOME";
		String sql1 = "SELECT * FROM TELEFONE_ENF WHERE FK_CPF = ?";
		
		ResultSet rs = null;
		
		try {
			
			PreparedStatement ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				String cpf = rs.getString("cpf");
				String nome = rs.getString("nome");			
				double salario = rs.getDouble("salario");
				int carga_horaria = rs.getInt("carga_horaria");
				String logradouro = rs.getString("logradouro");
				String cep = rs.getString("cep");
				String cidade = rs.getString("cidade");
				String coren = rs.getString("coren");
				String formacao = rs.getString("formacao");
				
				PreparedStatement ps1 = connection.prepareStatement(sql1);
				ps1.setString(1, cpf);
				
				ResultSet rs1 = ps1.executeQuery();
				
				ArrayList<String> telefones = new ArrayList<String>();
				while(rs1.next()){
					telefones.add(rs1.getString("telefone"));
				}
							
				ps1.close();
				
				enfermeiro = new Enfermeiro(nome, salario, cpf, carga_horaria, logradouro, cep, cidade, coren, formacao, telefones);
				enfermeiros.add(enfermeiro);
			}
			
			ps.close();
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		return enfermeiros;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Conexao_Postgres getConnection_Postgres() {
		return connection_Postgres;
	}

	public void setConnection_Postgres(Conexao_Postgres connection_Postgres) {
		this.connection_Postgres = connection_Postgres;
	}
	
}
