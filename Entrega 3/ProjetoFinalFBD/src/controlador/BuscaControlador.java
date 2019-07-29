package controlador;

import java.util.ArrayList;

import buscas.Busca;

import conexao.Conexao_Postgres;

public class BuscaControlador {

	Conexao_Postgres connection_Postgres = new Conexao_Postgres();
	Busca busca = new Busca(connection_Postgres);
	
	public ArrayList<String> PacientesAtendidosMaisDeUmaVezView() {
		return busca.PacientesAtendidosMaisDeUmaVezView();
	}
	
	public ArrayList<String> PacientesAtendidosMaisDeUmaVezTable() {
		return busca.PacientesAtendidosMaisDeUmaVezTable();
	}

	public ArrayList<ArrayList<String>> PacientesAtendidosMaisDeUmaVezPorMes() {
		return busca.PacientesAtendidosMaisDeUmaVezPorMes();
	}

	public ArrayList<ArrayList<String>> EnfermeirosQtdProcedimentosRealizadosMes() {
		return busca.EnfermeirosQtdProcedimentosRealizadosMes();
	}

	public ArrayList<ArrayList<String>> EnfermeiroMedicamentoQtdAplicados() {
		return busca.EnfermeiroMedicamentoQtdAplicados();
	}

	public ArrayList<ArrayList<String>> EnfermeiroMedicamentoData() {		
		return busca.EnfermeiroMedicamentoData();
	}

	public ArrayList<ArrayList<String>> PacienteMedicoDataHorario() {
		return busca.PacienteMedicoDataHorario();
	}

	public ArrayList<ArrayList<String>> MedicoMesQtdConsultas() {
		return busca.MedicoMesQtdConsultas();
	}

	public ArrayList<ArrayList<String>> CidadeQtdPacientes() {
		return busca.CidadeQtdPacientes();
	}

	public ArrayList<ArrayList<String>> CepCidadeQtdPacientes() {
		return busca.CepCidadeQtdPacientes();
	}

	public ArrayList<ArrayList<String>> PacientesQtdAtendimento() {
		return busca.PacientesQtdAtendimento();
	}

	public ArrayList<String> PacientesAtendidos() {
		return busca.PacientesAtendidos();
	}

	public ArrayList<String> MedicosConsultaram() {
		return busca.MedicosConsultaram();
	}

	public ArrayList<String> MedicosMaisConsultaram() {
		return busca.MedicosMaisConsultaram();
	}

	public ArrayList<ArrayList<String>> EnfermeiroQtdProcedimentos() {
		return busca.EnfermeiroQtdProcedimentos();
	}
}
