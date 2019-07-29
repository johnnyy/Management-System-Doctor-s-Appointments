package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexao.Conexao_Postgres;
import entidade.Medicamento;

public class MedicamentoDAO {

	private Connection connection;
	private Conexao_Postgres connection_Postgres;
	
	public MedicamentoDAO(Conexao_Postgres connection_Postgres){
		this.setConnection_Postgres(connection_Postgres);
		connection = connection_Postgres.getConnection();
	}
	
	public int getId(){
		String sql = "SELECT COALESCE(MAX(ID_REMEDIO), 0) as ID_REMEDIO FROM MEDICAMENTO";
		
		ResultSet rs = null;
		int Id = -1;
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			rs.next();
			
			Id = rs.getInt("id_remedio");
			
			ps.close();
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		return Id + 1;
	}
	
	public ArrayList<Medicamento> BuscaMedicamentoPresscricao(int id_prescricao) {
		
		ArrayList<Medicamento> medicamentos = new ArrayList<Medicamento>();
		Medicamento medicamento = null;
		String sql = "SELECT * FROM MEDICAMENTO WHERE ID_PRESCRICAO = ?";
			
		ResultSet rs = null;
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id_prescricao);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
			
				int id_remedio = rs.getInt("id_remedio");
				String nome = rs.getString("nome");			
				boolean tarja_preta = rs.getBoolean("tarja_preta");
				String tipo_remedio = rs.getString("tipo_remedio");
				int periodicidade = rs.getInt("periodicidade");
				int qtd_dias = rs.getInt("quant_dias");
				int dosagem = rs.getInt("dosagem");
				String cpf_enfermeiro = rs.getString("cpf_enfermeiro");
				
				medicamento = new Medicamento(id_remedio, nome, tarja_preta, tipo_remedio, periodicidade, qtd_dias, dosagem, id_prescricao);
				medicamento.setCpf_enfermeiro(cpf_enfermeiro);
				medicamentos.add(medicamento);
			}
			
			ps.close();
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return medicamentos;
	}
	
	public Medicamento busca(int id) {
		
		Medicamento medicamento = null;
		String sql = "SELECT * FROM MEDICAMENTO WHERE ID_REMEDIO = ?";
			
		ResultSet rs = null;
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if(!rs.next()) return null;
			
			String nome = rs.getString("nome");			
			boolean tarja_preta = rs.getBoolean("tarja_preta");
			String tipo_remedio = rs.getString("tipo_remedio");
			int periodicidade = rs.getInt("periodicidade");
			int qtd_dias = rs.getInt("quant_dias");
			int dosagem = rs.getInt("dosagem");
			String cpf_enfermeiro = rs.getString("cpf_enfermeiro");
			int id_prescricao = rs.getInt("id_prescricao");
			ps.close();
			
			medicamento = new Medicamento(id, nome, tarja_preta, tipo_remedio, periodicidade, qtd_dias, dosagem, id_prescricao);
			medicamento.setCpf_enfermeiro(cpf_enfermeiro);
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		return medicamento;
	}
	
	public boolean insere(Medicamento medicamento) {
		boolean retorno = false;

		String sql = "INSERT INTO MEDICAMENTO (ID_REMEDIO, NOME, TARJA_PRETA, TIPO_REMEDIO, PERIODICIDADE, QUANT_DIAS, DOSAGEM, ID_PRESCRICAO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try{
		    
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, medicamento.getId_remedio());
			ps.setString(2, medicamento.getNome());
			ps.setBoolean(3, medicamento.isTarja_preta());
			ps.setString(4, medicamento.getTipo_remedio());
			ps.setInt(5, medicamento.getPeriodicidade());
			ps.setInt(6, medicamento.getQtd_dias());
			ps.setInt(7, medicamento.getDosagem());
			ps.setInt(8, medicamento.getId_prescricao());
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
		String sql = "DELETE FROM MEDICAMENTO WHERE ID_REMEDIO = ?";
		
		try{
			PreparedStatement ps = connection.prepareStatement(sql);
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
	
	public boolean atualiza (int id, Medicamento medicamento) {
		boolean retorno = false;

		String sql = "UPDATE MEDICAMENTO SET NOME = ?, TARJA_PRETA = ?, TIPO_REMEDIO = ?, PERIODICIDADE = ?, QUANT_DIAS = ?, DOSAGEM = ?, ID_PRESCRICAO = ?, CPF_ENFERMEIRO = ? WHERE ID_REMEDIO = ?";
		
		try{
		    
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, medicamento.getNome());
			ps.setBoolean(2, medicamento.isTarja_preta());
			ps.setString(3, medicamento.getTipo_remedio());
			ps.setInt(4, medicamento.getPeriodicidade());
			ps.setInt(5, medicamento.getQtd_dias());
			ps.setInt(6, medicamento.getDosagem());
			ps.setInt(7, medicamento.getId_prescricao());
			ps.setString(8, medicamento.getCpf_enfermeiro());
			ps.setInt(9, medicamento.getId_remedio());
			
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
	
	public ArrayList<Medicamento> lista() {
		
		ArrayList<Medicamento> medicamentos = new ArrayList<Medicamento>();
		Medicamento medicamento = null;
		String sql = "SELECT * FROM MEDICAMENTO ORDER BY NOME";
			
		ResultSet rs = null;
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				int id_remedio = rs.getInt("id_remedio");
				String nome = rs.getString("nome");			
				boolean tarja_preta = rs.getBoolean("tarja_preta");
				String tipo_remedio = rs.getString("tipo_remedio");
				int periodicidade = rs.getInt("periodicidade");
				int qtd_dias = rs.getInt("quant_dias");
				int dosagem = rs.getInt("dosagem");
				String cpf_enfermeiro = rs.getString("cpf_enfermeiro");
				int id_prescricao = rs.getInt("id_prescricao");
				
				medicamento = new Medicamento(id_remedio, nome, tarja_preta, tipo_remedio, periodicidade, qtd_dias, dosagem, id_prescricao);
				medicamento.setCpf_enfermeiro(cpf_enfermeiro);
				medicamentos.add(medicamento);
			}
			
			ps.close();
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return medicamentos;
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
