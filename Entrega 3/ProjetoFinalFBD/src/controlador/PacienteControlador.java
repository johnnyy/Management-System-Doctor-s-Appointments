package controlador;
import java.util.ArrayList;

import conexao.Conexao_Postgres;
import dao.PacienteDAO;
import entidade.Paciente;

public class PacienteControlador {

	Conexao_Postgres connection_Postgres = new Conexao_Postgres();
	PacienteDAO pacienteDAO = new PacienteDAO(connection_Postgres);
	
	public Paciente busca(String cpf) {
		return pacienteDAO.busca(cpf);
	}
	
	public boolean insere(Paciente paciente) {
		return pacienteDAO.insere(paciente);
	}
	
	public boolean deleta (String cpf) {
		return pacienteDAO.deleta(cpf);
	}
	
	public boolean atualiza (String cpf, Paciente paciente) {
		return pacienteDAO.atualiza(cpf, paciente);
	}
	
	public ArrayList<Paciente> lista() {
		return pacienteDAO.lista();
	}
	
}
