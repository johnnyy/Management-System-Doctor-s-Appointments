package controlador;
import java.util.ArrayList;
import conexao.Conexao_Postgres;
import dao.EnfermeiroDAO;
import entidade.Enfermeiro;


public class EnfermeiroControlador {
	
	Conexao_Postgres connection_Postgres = new Conexao_Postgres();
	EnfermeiroDAO enfermeiroDAO = new EnfermeiroDAO(connection_Postgres);
	
	public Enfermeiro busca(String cpf) {
		return enfermeiroDAO.busca(cpf);
	}
	
	public boolean insere(Enfermeiro enfermeiro) {
		return enfermeiroDAO.insere(enfermeiro);
	}
	
	public boolean deleta (String cpf) {
		return enfermeiroDAO.deleta(cpf);
	}
	
	public boolean atualiza (String cpf, Enfermeiro enfermeiro) {
		return enfermeiroDAO.atualiza(cpf, enfermeiro);
	}
	
	public ArrayList<Enfermeiro> lista() {
		return enfermeiroDAO.lista();
	}
	
}
