package dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexao.Conexao_Postgres;
import entidade.Paciente;

public class PacienteDAO {

	private Connection connection;
	private Conexao_Postgres connection_Postgres;
	
	public PacienteDAO (Conexao_Postgres connection_Postgres){
		this.setConnection_Postgres(connection_Postgres);
		connection = connection_Postgres.getConnection();
	}
	
	public Paciente busca(String cpf) {
		
		Paciente paciente = null;
		String sql = "SELECT * FROM PACIENTE WHERE CPF = ?";
		String sql1 = "SELECT * FROM TELEFONE_PAC WHERE FK_CPF = ?";
			
		ResultSet rs = null;
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, cpf);
			
			rs = ps.executeQuery();
			
			if(!rs.next()) return null;
			
			String nome = rs.getString("nome");			
			int idade = rs.getInt("idade");
			String logradouro = rs.getString("logradouro");
			String cep = rs.getString("cep");
			String cidade = rs.getString("cidade");
			Date data_nasc = rs.getDate("data_nasc");
			
			ps.close();
			
			ps = connection.prepareStatement(sql1);
			ps.setString(1, cpf);
			
			rs = ps.executeQuery();
			
			ArrayList<String> telefones = new ArrayList<String>();
			while(rs.next()){
				telefones.add(rs.getString("telefone"));
			}
						
			ps.close();
			
			paciente = new Paciente(nome, cpf, data_nasc, idade, telefones, logradouro, cep, cidade);
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return paciente;
	}
	
	public boolean insere(Paciente paciente) {
		boolean retorno = false;
		
		String sql = "INSERT INTO PACIENTE (NOME, IDADE, CPF, LOGRADOURO, CEP, CIDADE, DATA_NASC) VALUES (?, ?, ?, ?, ?, ?, ?)";
		String sql1 = "INSERT INTO TELEFONE_PAC (FK_CPF, TELEFONE) VALUES (?, ?)";
		
		try{
		    
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, paciente.getNome());
			ps.setInt(2, paciente.getIdade());
			ps.setString(3, paciente.getCpf());
			ps.setString(4, paciente.getEndereco().getLogradouro());
			ps.setString(5, paciente.getEndereco().getCep());
			ps.setString(6, paciente.getEndereco().getCidade());
			ps.setDate(7, paciente.getData_nasc());
			int rowsAffected = ps.executeUpdate();
			ps.close();
			
			for (int i = 0; i < paciente.getTelefones().size(); i++) {
				ps = connection.prepareStatement(sql1);
				ps.setString(1,paciente.getCpf());
				ps.setString(2, paciente.getTelefones().get(i));
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
		String sql = "DELETE FROM PACIENTE WHERE CPF = ?";
		String sql1 = "DELETE FROM TELEFONE_PAC WHERE FK_CPF = ?";
		String sql2 = "DELETE FROM MEDICAMENTO WHERE ID_PRESCRICAO = ANY(SELECT ID FROM PRESCRICAO_MEDICA WHERE CPF_PACIENTE = ?)";
		String sql3 = "DELETE FROM PRESCRICAO_MEDICA WHERE CPF_PACIENTE = ?";
		
		try{
			PreparedStatement ps = connection.prepareStatement(sql2);
			ps.setString(1, cpf);
			ps.executeUpdate();
			ps.close();
			
			ps = connection.prepareStatement(sql3);
			ps.setString(1, cpf);
			ps.executeUpdate();
			ps.close();
			
			ps = connection.prepareStatement(sql1);
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
	
	public boolean atualiza (String cpf, Paciente paciente) {
		boolean retorno = false;
		
		String sql = "UPDATE PACIENTE SET NOME = ?, IDADE = ?, LOGRADOURO = ?, CEP = ?, CIDADE = ?, DATA_NASC = ? WHERE CPF = ?";
		String sql1 = "DELETE FROM TELEFONE_PAC WHERE FK_CPF = ?";
		String sql2 = "INSERT INTO TELEFONE_PAC (FK_CPF, TELEFONE) VALUES (?, ?)";
		
		try{
		    
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, paciente.getNome());
			ps.setInt(2, paciente.getIdade());
			ps.setString(3, paciente.getEndereco().getLogradouro());
			ps.setString(4, paciente.getEndereco().getCep());
			ps.setString(5, paciente.getEndereco().getCidade());
			ps.setDate(6, paciente.getData_nasc());
			ps.setString(7, paciente.getCpf());
			
			int rowsAffected = ps.executeUpdate();
			ps.close();
			
			ps = connection.prepareStatement(sql1);
			ps.setString(1,paciente.getCpf());
			ps.executeUpdate();
			ps.close();
			
			for (int i = 0; i < paciente.getTelefones().size(); i++) {
				ps = connection.prepareStatement(sql2);
				ps.setString(1,paciente.getCpf());
				ps.setString(2, paciente.getTelefones().get(i));
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
	
	public ArrayList<Paciente> lista() {
		
		ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
		Paciente paciente = null;
		String sql = "SELECT * FROM PACIENTE ORDER BY NOME";
		String sql1 = "SELECT * FROM TELEFONE_PAC WHERE FK_CPF = ?";
			
		ResultSet rs = null;
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				String nome = rs.getString("nome");			
				int idade = rs.getInt("idade");
				String logradouro = rs.getString("logradouro");
				String cep = rs.getString("cep");
				String cidade = rs.getString("cidade");
				Date data_nasc = rs.getDate("data_nasc");
				String cpf = rs.getString("cpf");
				
				
				PreparedStatement ps1 = connection.prepareStatement(sql1);
				ps1.setString(1, cpf);
				
				ResultSet rs1 = ps1.executeQuery();
				
				ArrayList<String> telefones = new ArrayList<String>();
				while(rs1.next()){
					telefones.add(rs1.getString("telefone"));
				}
							
				ps1.close();
				
				paciente = new Paciente(nome, cpf, data_nasc, idade, telefones, logradouro, cep, cidade);
				pacientes.add(paciente);
			}
			
			ps.close();
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return pacientes;
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
