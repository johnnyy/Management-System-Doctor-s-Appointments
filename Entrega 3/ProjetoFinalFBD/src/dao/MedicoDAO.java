package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexao.Conexao_Postgres;
import entidade.Medico;

public class MedicoDAO {

	private Connection connection;
	private Conexao_Postgres connection_Postgres;
	
	public MedicoDAO(Conexao_Postgres connection_Postgres){
		this.setConnection_Postgres(connection_Postgres);
		connection = connection_Postgres.getConnection();
	}
	
	public Medico busca(String cpf) {
		
		Medico medico = null;
		String sql = "SELECT * FROM MEDICO WHERE CPF = ?";
		String sql1 = "SELECT * FROM TELEFONE_MED WHERE FK_CPF = ?";
		String sql2 = "SELECT * FROM ESPECIALIDADE WHERE FK_CPF = ?";
		
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
			String crm = rs.getString("crm");
			
			ps.close();
			
			ps = connection.prepareStatement(sql1);
			ps.setString(1, cpf);
			
			rs = ps.executeQuery();
			
			ArrayList<String> telefones = new ArrayList<String>();
			while(rs.next()){
				telefones.add(rs.getString("telefone"));
			}
						
			ps.close();
			
			ps = connection.prepareStatement(sql2);
			ps.setString(1, cpf);
			
			rs = ps.executeQuery();
			
			ArrayList<String> especialidades = new ArrayList<String>();
			while(rs.next()){
				especialidades.add(rs.getString("especialidade"));
			}
						
			ps.close();
			
			medico = new Medico(nome, salario, cpf, carga_horaria, logradouro, cep, cidade, crm, especialidades, telefones);
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		return medico;
	}
	
	public boolean insere(Medico medico) {
		
		boolean retorno = false;
		
		String sql = "INSERT INTO MEDICO (NOME, SALARIO, CARGA_HORARIA, CPF, CRM, LOGRADOURO, CEP, CIDADE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		String sql1 = "INSERT INTO TELEFONE_MED (FK_CPF, TELEFONE) VALUES (?, ?)";
		String sql2 = "INSERT INTO ESPECIALIDADE (FK_CPF, ESPECIALIDADE) VALUES (?, ?)";
		
		try{
		    
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, medico.getNome());
			ps.setDouble(2, medico.getSalario());
			ps.setInt(3, medico.getCarga_horaria());
			ps.setString(4, medico.getCpf());
			ps.setString(5, medico.getCrm());
			ps.setString(6, medico.getEndereco().getLogradouro());
			ps.setString(7, medico.getEndereco().getCep());
			ps.setString(8, medico.getEndereco().getCidade());
			int rowsAffected = ps.executeUpdate();
			ps.close();
			
			for (int i = 0; i < medico.getTelefones().size(); i++) {
				ps = connection.prepareStatement(sql1);
				ps.setString(1,medico.getCpf());
				ps.setString(2, medico.getTelefones().get(i));
				ps.executeUpdate();
				ps.close();
			}
			
			for (int i = 0; i < medico.getEspecialidades().size(); i++) {
				ps = connection.prepareStatement(sql2);
				ps.setString(1,medico.getCpf());
				ps.setString(2, medico.getEspecialidades().get(i));
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
		
		String sql = "DELETE FROM MEDICO WHERE CPF = ?";
		String sql1 = "DELETE FROM TELEFONE_MED WHERE FK_CPF = ?";
		String sql2 = "DELETE FROM ESPECIALIDADE WHERE FK_CPF = ?";
		String sql3 = "DELETE FROM MEDICAMENTO WHERE ID_PRESCRICAO = ANY(SELECT ID FROM PRESCRICAO_MEDICA WHERE CPF_MEDICO = ?)";
		String sql4 = "DELETE FROM PRESCRICAO_MEDICA WHERE CPF_MEDICO = ?";
		
		try{
			
			PreparedStatement ps = connection.prepareStatement(sql3);
			ps.setString(1, cpf);
			ps.executeUpdate();
			ps.close();
			
			ps = connection.prepareStatement(sql4);
			ps.setString(1, cpf);
			ps.executeUpdate();
			ps.close();
			
			ps = connection.prepareStatement(sql1);
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
	
	public boolean atualiza (String cpf, Medico medico) {
		boolean retorno = false;
		
		String sql = "UPDATE MEDICO SET NOME = ?, SALARIO = ?, CARGA_HORARIA = ?, CRM = ?, LOGRADOURO = ?, CEP = ?, CIDADE = ? WHERE CPF = ?";
		String sql1 = "DELETE FROM TELEFONE_MED WHERE FK_CPF = ?";
		String sql2 = "INSERT INTO TELEFONE_MED (FK_CPF, TELEFONE) VALUES (?, ?)";
		String sql3 = "DELETE FROM ESPECIALIDADE (FK_CPF, ESPECIALIDADE) VALUES (?, ?)";
		String sql4 = "INSERT INTO ESPECIALIDADE (FK_CPF, ESPECIALIDADE) VALUES (?, ?)";
		
		try{
		    
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, medico.getNome());
			ps.setDouble(2, medico.getSalario());
			ps.setInt(3, medico.getCarga_horaria());
			ps.setString(4, medico.getCrm());
			ps.setString(5, medico.getEndereco().getLogradouro());
			ps.setString(6, medico.getEndereco().getCep());
			ps.setString(7, medico.getEndereco().getCidade());
			ps.setString(8, medico.getCpf());
			
			int rowsAffected = ps.executeUpdate();
			ps.close();
			
			ps = connection.prepareStatement(sql1);
			ps.setString(1, medico.getCpf());
			ps.executeUpdate();
			ps.close();
			
			for (int i = 0; i < medico.getTelefones().size(); i++) {
				ps = connection.prepareStatement(sql2);
				ps.setString(1,medico.getCpf());
				ps.setString(2, medico.getTelefones().get(i));
				ps.executeUpdate();
				ps.close();
			}
			
			ps = connection.prepareStatement(sql3);
			ps.setString(1,medico.getCpf());
			ps.executeUpdate();
			ps.close();
			
			for (int i = 0; i < medico.getEspecialidades().size(); i++) {
				ps = connection.prepareStatement(sql4);
				ps.setString(1,medico.getCpf());
				ps.setString(2, medico.getEspecialidades().get(i));
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
	
	public ArrayList<Medico> lista() {
		
		ArrayList<Medico> medicos = new ArrayList<Medico>();
		Medico medico = null;
		String sql = "SELECT * FROM MEDICO ORDER BY NOME";
		String sql1 = "SELECT * FROM TELEFONE_MED WHERE FK_CPF = ?";
		String sql2 = "SELECT * FROM ESPECIALIDADE WHERE FK_CPF = ?";
		
		ResultSet rs = null;
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
			
				String nome = rs.getString("nome");			
				double salario = rs.getDouble("salario");
				int carga_horaria = rs.getInt("carga_horaria");
				String logradouro = rs.getString("logradouro");
				String cep = rs.getString("cep");
				String cidade = rs.getString("cidade");
				String crm = rs.getString("crm");
				String cpf = rs.getString("cpf");
				
				PreparedStatement ps1 = connection.prepareStatement(sql1);
				ps1.setString(1, cpf);
				
				ResultSet rs1 = ps1.executeQuery();
				
				ArrayList<String> telefones = new ArrayList<String>();
				while(rs1.next()){
					telefones.add(rs1.getString("telefone"));
				}
							
				ps1.close();
				
				PreparedStatement ps2 = connection.prepareStatement(sql2);
				ps2.setString(1, cpf);
				
				ResultSet rs2 = ps2.executeQuery();
				
				ArrayList<String> especialidades = new ArrayList<String>();
				while(rs2.next()){
					especialidades.add(rs2.getString("especialidade"));
				}
							
				ps2.close();
				
				medico = new Medico(nome, salario, cpf, carga_horaria, logradouro, cep, cidade, crm, especialidades, telefones);
				medicos.add(medico);
			}
			
			ps.close();
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		return medicos;
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
