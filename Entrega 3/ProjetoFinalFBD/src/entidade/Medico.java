package entidade;

import java.util.ArrayList;


public class Medico extends Funcionario {

	private String crm;
	private ArrayList<String> especialidades;
	
	public Medico(){
		
	}
	
	public Medico(String nome, double salario, String cpf, int carga_horaria, String logradouro, String cep, String cidade, String crm, ArrayList<String> especialidades, ArrayList<String> telefones){
		this.setNome(nome);
		this.setCpf(cpf);
		this.setCarga_horaria(carga_horaria);
		this.setEndereco(new Endereco(logradouro, cep, cidade));
		this.setCrm(crm);
		this.setEspecialidades(especialidades);
		this.setTelefones(telefones);
		this.setSalario(salario);
	}
	
	public String getCrm() {
		return crm;
	}
	
	public void setCrm(String crm) {
		this.crm = crm;
	}
	
	public ArrayList<String> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(ArrayList<String> especialidades) {
		this.especialidades = especialidades;
	}

}
