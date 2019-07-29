package entidade;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class Prescricao_Medica {

	private int id;
	private Date date;
	private String descricao_diagnostico;
	private Time horario;
	private String cpf_paciente;
	private String cpf_medico;
	private ArrayList<Medicamento> medicamentos;
	
	public Prescricao_Medica(){
		
	}
	
	public Prescricao_Medica(int id, Date date, String descricao_diagnostico, Time horario, String cpf_paciente, String cpf_medico, ArrayList<Medicamento> medicamentos){
		this.setId(id);
		this.setDate(date);
		this.setDescricao_diagnostico(descricao_diagnostico);
		this.setHorario(horario);
		this.setMedicamentos(medicamentos);
		this.setCpf_medico(cpf_medico);
		this.setCpf_paciente(cpf_paciente);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescricao_diagnostico() {
		return descricao_diagnostico;
	}

	public void setDescricao_diagnostico(String descricao_diagnostico) {
		this.descricao_diagnostico = descricao_diagnostico;
	}

	public Time getHorario() {
		return horario;
	}

	public void setHorario(Time horario) {
		this.horario = horario;
	}

	public ArrayList<Medicamento> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(ArrayList<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCpf_paciente() {
		return cpf_paciente;
	}

	public void setCpf_paciente(String cpf_paciente) {
		this.cpf_paciente = cpf_paciente;
	}

	public String getCpf_medico() {
		return cpf_medico;
	}

	public void setCpf_medico(String cpf_medico) {
		this.cpf_medico = cpf_medico;
	}
	
}
