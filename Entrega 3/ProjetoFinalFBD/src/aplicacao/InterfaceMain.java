package aplicacao;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import controlador.*;
import entidade.*;

public class InterfaceMain {
	
	Scanner s = new Scanner(System.in);
	
	BuscaControlador buscaC = new BuscaControlador();
	EnfermeiroControlador enfermeiroC = new EnfermeiroControlador();
	MedicamentoControlador medicamentoC = new MedicamentoControlador();
	MedicoControlador medicoC = new MedicoControlador();
	PacienteControlador pacienteC = new PacienteControlador();
	Prescricao_MedicaControlador prescricao_medicaC = new Prescricao_MedicaControlador();
	 
	
	// Exclusoes
	
	private boolean excluirMedico(){
		String cpf;
		
		System.out.println("Digite o cpf do medico: ");
		cpf = s.nextLine();
		
		return medicoC.deleta(cpf);
	}
	
	private boolean excluirEnfermeiro(){
		String cpf;
		
		System.out.println("Digite o cpf do enfermeiro: ");
		cpf = s.nextLine();
		
		return enfermeiroC.deleta(cpf);
	}
		
	private boolean excluirPaciente(){
		String cpf;
		
		System.out.println("Digite o cpf do paciente: ");
		cpf = s.nextLine();
		
		return pacienteC.deleta(cpf);
	}
	
	private boolean excluirMedicamento(){
		int id;
		
		System.out.println("Digite o id do medicamento: ");
		id = Integer.parseInt(s.nextLine());
		
		return medicamentoC.deleta(id);
	}
	
	private boolean excluirPrescricaoMedica(){
		int id;
		
		System.out.println("Digite o id da prescricao medica: ");
		id = Integer.parseInt(s.nextLine());
		
		return prescricao_medicaC.deleta(id);
	}
	
	// Buscas
	
	private void buscarMedico(){
		String cpf;
		
		System.out.println("Digite o cpf do medico: ");
		cpf = s.nextLine();
		
		Medico medico = medicoC.busca(cpf);
		if(medico == null){ 
			System.out.println("Erro ao buscar medico");
			return;
		}
		
		System.out.println("\n\n --- Médico Encontrado -- \n\n");
		
		System.out.println("Nome: " + medico.getNome());
		System.out.println("CPF: " + medico.getCpf());
		System.out.println("CRM: " + medico.getCrm());
		System.out.println("Carga Horária: " + medico.getCarga_horaria());
		System.out.println("Salário: " + medico.getSalario());
		System.out.println("Logradouro: " + medico.getEndereco().getLogradouro());
		System.out.println("CEP: " + medico.getEndereco().getCep());
		System.out.println("Cidade: " + medico.getEndereco().getCidade());
		
		System.out.println("\n--- Telefones ---\n");
		
		for (int i = 0; i < medico.getTelefones().size(); i++) {
			System.out.println("Telefone " + (i + 1) + " : " + medico.getTelefones().get(i));
		}
		
		System.out.println("\n--- Especialidades ---\n");
		
		for (int i = 0; i < medico.getEspecialidades().size(); i++) {
			System.out.println("Especialidades " + (i + 1) + " : " + medico.getEspecialidades().get(i));
		}
		
		System.out.println("\n-----------------------\n");
	}
	
	private void buscarEnfermeiro(){
		String cpf;
		
		System.out.println("Digite o cpf do enfermeiro: ");
		cpf = s.nextLine();
		
		Enfermeiro enfermeiro = enfermeiroC.busca(cpf);
		
		if(enfermeiro == null){ 
			System.out.println("Erro ao buscar enfermeiro");
			return;
		}
		
		System.out.println("\n\n --- Enfermeiro Encontrado -- \n\n");
		
		System.out.println("Nome: " + enfermeiro.getNome());
		System.out.println("CPF: " + enfermeiro.getCpf());
		System.out.println("COREN: " + enfermeiro.getCoren());
		System.out.println("Carga Horária: " + enfermeiro.getCarga_horaria());
		System.out.println("Formacao: " + enfermeiro.getFormacao());
		System.out.println("Logradouro: " + enfermeiro.getEndereco().getLogradouro());
		System.out.println("CEP: " + enfermeiro.getEndereco().getCep());
		System.out.println("Cidade: " + enfermeiro.getEndereco().getCidade());
		
		System.out.println("\n--- Telefones ---\n");
		
		for (int i = 0; i < enfermeiro.getTelefones().size(); i++) {
			System.out.println("Telefone " + (i + 1) + " : " + enfermeiro.getTelefones().get(i));
		}
				
		System.out.println("\n-----------------------\n");
	}
		
	private void buscarPaciente(){
		String cpf;
		
		System.out.println("Digite o cpf do paciente: ");
		cpf = s.nextLine();
		
		Paciente paciente = pacienteC.busca(cpf);
		
		if(paciente == null){ 
			System.out.println("Erro ao buscar paciente");
			return;
		}
		
		System.out.println("\n\n --- Paciente Encontrado -- \n\n");
		
		System.out.println("Nome: " + paciente.getNome());
		System.out.println("CPF: " + paciente.getCpf());
		System.out.println("Idade: " + paciente.getIdade());
		System.out.println("Data nascimento: " + paciente.getData_nasc());
		System.out.println("Logradouro: " + paciente.getEndereco().getLogradouro());
		System.out.println("CEP: " + paciente.getEndereco().getCep());
		System.out.println("Cidade: " + paciente.getEndereco().getCidade());
		
		System.out.println("\n--- Telefones ---\n");
		
		for (int i = 0; i < paciente.getTelefones().size(); i++) {
			System.out.println("Telefone " + (i + 1) + " : " + paciente.getTelefones().get(i));
		}
				
		System.out.println("\n-----------------------\n");
	}
	
	private void buscarMedicamento(){
		int id;
		
		System.out.println("Digite o id do medicamento: ");
		id = Integer.parseInt(s.nextLine());
		
		Medicamento medicamento = medicamentoC.busca(id);
		
		if(medicamento == null){ 
			System.out.println("Erro ao buscar medicamento");
			return;
		}
		
		System.out.println("\n\n --- Medicamento Encontrado -- \n\n");
		
		System.out.println("Id: " + medicamento.getId_remedio());
		System.out.println("Nome: " + medicamento.getNome());
		if(medicamento.isTarja_preta()){
			System.out.println("Medicamento Tarja preta");
		}
		System.out.println("Tipo: " + medicamento.getTipo_remedio());
		System.out.println("Periodicidade: " + medicamento.getPeriodicidade() + " h");
		System.out.println("Quantidade de dias: " + medicamento.getQtd_dias() + " dias");
		System.out.println("Dosagem: " + medicamento.getDosagem() + " mg");
		System.out.println("CPF enfermeiro: " + medicamento.getCpf_enfermeiro());
						
		System.out.println("\n-----------------------\n");
	}
	
	private void buscarPrescricaoMedica(){
		int id;
		
		System.out.println("Digite o id da prescricao medica: ");
		id = Integer.parseInt(s.nextLine());
		
		Prescricao_Medica prescricao_medica = prescricao_medicaC.busca(id);
		
		if(prescricao_medica == null){ 
			System.out.println("Erro ao buscar prescricao medica");
			return;
		}
		
		System.out.println("\n\n --- Prescricao Medica Encontrada -- \n\n");
		
		System.out.println("Id: " + prescricao_medica.getId());
		System.out.println("Cpf paciente: " + prescricao_medica.getCpf_paciente());
		System.out.println("Cpf medico: " + prescricao_medica.getCpf_medico());
		System.out.println("Diagnostico: " + prescricao_medica.getDescricao_diagnostico());
		System.out.println("Data: " + prescricao_medica.getDate());
		System.out.println("Horario: " + prescricao_medica.getHorario());
		
		System.out.println("Medicamentos: ");
		
		for (int i = 0; i < prescricao_medica.getMedicamentos().size(); i++) {
			System.out.println("\n--- Medicamento " + (i + 1) + " ---\n");
			System.out.println("Id: " + prescricao_medica.getMedicamentos().get(i).getId_remedio());
			System.out.println("Nome: " + prescricao_medica.getMedicamentos().get(i).getNome());
			if(prescricao_medica.getMedicamentos().get(i).isTarja_preta()){
				System.out.println("Medicamento Tarja preta");
			}
			System.out.println("Tipo: " + prescricao_medica.getMedicamentos().get(i).getTipo_remedio());
			System.out.println("Periodicidade: " + prescricao_medica.getMedicamentos().get(i).getPeriodicidade() + " h");
			System.out.println("Quantidade de dias: " + prescricao_medica.getMedicamentos().get(i).getQtd_dias() + " dias");
			System.out.println("Dosagem: " + prescricao_medica.getMedicamentos().get(i).getDosagem() + " mg");
			System.out.println("CPF enfermeiro: " + prescricao_medica.getMedicamentos().get(i).getCpf_enfermeiro());
		}
		
		
						
		System.out.println("\n-----------------------\n");
	}
	
	//insercoes
	
	private boolean inserirMedicamento(int id_prescricao){
		
		int id_remedio = medicamentoC.getId();
		boolean tarja_preta;
		String nome, tipo_remedio, aux;
		int periodicidade, qtd_dias, dosagem;
		
		System.out.println("Digite o nome do medicamento: ");
		nome = s.nextLine();
		System.out.println("Digite o tipo de medicamentos: ");
		tipo_remedio = s.nextLine();
		System.out.println("Digite s para se o medicamento é tarja preta: ");
		aux = s.nextLine();
		tarja_preta = aux.equals("s");
		System.out.println("Digite a periodicidade do medicamento: ");
		periodicidade = Integer.valueOf(s.nextLine());
		System.out.println("Digite a quantidade de dias que o medicamento deve ser tomado: ");
		qtd_dias = Integer.valueOf(s.nextLine());
		System.out.println("Digite a dosagem do medicamento: ");
		dosagem = Integer.valueOf(s.nextLine());
		
		return medicamentoC.insere(new Medicamento(id_remedio, nome, tarja_preta, tipo_remedio, periodicidade, qtd_dias, dosagem, id_prescricao));
	}
	
	private  boolean novoAtendimento(){
		
		int id = prescricao_medicaC.getId();
		
		String descricao_diagnostico, cpf_paciente, cpf_medico;
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now(); 
		java.util.Date data = new java.util.Date(dtf.format(now));  
		java.sql.Date date = new java.sql.Date(data.getTime());
		Time horario = new Time(data.getTime());
		
		int qtdMedicamentos;
		
		System.out.println("Digite a descricao do diagnostico: ");
		descricao_diagnostico = s.nextLine();
		System.out.println("Digite o cpf do paciente: ");
		cpf_paciente = s.nextLine();
		System.out.println("Digite o cpf do medico: ");
		cpf_medico = s.nextLine();
		
		if(medicoC.busca(cpf_medico) == null){
			System.out.println("Médico não encontrado");
			return false;
		}else if(pacienteC.busca(cpf_paciente) == null){
			System.out.println("Paciente não encontrado");
			return false;
		}
		
		boolean retorno = prescricao_medicaC.insere(new Prescricao_Medica(id, date, descricao_diagnostico, horario, cpf_paciente, cpf_medico, new ArrayList<Medicamento>()));
		
		if(retorno){
			
			System.out.println("Digite a quantidade de medicamentos: ");
			qtdMedicamentos = Integer.valueOf(s.nextLine());
			
			for (int i = 0; i < qtdMedicamentos; i++) {
				System.out.println("Medicamento " + (i + 1) + "\n");
				inserirMedicamento(id);
			}
		}
		
		return retorno;
	}
	
	private boolean inserirPaciente(){
		
		String nome, cpf, logradouro, cep, cidade, data = "";
		int idade, qtdTelefones;
		Date data_nasc = null;
		ArrayList<String> telefones = new ArrayList<String>();
		
		System.out.println("Digite o nome do paciente: ");
		nome = s.nextLine();
		System.out.println("Digite o cpf do paciente: ");
		cpf = s.nextLine();
		System.out.println("Digite a data de nascimento do paciente: ");
		data = s.nextLine();
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/mm/yyyy");
		java.util.Date date;
		try {
			date = sdf1.parse(data);
			data_nasc = new java.sql.Date(date.getTime());  
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println("Digite a idade do paciente: ");
		idade = Integer.valueOf(s.nextLine());
		System.out.println("Digite o logradouro do paciente: ");
		logradouro = s.nextLine();
		System.out.println("Digite o cep do paciente: ");
		cep = s.nextLine();
		System.out.println("Digite a cidade do paciente: ");
		cidade = s.nextLine();
		
		System.out.println("Digite a quantidade de telefones que o paciente possui: ");
		qtdTelefones = Integer.valueOf(s.nextLine());
		
		for (int i = 0; i < qtdTelefones; i++) {
			System.out.println("Digite o telefone " + (i + 1) + " : ");
			telefones.add(s.nextLine());
		}
		
		return pacienteC.insere(new Paciente(nome, cpf, data_nasc, idade, telefones, logradouro, cep, cidade));
	}
	
	private boolean inserirEnfermeiro(){
		
		String nome, cpf, logradouro, cep, cidade, coren, formacao;
		Double salario;
		int carga_horaria, qtdTelefones;
		ArrayList<String> telefones = new ArrayList<String>();
		
		System.out.println("Digite o nome do enfermeiro: ");
		nome = s.nextLine();
		System.out.println("Digite o salario do enfermeiro: ");
		salario = Double.valueOf(s.nextLine());
		System.out.println("Digite o cpf do enfermeiro: ");
		cpf = s.nextLine();
		System.out.println("Digite o coren do enfermeiro: ");
		coren = s.nextLine();
		System.out.println("Digite a formacao do enfermeiro: ");
		formacao = s.nextLine();
		System.out.println("Digite a carga de trabalho (em horas) do enfermeiro: ");
		carga_horaria = Integer.valueOf(s.nextLine());
		System.out.println("Digite o logradouro do enfermeiro: ");
		logradouro = s.nextLine();
		System.out.println("Digite o cep do enfermeiro: ");
		cep = s.nextLine();
		System.out.println("Digite a cidade do enfermeiro: ");
		cidade = s.nextLine();
		
		System.out.println("Digite a quantidade de telefones que o enfermeiro possui: ");
		qtdTelefones = Integer.valueOf(s.nextLine());
		
		for (int i = 0; i < qtdTelefones; i++) {
			System.out.println("Digite o telefone " + (i + 1) + " : ");
			telefones.add(s.nextLine());
		}
		
		return enfermeiroC.insere(new Enfermeiro(nome, salario, cpf, carga_horaria, logradouro, cep, cidade, coren, formacao, telefones));
	}
	
	
	private boolean inserirMedico(){
		
		String nome, cpf, logradouro, cep, cidade, crm;
		Double salario;
		int carga_horaria, qtdTelefones, qtdEspecialidades;
		ArrayList<String> telefones = new ArrayList<String>();
		ArrayList<String> especialidades = new ArrayList<String>();
		
		System.out.println("Digite o nome do médico: ");
		nome = s.nextLine();
		System.out.println("Digite o salario do médico: ");
		salario = Double.valueOf(s.nextLine());
		System.out.println("Digite o cpf do médico: ");
		cpf = s.nextLine();
		System.out.println("Digite o crm do médico: ");
		crm = s.nextLine();
		System.out.println("Digite a carga de trabalho (em horas) do médico: ");
		carga_horaria = Integer.valueOf(s.nextLine());
		System.out.println("Digite o logradouro do médico: ");
		logradouro = s.nextLine();
		System.out.println("Digite o cep do médico: ");
		cep = s.nextLine();
		System.out.println("Digite a cidade do médico: ");
		cidade = s.nextLine();
		
		System.out.println("Digite a quantidade de telefones que o médico possui: ");
		qtdTelefones = Integer.valueOf(s.nextLine());
		
		for (int i = 0; i < qtdTelefones; i++) {
			System.out.println("Digite o telefone " + (i + 1) + " : ");
			telefones.add(s.nextLine());
		}
		
		System.out.println("Digite a quantidade de especialidades que o médico possui: ");
		qtdEspecialidades = Integer.valueOf(s.nextLine());
		
		for (int i = 0; i < qtdEspecialidades; i++) {
			System.out.println("Digite a especialidade " + (i + 1) + " : ");
			especialidades.add(s.nextLine());
		}
		
		return medicoC.insere(new Medico(nome, salario, cpf, carga_horaria, logradouro, cep, cidade, crm, especialidades, telefones));
	}
	
	// atualizacoes
	
	private boolean novoProcedimento(){
		
		String cpf_enfermeiro;
		int id_medicamento;
		
		System.out.println("Digite o cpf do enfermeiro: ");
		cpf_enfermeiro = s.nextLine();
		System.out.println("Digite o id do medicamento: ");
		id_medicamento = Integer.valueOf(s.nextLine());
		
		Medicamento medicamento = medicamentoC.busca(id_medicamento);
		
		if(medicamento == null) return false;
		
		medicamento.setCpf_enfermeiro(cpf_enfermeiro);
		
		return medicamentoC.atualiza(id_medicamento, medicamento);
	}
	
	private boolean atualizarPaciente(){
		
		String nome, cpf, logradouro, cep, cidade, data = "";
		int idade, qtdTelefones;
		Date data_nasc = null;
		ArrayList<String> telefones = new ArrayList<String>();
		
		System.out.println("Digite o cpf do paciente: ");
		cpf = s.nextLine();
		
		if(pacienteC.busca(cpf) == null){
			return false;
		}
		
		System.out.println("Digite o nome do paciente: ");
		nome = s.nextLine();
		System.out.println("Digite a data de nascimento do paciente: ");
		data = s.nextLine();
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/mm/yyyy");
		java.util.Date date;
		try {
			date = sdf1.parse(data);
			data_nasc = new java.sql.Date(date.getTime());  
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println("Digite a idade do paciente: ");
		idade = Integer.valueOf(s.nextLine());
		System.out.println("Digite o logradouro do paciente: ");
		logradouro = s.nextLine();
		System.out.println("Digite o cep do paciente: ");
		cep = s.nextLine();
		System.out.println("Digite a cidade do paciente: ");
		cidade = s.nextLine();
		
		System.out.println("Digite a quantidade de telefones que o paciente possui: ");
		qtdTelefones = Integer.valueOf(s.nextLine());
		
		for (int i = 0; i < qtdTelefones; i++) {
			System.out.println("Digite o telefone " + (i + 1) + " : ");
			telefones.add(s.nextLine());
		}
		
		return pacienteC.atualiza(cpf, new Paciente(nome, cpf, data_nasc, idade, telefones, logradouro, cep, cidade));
	}
	
	private boolean atualizarEnfermeiro(){
		
		String nome, cpf, logradouro, cep, cidade, coren, formacao;
		Double salario;
		int carga_horaria, qtdTelefones;
		ArrayList<String> telefones = new ArrayList<String>();
		
		System.out.println("Digite o cpf do enfermeiro: ");
		cpf = s.nextLine();
		
		if(enfermeiroC.busca(cpf) == null){
			return false;
		}
		
		System.out.println("Digite o nome do enfermeiro: ");
		nome = s.nextLine();
		System.out.println("Digite o salario do enfermeiro: ");
		salario = Double.valueOf(s.nextLine());
		System.out.println("Digite o coren do enfermeiro: ");
		coren = s.nextLine();
		System.out.println("Digite a formacao do enfermeiro: ");
		formacao = s.nextLine();
		System.out.println("Digite a carga de trabalho (em horas) do enfermeiro: ");
		carga_horaria = Integer.valueOf(s.nextLine());
		System.out.println("Digite o logradouro do enfermeiro: ");
		logradouro = s.nextLine();
		System.out.println("Digite o cep do enfermeiro: ");
		cep = s.nextLine();
		System.out.println("Digite a cidade do enfermeiro: ");
		cidade = s.nextLine();
		
		System.out.println("Digite a quantidade de telefones que o enfermeiro possui: ");
		qtdTelefones = Integer.valueOf(s.nextLine());
		
		for (int i = 0; i < qtdTelefones; i++) {
			System.out.println("Digite o telefone " + (i + 1) + " : ");
			telefones.add(s.nextLine());
		}
		
		return enfermeiroC.atualiza(cpf, new Enfermeiro(nome, salario, cpf, carga_horaria, logradouro, cep, cidade, coren, formacao, telefones));
	}
	
	
	private boolean atualizarMedico(){
		
		String nome, cpf, logradouro, cep, cidade, crm;
		Double salario;
		int carga_horaria, qtdTelefones, qtdEspecialidades;
		ArrayList<String> telefones = new ArrayList<String>();
		ArrayList<String> especialidades = new ArrayList<String>();
		
		System.out.println("Digite o cpf do médico: ");
		cpf = s.nextLine();
		
		if(medicoC.busca(cpf) == null){
			return false;
		}
		
		System.out.println("Digite o nome do médico: ");
		nome = s.nextLine();
		System.out.println("Digite o salario do médico: ");
		salario = Double.valueOf(s.nextLine());
		System.out.println("Digite o crm do médico: ");
		crm = s.nextLine();
		System.out.println("Digite a carga de trabalho (em horas) do médico: ");
		carga_horaria = Integer.valueOf(s.nextLine());
		System.out.println("Digite o logradouro do médico: ");
		logradouro = s.nextLine();
		System.out.println("Digite o cep do médico: ");
		cep = s.nextLine();
		System.out.println("Digite a cidade do médico: ");
		cidade = s.nextLine();
		
		System.out.println("Digite a quantidade de telefones que o médico possui: ");
		qtdTelefones = Integer.valueOf(s.nextLine());
		
		for (int i = 0; i < qtdTelefones; i++) {
			System.out.println("Digite o telefone " + (i + 1) + " : ");
			telefones.add(s.nextLine());
		}
		
		System.out.println("Digite a quantidade de especialidades que o médico possui: ");
		qtdEspecialidades = Integer.valueOf(s.nextLine());
		
		for (int i = 0; i < qtdEspecialidades; i++) {
			System.out.println("Digite a especialidade " + (i + 1) + " : ");
			especialidades.add(s.nextLine());
		}
		
		return medicoC.atualiza(cpf, new Medico(nome, salario, cpf, carga_horaria, logradouro, cep, cidade, crm, especialidades, telefones));
	}
	
	private boolean atualizarMedicamento(){
		
		int id;
		System.out.println("Digite o id do medicamento: ");
		id = Integer.parseInt(s.nextLine());
		
		Medicamento medicamento = medicamentoC.busca(id);
		
		if(medicamento == null){
			return false;
		}
		
		int id_prescricao = medicamento.getId_prescricao();
		
		boolean tarja_preta;
		String nome, tipo_remedio, aux;
		int periodicidade, qtd_dias, dosagem;
		
		System.out.println("Digite o nome do medicamento: ");
		nome = s.nextLine();
		System.out.println("Digite o tipo de medicamentos: ");
		tipo_remedio = s.nextLine();
		System.out.println("Digite s para se o medicamento é tarja preta: ");
		aux = s.nextLine();
		tarja_preta = aux.equals("s");
		System.out.println("Digite a periodicidade do medicamento: ");
		periodicidade = Integer.valueOf(s.nextLine());
		System.out.println("Digite a quantidade de dias que o medicamento deve ser tomado: ");
		qtd_dias = Integer.valueOf(s.nextLine());
		System.out.println("Digite a dosagem do medicamento: ");
		dosagem = Integer.valueOf(s.nextLine());
		
		return medicamentoC.atualiza(id, new Medicamento(id, nome, tarja_preta, tipo_remedio, periodicidade, qtd_dias, dosagem, id_prescricao));
	}
	
	private boolean atualizarPrescricaoMedica(){
		
		int id;
		System.out.println("Digite o id da prescrição medica: ");
		id = Integer.parseInt(s.nextLine());
		
		Prescricao_Medica prescricao = prescricao_medicaC.busca(id);
		
		if(prescricao == null){
			return false;
		}
		
		String descricao_diagnostico, cpf_paciente, cpf_medico;
		
		System.out.println("Digite a descricao do diagnostico: ");
		descricao_diagnostico = s.nextLine();
		System.out.println("Digite o cpf do paciente: ");
		cpf_paciente = s.nextLine();
		System.out.println("Digite o cpf do medico: ");
		cpf_medico = s.nextLine();
		
		if(medicoC.busca(cpf_medico) == null){
			System.out.println("Médico não encontrado");
			return false;
		}else if(pacienteC.busca(cpf_paciente) == null){
			System.out.println("Paciente não encontrado");
			return false;
		}
		
		return prescricao_medicaC.atualiza(id, new Prescricao_Medica(id, prescricao.getDate(), descricao_diagnostico, prescricao.getHorario(), cpf_paciente, cpf_medico, new ArrayList<Medicamento>()));
		
	}
	
	// listagem

	private void listarMedicos(){
		
		ArrayList<Medico> medicos = medicoC.lista();
		
		for (int i = 0; i < medicos.size(); i++) {
			System.out.println("\n\n --- Médico " + (i + 1) +"  -- \n\n");
			
			System.out.println("Nome: " + medicos.get(i).getNome());
			System.out.println("CPF: " + medicos.get(i).getCpf());
			System.out.println("CRM: " + medicos.get(i).getCrm());
			System.out.println("Carga Horária: " + medicos.get(i).getCarga_horaria());
			System.out.println("Salário: " + medicos.get(i).getSalario());
			System.out.println("Logradouro: " + medicos.get(i).getEndereco().getLogradouro());
			System.out.println("CEP: " + medicos.get(i).getEndereco().getCep());
			System.out.println("Cidade: " + medicos.get(i).getEndereco().getCidade());
			
			System.out.println("\n--- Telefones ---\n");
			
			for (int j = 0; j < medicos.get(i).getTelefones().size(); j++) {
				System.out.println("Telefone " + (j + 1) + " : " + medicos.get(i).getTelefones().get(j));
			}
			
			System.out.println("\n--- Especialidades ---\n");
			
			for (int j = 0; j < medicos.get(i).getEspecialidades().size(); j++) {
				System.out.println("Especialidades " + (j + 1) + " : " + medicos.get(i).getEspecialidades().get(j));
			}
			
			System.out.println("\n-----------------------\n");
		}
		
	}
	
	private void listarEnfermeiros(){
		
		ArrayList<Enfermeiro> enfermeiros = enfermeiroC.lista();
		
		for (int i = 0; i < enfermeiros.size(); i++) {
			System.out.println("\n\n --- Enfermeiro " + (i + 1) +" -- \n\n");
			
			System.out.println("Nome: " + enfermeiros.get(i).getNome());
			System.out.println("CPF: " + enfermeiros.get(i).getCpf());
			System.out.println("COREN: " + enfermeiros.get(i).getCoren());
			System.out.println("Carga Horária: " + enfermeiros.get(i).getCarga_horaria());
			System.out.println("Formacao: " + enfermeiros.get(i).getFormacao());
			System.out.println("Logradouro: " + enfermeiros.get(i).getEndereco().getLogradouro());
			System.out.println("CEP: " + enfermeiros.get(i).getEndereco().getCep());
			System.out.println("Cidade: " + enfermeiros.get(i).getEndereco().getCidade());
			
			System.out.println("\n--- Telefones ---\n");
			
			for (int j = 0; j < enfermeiros.get(i).getTelefones().size(); j++) {
				System.out.println("Telefone " + (j + 1) + " : " + enfermeiros.get(i).getTelefones().get(j));
			}
					
			System.out.println("\n-----------------------\n");
		}
		
	}
		
	private void listarPacientes(){
		
		ArrayList<Paciente> pacientes = pacienteC.lista();
		
		for (int i = 0; i < pacientes.size(); i++) {
			System.out.println("\n\n --- Paciente " + (i + 1) + " -- \n\n");
			
			System.out.println("Nome: " + pacientes.get(i).getNome());
			System.out.println("CPF: " + pacientes.get(i).getCpf());
			System.out.println("Idade: " + pacientes.get(i).getIdade());
			System.out.println("Data nascimento: " + pacientes.get(i).getData_nasc());
			System.out.println("Logradouro: " + pacientes.get(i).getEndereco().getLogradouro());
			System.out.println("CEP: " + pacientes.get(i).getEndereco().getCep());
			System.out.println("Cidade: " + pacientes.get(i).getEndereco().getCidade());
			
			System.out.println("\n--- Telefones ---\n");
			
			for (int j = 0; j < pacientes.get(i).getTelefones().size(); j++) {
				System.out.println("Telefone " + (j + 1) + " : " + pacientes.get(i).getTelefones().get(j));
			}
					
			System.out.println("\n-----------------------\n");
		}
		
	}
	
	private void listarMedicamentos(){
		
		ArrayList<Medicamento> medicamentos = medicamentoC.lista();
		
		for (int i = 0; i < medicamentos.size(); i++) {
			System.out.println("\n\n --- Medicamento " + (i + 1) +" -- \n\n");
			
			System.out.println("Id: " + medicamentos.get(i).getId_remedio());
			System.out.println("Nome: " + medicamentos.get(i).getNome());
			if(medicamentos.get(i).isTarja_preta()){
				System.out.println("Medicamento Tarja preta");
			}
			System.out.println("Tipo: " + medicamentos.get(i).getTipo_remedio());
			System.out.println("Periodicidade: " + medicamentos.get(i).getPeriodicidade() + " h");
			System.out.println("Quantidade de dias: " + medicamentos.get(i).getQtd_dias() + " dias");
			System.out.println("Dosagem: " + medicamentos.get(i).getDosagem() + " mg");
			System.out.println("CPF enfermeiro: " + medicamentos.get(i).getCpf_enfermeiro());
							
			System.out.println("\n-----------------------\n");
		}
	}
	
	private void listarPrescricoesMedicas(){
		
		ArrayList<Prescricao_Medica> prescricoes = prescricao_medicaC.lista();
		
		for (int i = 0; i < prescricoes.size(); i++) {
			
			System.out.println("\n\n --- Prescricao " + (i + 1) +" -- \n\n");
			
			System.out.println("Id: " + prescricoes.get(i).getId());
			System.out.println("Cpf paciente: " + prescricoes.get(i).getCpf_paciente());
			System.out.println("Cpf medico: " + prescricoes.get(i).getCpf_medico());
			System.out.println("Diagnostico: " + prescricoes.get(i).getDescricao_diagnostico());
			System.out.println("Data: " + prescricoes.get(i).getDate());
			System.out.println("Horario: " + prescricoes.get(i).getHorario());
			
			System.out.println("Medicamentos: ");
			
			for (int j = 0; j < prescricoes.get(i).getMedicamentos().size(); j++) {
				System.out.println("\n--- Medicamento " + (j + 1) + " ---\n");
				System.out.println("Id: " + prescricoes.get(i).getMedicamentos().get(j).getId_remedio());
				System.out.println("Nome: " + prescricoes.get(i).getMedicamentos().get(j).getNome());
				if(prescricoes.get(i).getMedicamentos().get(j).isTarja_preta()){
					System.out.println("Medicamento Tarja preta");
				}
				System.out.println("Tipo: " + prescricoes.get(i).getMedicamentos().get(j).getTipo_remedio());
				System.out.println("Periodicidade: " + prescricoes.get(i).getMedicamentos().get(j).getPeriodicidade() + " h");
				System.out.println("Quantidade de dias: " + prescricoes.get(i).getMedicamentos().get(j).getQtd_dias() + " dias");
				System.out.println("Dosagem: " + prescricoes.get(i).getMedicamentos().get(j).getDosagem() + " mg");
				System.out.println("CPF enfermeiro: " + prescricoes.get(i).getMedicamentos().get(j).getCpf_enfermeiro());
			}
		
			System.out.println("\n-----------------------\n");
		}
	}

	
	// Buscas avancadas

	private void EnfermeiroQtdProcedimentos(){
		System.out.println("-----------------------");
		
		ArrayList<ArrayList<String> >response = buscaC.EnfermeiroQtdProcedimentos();
		for (int i = 0; i < response.size();i++){
			System.out.println("Enfermeiro: "+ response.get(i).get(0)+", Qtd: "+response.get(i).get(1)+"\n"); 
		}
		System.out.println("-----------------------");

	}

	private void MedicosMaisConsultaram(){
		System.out.println("-----------------------");

		ArrayList<String> response = buscaC.MedicosMaisConsultaram();
		for (int i = 0; i < response.size();i++){
			System.out.println("Medico: "+response.get(i)+"\n"); 
		}
		System.out.println("-----------------------");

	}

	private void MedicosConsultaram(){
		System.out.println("-----------------------");

		ArrayList<String> response = buscaC.MedicosConsultaram();
		for (int i = 0; i < response.size();i++){
			System.out.println("Medico: "+response.get(i)+"\n"); 
		}
		System.out.println("-----------------------");

	}
	
	private void PacientesAtendidos(){
		System.out.println("-----------------------");

		ArrayList<String> response = buscaC.PacientesAtendidos();
		for (int i = 0; i < response.size();i++){
			System.out.println("Paciente: "+response.get(i)+"\n"); 
		}
		System.out.println("-----------------------");

	}

	private void PacientesQtdAtendiment(){
		System.out.println("-----------------------");
		
		ArrayList<ArrayList<String> >response = buscaC.PacientesQtdAtendimento();
		for (int i = 0; i < response.size();i++){
			System.out.println("Paciente: "+ response.get(i).get(0)+", Qtd: "+response.get(i).get(1)+"\n"); 
		}
		System.out.println("-----------------------");

	}

	private void CepCidadeQtdPacient(){
		System.out.println("-----------------------");
		
		ArrayList<ArrayList<String> >response = buscaC.CepCidadeQtdPacientes();
		for (int i = 0; i < response.size();i++){
			System.out.println("Cep: "+ response.get(i).get(0)+", Cidade: "+response.get(i).get(1)+", Qtd: "+response.get(i).get(2)+"\n"); 
		}
		System.out.println("-----------------------");

	}


	private  void CidadeQtdPacient(){
		System.out.println("-----------------------");
		
		ArrayList<ArrayList<String> >response = buscaC.CidadeQtdPacientes();
		for (int i = 0; i < response.size();i++){
			System.out.println("Cidade: "+ response.get(i).get(0)+", Qtd: "+response.get(i).get(1)+"\n"); 
		}
		System.out.println("-----------------------");

	}

	private void MedicoMesQtdConsult(){
		System.out.println("-----------------------");
		
		ArrayList<ArrayList<String> >response = buscaC.MedicoMesQtdConsultas();
		for (int i = 0; i < response.size();i++){
			System.out.println("Medico: "+ response.get(i).get(0)+", Mes: "+response.get(i).get(1)+", Qtd: "+response.get(i).get(2)+"\n"); 
		}
		System.out.println("-----------------------");

	}

	private void PacienteMedicoDataHorario(){
		System.out.println("-----------------------");
		
		ArrayList<ArrayList<String> >response = buscaC.PacienteMedicoDataHorario();
		for (int i = 0; i < response.size();i++){
			System.out.println("Paciente: "+ response.get(i).get(0)+", Medico: "+response.get(i).get(1)+", Data: "+response.get(i).get(2)+", Horario: "+response.get(i).get(3)+"\n"); 
		}
		System.out.println("-----------------------");

	}

	private void EnfermeMedicamentoData(){
		System.out.println("-----------------------");
		
		ArrayList<ArrayList<String> >response = buscaC.EnfermeiroMedicamentoData();
		for (int i = 0; i < response.size();i++){
			System.out.println("Enfermeiro: "+ response.get(i).get(0)+", Medicamento: "+response.get(i).get(1)+", Data: "+response.get(i).get(2)+"\n"); 
		}
		System.out.println("-----------------------");

	}
	
	private void EnfermeMedicamentoQtdApli(){
		System.out.println("-----------------------");
		
		ArrayList<ArrayList<String> >response = buscaC.EnfermeiroMedicamentoQtdAplicados();
		for (int i = 0; i < response.size();i++){
			System.out.println("Enfermeiro: "+ response.get(i).get(0)+", Medicamento: "+response.get(i).get(1)+", Qtd: "+response.get(i).get(2)+"\n"); 
		}
		System.out.println("-----------------------");

	}
	
	private void EnfermeQtdProcRealMes(){
		System.out.println("-----------------------");

		ArrayList<ArrayList<String> >response = buscaC.EnfermeirosQtdProcedimentosRealizadosMes();
		for (int i = 0; i < response.size();i++){
			System.out.println("Enfermeiro: "+ response.get(i).get(0)+", Mes: "+response.get(i).get(1)+", Qtd: "+response.get(i).get(2)+"\n"); 
		}
		System.out.println("-----------------------");

	}
	
	private void PacientesAtendMaisDeUmaVezMes(){
		System.out.println("-----------------------");

		ArrayList<ArrayList<String> >response = buscaC.PacientesAtendidosMaisDeUmaVezPorMes();
		for (int i = 0; i < response.size();i++){
			System.out.println("Paciente: "+ response.get(i).get(1) +", Mes: "+response.get(i).get(0)+"\n"); 
		}
		System.out.println("-----------------------");
	}

	private void PacientesAtendMaisDeUmaVezView(){
		System.out.println("-----------------------");

		ArrayList<String> response = buscaC.PacientesAtendidosMaisDeUmaVezView();
		for (int i = 0; i < response.size();i++){
			System.out.println("Paciente: "+response.get(i)+"\n"); 
		}
		System.out.println("-----------------------");

	}
	
	private void PacientesAtendMaisDeUmaVezTable(){
		System.out.println("-----------------------");

		ArrayList<String> response = buscaC.PacientesAtendidosMaisDeUmaVezTable();
		for (int i = 0; i < response.size();i++){
			System.out.println("Paciente: "+response.get(i)+"\n"); 
		}
		System.out.println("-----------------------");

	}

	private void MenuConsultas(){
		
		int opcao_cons;
		System.out.println("Digite: \n" +
							"1 - Para consultar Pacientes atendidos mais de uma vez (view) \n" +
							"2 - Para consultar Pacientes atendidos mais de uma vez (table) \n" +
							"3 - Para consultar Pacientes antendidos mais de uma vez por mês\n" +
							"4 - Para consultar qtd procedimento realizados por Enfermeiro por mês \n" +
							"5 - Para consultar qtd de Medicamento Aplicado por Enfermeiro \n" +
							"6 - Para consultar data da aplicação de Medicamentos por Paciente  \n" +
							"7 - Para consultar relação entre Paciente e Medicos \n" +
							"8 - Para consultar qtd de consultas dos Medicos por mes\n" +
							"9 - Para consultar a qtd de Pacientes por cidade \n" +
							"10 - Para consultar cep da cidade com qtd de Pacientes \n" +
							"11 - Para consultar qtd de atendimentos por Paciente \n" +
							"12 - Para consultar Pacientes atendidos \n" +
							"13 - Para consultar Médicos que consultaram \n" +
							"14 - Para consultar Médicos que mais consultaram \n" +
							"15 - Para consultar qtd de Procedimentos dos Enfermeiros \n" +
							"16 - Para voltar \n");
		
		opcao_cons = Integer.valueOf(s.nextLine());

		switch (opcao_cons) {
			case 1:
				PacientesAtendMaisDeUmaVezView();
				break;
			case 2:
				PacientesAtendMaisDeUmaVezTable();
				break;
			case 3:
				PacientesAtendMaisDeUmaVezMes();
				break;
			case 4:
				EnfermeQtdProcRealMes();
				break;
			case 5:
				EnfermeMedicamentoQtdApli();
				break;
			case 6:
				EnfermeMedicamentoData();
				break;
			case 7:
				PacienteMedicoDataHorario();
				break;
			case 8:
				MedicoMesQtdConsult();
				break;
			case 9:
				CidadeQtdPacient();
				break;
			case 10:
				CepCidadeQtdPacient();
				break;
			case 11:
				PacientesQtdAtendiment();
				break;
			case 12:
				PacientesAtendidos();
				break;
			case 13:
				MedicosConsultaram();
				break;
			case 14:
				MedicosMaisConsultaram();
				break;
			case 15:
				EnfermeiroQtdProcedimentos();
				break;
			case 16:
				break;
			default:
				System.out.println("Opção inválida!");
				MenuConsultas();
				break;
		}
	
	} 
	
	private void Menu(){
		int opcao;
		System.out.println("------------------ Menu ------------------");
		System.out.println("Digite: \n" +
							"1 - Para inserir novo Medico \n" +
							"2 - Para inserir novo Enfermeiro\n" +
							"3 - Para inserir novo Paciente \n" +
							"4 - Para novo Atendimento \n" +
							"5 - Para novo Procedimento \n" +
							"6 - Para buscar Medico \n" +
							"7 - Para buscar Enfermeiro\n" +
							"8 - Para buscar Paciente \n" +
							"9 - Para buscar Medicamento\n" +
							"10 - Para buscar Prescrição Medica \n" +
							"11 - Para excluir Medico \n" +
							"12 - Para excluir Enfermeiro \n" +
							"13 - Para excluir Paciente \n" +
							"14 - Para excluir Medicamento\n" +
							"15 - Para excluir Prescricao Medica \n" +
							"16 - Para atualizar Medico \n" +
							"17 - Para atualizar Enfermeiro \n" +
							"18 - Para atualizar Paciente \n" +
							"19 - Para atualizar Medicamento\n" +
							"20 - Para atualizar Prescricao Medica \n" +
							"21 - Para listar Medicos \n" +
							"22 - Para listar Enfermeiros \n" +
							"23 - Para listar Pacientes \n" +
							"24 - Para listar Medicamentos\n" +
							"25 - Para listar Prescrições Medicas \n" +
							"26 - Para realizar consultas \n" +
							"27 - Para sair \n");
		
		opcao = Integer.valueOf(s.nextLine());
		switch (opcao) {
			case 1:
				if(inserirMedico()){
					System.out.println("Médico inserido com sucesso");
				}else{
					System.out.println("Erro ao inserir médico");
				}
				break;
			case 2:
				if(inserirEnfermeiro()){
					System.out.println("Enfermeiro inserido com sucesso");
				}else{
					System.out.println("Erro ao inserir enfermeiro");
				}
				break;
			case 3:
				if(inserirPaciente()){
					System.out.println("Paciente inserido com sucesso");
				}else{
					System.out.println("Erro ao inserir medico");
				}
				break;
			case 4:
				if(novoAtendimento()){
					System.out.println("Atedimento armazenado com sucesso");
				}else{
					System.out.println("Erro ao armazenar o atendimento");
				}
				break;
			case 5:
				if(novoProcedimento()){
					System.out.println("Procedimento armazenado com sucesso");
				}else{
					System.out.println("Erro ao armazenar o procedimento");
				}
				break;
			case 6:
				buscarMedico();
				break;
			case 7:
				buscarEnfermeiro();
				break;
			case 8:
				buscarPaciente();
				break;
			case 9:
				buscarMedicamento();
				break;
			case 10:
				buscarPrescricaoMedica();
				break;
			case 11:
				if(excluirMedico()){
					System.out.println("Médico excluido com sucesso");
				}else{
					System.out.println("Erro ao excluir médico");
				}
				break;
			case 12:
				if(excluirEnfermeiro()){
					System.out.println("Enfermeiro excluido com sucesso");
				}else{
					System.out.println("Erro ao excluir enfermeiro");
				}
				break;
			case 13:
				if(excluirPaciente()){
					System.out.println("Paciente excluido com sucesso");
				}else{
					System.out.println("Erro ao excluir paciente");
				}
				break;
			case 14:
				if(excluirMedicamento()){
					System.out.println("Medicamento excluido com sucesso");
				}else{
					System.out.println("Erro ao excluir medicamento");
				}
				break;
			case 15:
				if(excluirPrescricaoMedica()){
					System.out.println("Prescrição excluido com sucesso");
				}else{
					System.out.println("Erro ao excluir prescrição");
				}
				break;
			case 16:
				if(atualizarMedico()){
					System.out.println("Medico atualizado com sucesso");
				}else{
					System.out.println("Erro ao atualizar medico");
				}
				break;	
			case 17:
				if(atualizarEnfermeiro()){
					System.out.println("Enfermeiro atualizado com sucesso");
				}else{
					System.out.println("Erro ao atualizar enfermeiro");
				}
				break;
			case 18:
				if(atualizarPaciente()){
					System.out.println("Paciente atualizado com sucesso");
				}else{
					System.out.println("Erro ao atualizar paciente");
				}
				break;
			case 19:
				if(atualizarMedicamento()){
					System.out.println("Medicamento atualizado com sucesso");
				}else{
					System.out.println("Erro ao atualizar medicamento");
				}
				break;
			case 20:
				if(atualizarPrescricaoMedica()){
					System.out.println("Prescrição atualizada com sucesso");
				}else{
					System.out.println("Erro ao atualizar prescrição");
				}
				break;
			case 21:
				listarMedicos();
				break;
			case 22:
				listarEnfermeiros();
				break;
			case 23:
				listarPacientes();
				break;
			case 24:
				listarMedicamentos();
				break;
			case 25:
				listarPrescricoesMedicas();
				break;
			case 26:
				MenuConsultas();
				break;
			case 27:
				System.out.println("Você saiu!!!");
				System.exit(0);
				break;	
			default:
				System.out.println("Opção inválida");
				break;
		}
		
	}

	public void start() {
		
		System.out.println("Seja bem-vindo ao Sistema de Gerenciamento de Consultas Médicas (SGCM)\n\n");
		
		while(true){
			Menu();
		}
	}
	
}
