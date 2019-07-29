package controlador;

import java.util.ArrayList;

import conexao.Conexao_Postgres;
import dao.MedicamentoDAO;
import entidade.Medicamento;

public class MedicamentoControlador {

	Conexao_Postgres connection_Postgres = new Conexao_Postgres();
	MedicamentoDAO medicamentoDAO = new MedicamentoDAO(connection_Postgres);
		
	public int getId(){
		return medicamentoDAO.getId();
	}
	
	public Medicamento busca(int id) {
		return medicamentoDAO.busca(id);
	}
	
	public boolean insere(Medicamento medicamento) {
		return medicamentoDAO.insere(medicamento);
	}
	
	public boolean deleta (int id) {
		return medicamentoDAO.deleta(id);
	}
	
	public boolean atualiza (int id, Medicamento medicamento) {
		return medicamentoDAO.atualiza(id, medicamento);
	}
	
	public ArrayList<Medicamento> lista() {
		return medicamentoDAO.lista();
	}
	
}
