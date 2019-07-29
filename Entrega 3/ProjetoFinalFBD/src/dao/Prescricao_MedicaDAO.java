package dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import conexao.Conexao_Postgres;
import entidade.Medicamento;
import entidade.Prescricao_Medica;
import dao.MedicamentoDAO;

public class Prescricao_MedicaDAO {

	private Connection connection;
	private Conexao_Postgres connection_Postgres;
	
	public Prescricao_MedicaDAO(Conexao_Postgres connection_Postgres){
		this.setConnection_Postgres(connection_Postgres);
		connection = connection_Postgres.getConnection();
	}
	
	public int getId(){
		
		String sql = "SELECT COALESCE(MAX(ID), 0) as ID FROM PRESCRICAO_MEDICA";
		
		int Id = -1;
		
		ResultSet rs = null;
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			rs.next();
			
			Id = rs.getInt("id");
			
			ps.close();
				
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return Id + 1;
	}
	
	public Prescricao_Medica busca(int id) {
	
		Prescricao_Medica prescricao = null;
		String sql = "SELECT * FROM PRESCRICAO_MEDICA WHERE ID = ?";
			
		ResultSet rs = null;
		
		MedicamentoDAO medicamentoDAO_aux = new MedicamentoDAO(connection_Postgres);
		
		try {
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if(!rs.next()) return null;
			
			Date date = rs.getDate("data_atendimento");
			Time horario = rs.getTime("horario");
			String descricao_diagnostico = rs.getString("descricao_diagnostico");
			String cpf_medico = rs.getString("cpf_medico");
			String cpf_paciente = rs.getString("cpf_paciente");
			
			ps.close();
			
			ArrayList<Medicamento> medicamentos = medicamentoDAO_aux.BuscaMedicamentoPresscricao(id);
			
			prescricao = new Prescricao_Medica(id, date, descricao_diagnostico, horario, cpf_paciente, cpf_medico, medicamentos);
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return prescricao;
	}
	
	public boolean insere(Prescricao_Medica prescricao_medica) {
		
		boolean retorno = false;
		
		String sql = "INSERT INTO PRESCRICAO_MEDICA (ID, DATA_ATENDIMENTO, HORARIO, DESCRICAO_DIAGNOSTICO, CPF_MEDICO, CPF_PACIENTE) VALUES (?, ?, ?, ?, ?, ?)";
		
		try{
		    
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, prescricao_medica.getId());
			ps.setDate(2, prescricao_medica.getDate());
			ps.setTime(3, prescricao_medica.getHorario());
			ps.setString(4, prescricao_medica.getDescricao_diagnostico());
			ps.setString(5, prescricao_medica.getCpf_medico());
			ps.setString(6, prescricao_medica.getCpf_paciente());
			
			int rowsAffected = ps.executeUpdate();
			ps.close();
			
			if(rowsAffected > 0){
				retorno = true;
			}
		}
		catch(SQLException e){
			System.err.println(e.getMessage());
		}	
		
		return retorno;
	}
	
	public boolean deleta (int id) {
		boolean retorno = false;
		String sql = "DELETE FROM PRESCRICAO_MEDICA WHERE ID = ?";
		String sql1 = "DELETE FROM MEDICAMENTO WHERE ID_PRESCRICAO = ?";
		
		try{
			
			PreparedStatement ps = connection.prepareStatement(sql1);
			ps.setInt(1, id);
			ps.close();
			
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
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
	
	public boolean atualiza (int id, Prescricao_Medica prescricao_medica) {
		
		boolean retorno = false;
		
		String sql = "UPDATE PRESCRICAO_MEDICA SET DATA_ATENDIMENTO = ?, HORARIO = ?, DESCRICAO_DIAGNOSTICO = ?, CPF_MEDICO = ?, CPF_PACIENTE = ? WHERE ID = ?";
		
		try{
		    
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setDate(1, prescricao_medica.getDate());
			ps.setTime(2, prescricao_medica.getHorario());
			ps.setString(3, prescricao_medica.getDescricao_diagnostico());
			ps.setString(4, prescricao_medica.getCpf_medico());
			ps.setString(5, prescricao_medica.getCpf_paciente());
			ps.setInt(6, prescricao_medica.getId());
			
			int rowsAffected = ps.executeUpdate();
			ps.close();
			
			if(rowsAffected > 0){
				retorno = true;
			}
		}
		catch(SQLException e){
			System.err.println(e.getMessage());
		}	
		
		return retorno;
	}
	
	public ArrayList<Prescricao_Medica> lista() {
		
		ArrayList<Prescricao_Medica> prescricoes = new ArrayList<Prescricao_Medica>();
		Prescricao_Medica prescricao = null;
		String sql = "SELECT * FROM PRESCRICAO_MEDICA ORDER BY DATA_ATENDIMENTO";
			
		MedicamentoDAO medicamentoDAO_aux = new MedicamentoDAO(connection_Postgres);
		
		ResultSet rs = null;
		
		try {
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
			
				Date date = rs.getDate("data_atendimento");
				Time horario = rs.getTime("horario");
				String descricao_diagnostico = rs.getString("descricao_diagnostico");
				String cpf_medico = rs.getString("cpf_medico");
				String cpf_paciente = rs.getString("cpf_paciente");
				int id = rs.getInt("id");
				
				ArrayList<Medicamento> medicamentos = medicamentoDAO_aux.BuscaMedicamentoPresscricao(id);
				 
				prescricao = new Prescricao_Medica(id, date, descricao_diagnostico, horario, cpf_paciente, cpf_medico, medicamentos);
				prescricoes.add(prescricao);
			}
			
			ps.close();
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return prescricoes;
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
