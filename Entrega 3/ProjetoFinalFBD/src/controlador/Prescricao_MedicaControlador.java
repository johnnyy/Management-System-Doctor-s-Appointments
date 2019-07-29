package controlador;
import java.util.ArrayList;

import conexao.Conexao_Postgres;
import dao.Prescricao_MedicaDAO;
import entidade.Prescricao_Medica;

public class Prescricao_MedicaControlador {

	Conexao_Postgres connection_Postgres = new Conexao_Postgres();
	Prescricao_MedicaDAO prescricao_medicaDAO = new Prescricao_MedicaDAO(connection_Postgres);
	
	public int getId(){
		return prescricao_medicaDAO.getId();
	}
	
	public Prescricao_Medica busca(int id) {
		return prescricao_medicaDAO.busca(id);
	}
	
	public boolean insere(Prescricao_Medica prescricao_medica) {
		return prescricao_medicaDAO.insere(prescricao_medica);
	}
	
	public boolean deleta (int id) {
		return prescricao_medicaDAO.deleta(id);
	}
	
	public boolean atualiza (int id, Prescricao_Medica prescricao_medica) {
		return prescricao_medicaDAO.atualiza(id, prescricao_medica);
	}
	
	public ArrayList<Prescricao_Medica> lista() {
		return prescricao_medicaDAO.lista();
	}
	
}
