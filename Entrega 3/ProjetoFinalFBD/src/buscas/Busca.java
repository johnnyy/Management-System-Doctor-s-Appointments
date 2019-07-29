package buscas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import conexao.Conexao_Postgres;

public class Busca {
	
	private Connection connection;
	private Conexao_Postgres connection_Postgres;
	
	public Busca (Conexao_Postgres connection_Postgres) {
		this.connection_Postgres = connection_Postgres;
	}

	public ArrayList<String> PacientesAtendidosMaisDeUmaVezView() {
		
		ArrayList<String> retorno = new ArrayList<String>();
		String sql = "SELECT nome_paciente as NOME_PACIENTE FROM QTD_ATENDIMENTO_PACIENTE WHERE qtd_atendimentos > 1";
			
		ResultSet rs = null;
		
		try {
			
			this.connection = connection_Postgres.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				retorno.add(rs.getString("nome_paciente"));
			}
			
			ps.close();
			
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return retorno;
	}
	
	public ArrayList<String> PacientesAtendidosMaisDeUmaVezTable() {
		
		ArrayList<String> retorno = new ArrayList<String>();
		String sql = "SELECT nome_paciente as NOME_PACIENTE FROM QTD_ATENDIMENTO_PACIENTE_TABLE WHERE qtd_atendimentos > 1";
			
		ResultSet rs = null;
		
		try {
			
			this.connection = connection_Postgres.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				retorno.add(rs.getString("nome_paciente"));
			}
			
			ps.close();
			
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return retorno;
	}

	public ArrayList<ArrayList<String>> PacientesAtendidosMaisDeUmaVezPorMes() {
		
		ArrayList<ArrayList<String>> retorno = new ArrayList< ArrayList<String> >();
		ArrayList<String> aux;
		String sql = "SELECT EXTRACT(MONTH FROM data_atendimento) as MES, nome_paciente as NOME_PACIENTE FROM PACIENTE_MEDICO_DATA GROUP BY nome_paciente, EXTRACT(MONTH FROM data_atendimento) HAVING COUNT(*) > 1 ORDER BY EXTRACT(MONTH FROM data_atendimento), nome_paciente";
			
		ResultSet rs = null;
		
		try {
			
			this.connection = connection_Postgres.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				aux = new ArrayList<String>();
				aux.add(String.valueOf(rs.getDouble("mes")));
				aux.add(rs.getString("nome_paciente"));
				retorno.add(aux);
			}
			
			ps.close();
			
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return retorno;
	}

	public ArrayList<ArrayList<String>> EnfermeirosQtdProcedimentosRealizadosMes() {
		
		ArrayList<ArrayList<String>> retorno = new ArrayList< ArrayList<String> >();
		ArrayList<String> aux;
		String sql = "SELECT e.nome as NOME_ENFERMEIRO, EXTRACT(MONTH FROM pm.data_atendimento) as MES, COUNT(m.cpf_enfermeiro) as QTD_PROCEDIMENTOS FROM enfermeiro e LEFT JOIN medicamento m ON m.cpf_enfermeiro = e.cpf, prescricao_medica pm WHERE (pm.id = m.id_prescricao) GROUP BY e.nome, m.cpf_enfermeiro, EXTRACT(MONTH FROM pm.data_atendimento) ORDER BY e.nome, EXTRACT(MONTH FROM pm.data_atendimento)";
			
		ResultSet rs = null;
		
		try {
			
			this.connection = connection_Postgres.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				aux = new ArrayList<String>();
				aux.add(rs.getString("nome_enfermeiro"));
				aux.add(String.valueOf(rs.getDouble("mes")));
				aux.add(String.valueOf(rs.getInt("qtd_procedimentos")));
				retorno.add(aux);
			}
			
			ps.close();
			
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return retorno;
	}

	public ArrayList<ArrayList<String>> EnfermeiroMedicamentoQtdAplicados() {
		
		ArrayList<ArrayList<String>> retorno = new ArrayList< ArrayList<String> >();
		ArrayList<String> aux;
		String sql = "SELECT p.nome as NOME_PACIENTE, m.nome as NOME_MEDICAMENTO, COUNT(*) as QTD_UTILIZADA  FROM paciente p, medicamento m, prescricao_medica pm WHERE pm.id = m.id_prescricao AND pm.cpf_paciente = p.cpf  GROUP BY p.nome, m.nome ORDER BY p.nome, m.nome";
			
		ResultSet rs = null;
		
		try {
			
			this.connection = connection_Postgres.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				aux = new ArrayList<String>();
				aux.add(rs.getString("nome_paciente"));
				aux.add(rs.getString("nome_medicamento"));
				aux.add(String.valueOf(rs.getInt("qtd_utilizada")));
				retorno.add(aux);
			}
			
			ps.close();
			
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return retorno;
	}

	public ArrayList<ArrayList<String>> EnfermeiroMedicamentoData() {
		
		ArrayList<ArrayList<String>> retorno = new ArrayList< ArrayList<String> >();
		ArrayList<String> aux;
		String sql = "SELECT p.nome as NOME_PACIENTE, m.nome as NOME_MEDICAMENTO, pm.data_atendimento as DATA_ATENDIMENTO FROM paciente p, medicamento m, prescricao_medica pm WHERE pm.id = m.id_prescricao AND pm.cpf_paciente = p.cpf ORDER BY p.nome, m.nome, pm.data_atendimento";
			
		ResultSet rs = null;
		
		try {
			
			this.connection = connection_Postgres.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				aux = new ArrayList<String>();
				aux.add(rs.getString("nome_paciente"));
				aux.add(rs.getString("nome_medicamento"));
				aux.add(String.valueOf(rs.getDate("data_atendimento")));
				retorno.add(aux);
			}
			
			ps.close();
			
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return retorno;
	}

	public ArrayList<ArrayList<String>> PacienteMedicoDataHorario() {
		
		ArrayList<ArrayList<String>> retorno = new ArrayList< ArrayList<String> >();
		ArrayList<String> aux;
		String sql = "SELECT p.nome as NOME_PACIENTE, m.nome as NOME_MEDICO, pm.data_atendimento as DATA_ATENDIMENTO, pm.horario as HORARIO FROM medico m, paciente p, prescricao_medica pm WHERE pm.cpf_paciente = p.cpf AND pm.cpf_medico = m.cpf ORDER BY p.nome, pm.data_atendimento, m.nome, pm.horario";
			
		ResultSet rs = null;
		
		try {
			
			this.connection = connection_Postgres.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				aux = new ArrayList<String>();
				aux.add(rs.getString("nome_paciente"));
				aux.add(rs.getString("nome_medico"));
				aux.add(String.valueOf(rs.getDate("data_atendimento")));
				aux.add(String.valueOf(rs.getTime("horario")));
				retorno.add(aux);
			}
			
			ps.close();
			
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return retorno;
	}

	public ArrayList<ArrayList<String>> MedicoMesQtdConsultas() {
		
		ArrayList<ArrayList<String>> retorno = new ArrayList< ArrayList<String> >();
		ArrayList<String> aux;
		String sql = "SELECT atendimento.nome as NOME_MEDICO, EXTRACT(MONTH FROM atendimento.data_atendimento) as MES, COUNT(*) as QTD_ATENDIMENTOS FROM (medico JOIN prescricao_medica ON (cpf = cpf_medico)) as atendimento GROUP BY atendimento.nome, atendimento.cpf, EXTRACT(MONTH FROM atendimento.data_atendimento) ORDER BY atendimento.nome, EXTRACT(MONTH FROM atendimento.data_atendimento)";
			
		ResultSet rs = null;
		
		try {
			
			this.connection = connection_Postgres.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				aux = new ArrayList<String>();
				aux.add(rs.getString("nome_medico"));
				aux.add(String.valueOf(rs.getDouble("mes")));
				aux.add(String.valueOf(rs.getInt("qtd_atendimentos")));
				retorno.add(aux);
			}
			
			ps.close();
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return retorno;
	}

	public ArrayList<ArrayList<String>> CidadeQtdPacientes() {
		
		ArrayList<ArrayList<String>> retorno = new ArrayList< ArrayList<String> >();
		ArrayList<String> aux;
		String sql = "SELECT cidade as CIDADE, COUNT(*) as QTD_PACIENTES FROM paciente GROUP BY cidade ORDER BY cidade";
			
		ResultSet rs = null;
		
		try {
			
			this.connection = connection_Postgres.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				aux = new ArrayList<String>();
				aux.add(rs.getString("cidade"));
				aux.add(String.valueOf(rs.getInt("qtd_pacientes")));
				retorno.add(aux);
			}
			
			ps.close();
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return retorno;
	}

	public ArrayList<ArrayList<String>> CepCidadeQtdPacientes() {
		
		ArrayList<ArrayList<String>> retorno = new ArrayList< ArrayList<String> >();
		ArrayList<String> aux;
		String sql = "SELECT cep as CEP, cidade as CIDADE, COUNT(*) as QTD_PACIENTES FROM paciente GROUP BY cidade, cep ORDER BY cidade, cep";
			
		ResultSet rs = null;
		
		try {
			
			this.connection = connection_Postgres.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				aux = new ArrayList<String>();
				aux.add(rs.getString("cep"));
				aux.add(rs.getString("cidade"));
				aux.add(String.valueOf(rs.getInt("qtd_pacientes")));
				retorno.add(aux);
			}
			
			ps.close();
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return retorno;
	}

	public ArrayList<ArrayList<String>> PacientesQtdAtendimento() {
		
		ArrayList<ArrayList<String>> retorno = new ArrayList< ArrayList<String> >();
		ArrayList<String> aux;
		String sql = "SELECT atendimento.nome as NOME_PACIENTE, COUNT(atendimento.cpf_paciente) as QTD_ATENDIMENTOS FROM (paciente p LEFT JOIN prescricao_medica ON (cpf = cpf_paciente)) as atendimento GROUP BY atendimento.nome ORDER BY atendimento.nome";
			
		ResultSet rs = null;
		
		try {
			
			this.connection = connection_Postgres.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				aux = new ArrayList<String>();
				aux.add(rs.getString("nome_paciente"));
				aux.add(String.valueOf(rs.getInt("qtd_atendimentos")));
				retorno.add(aux);
			}
			
			ps.close();
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return retorno;
	
	}

	public ArrayList<String> PacientesAtendidos() {
		
		ArrayList<String> retorno = new ArrayList<String>();
		String sql = "SELECT p.nome as NOME_PACIENTE FROM paciente as p WHERE EXISTS(SELECT * FROM prescricao_medica pm WHERE pm.cpf_paciente = p.cpf) ORDER BY p.nome";
			
		ResultSet rs = null;
		
		try {
			
			this.connection = connection_Postgres.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				retorno.add(rs.getString("nome_paciente"));
			}
			
			ps.close();
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return retorno;
	}

	public ArrayList<String> MedicosConsultaram() {
		
		ArrayList<String> retorno = new ArrayList<String>();
		String sql = "SELECT m.nome as NOME_MEDICO FROM medico m WHERE m.cpf IN (SELECT pm.cpf_medico FROM prescricao_medica pm) ORDER BY m.nome";
			
		ResultSet rs = null;
		
		try {
			
			this.connection = connection_Postgres.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				retorno.add(rs.getString("nome_medico"));
			}
			
			ps.close();
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return retorno;
	}

	public ArrayList<String> MedicosMaisConsultaram() {
		ArrayList<String> retorno = new ArrayList<String>();
		String sql = "SELECT m.nome as NOME_MEDICO FROM medico m, prescricao_medica pm WHERE m.cpf = pm.cpf_medico GROUP BY m.cpf, m.nome HAVING COUNT(*) >= all(SELECT COUNT(*) FROM prescricao_medica pm GROUP BY pm.cpf_medico) ORDER BY m.nome";
			
		ResultSet rs = null;
		
		try {
			
			this.connection = connection_Postgres.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				retorno.add(rs.getString("nome_medico"));
			}
			
			ps.close();
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return retorno;
	}

	public ArrayList<ArrayList<String>> EnfermeiroQtdProcedimentos() {
		
		ArrayList<ArrayList<String>> retorno = new ArrayList< ArrayList<String> >();
		ArrayList<String> aux;
		
		String sql = "SELECT e.nome as NOME_ENFERMEIRO, COUNT(m.cpf_enfermeiro) as QTD_PROCEDIMENTOS FROM enfermeiro e LEFT JOIN medicamento m ON e.cpf = m.cpf_enfermeiro GROUP BY e.nome, e.cpf ORDER BY e.nome";
			
		ResultSet rs = null;
		
		try {
			
			this.connection = connection_Postgres.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				aux = new ArrayList<String>();
				aux.add(rs.getString("nome_enfermeiro"));
				aux.add(String.valueOf(rs.getInt("qtd_procedimentos")));
				retorno.add(aux);
			}
			
			ps.close();
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return retorno;
	}

}
