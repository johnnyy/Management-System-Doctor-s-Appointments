package controlador;
import java.util.ArrayList;

import conexao.Conexao_Postgres;
import dao.MedicoDAO;
import entidade.Medico;

public class MedicoControlador {

	Conexao_Postgres connection_Postgres = new Conexao_Postgres();
	MedicoDAO medicoDAO = new MedicoDAO(connection_Postgres);
	
	public Medico busca(String cpf) {
		return medicoDAO.busca(cpf);
	}
	
	public boolean insere(Medico medico) {
		return medicoDAO.insere(medico);
	}
	
	public boolean deleta (String cpf) {
		return medicoDAO.deleta(cpf);
	}
	
	public boolean atualiza (String cpf, Medico medico) {
		return medicoDAO.atualiza(cpf, medico);
	}
	
	public ArrayList<Medico> lista() {
		return medicoDAO.lista();
	}

}
