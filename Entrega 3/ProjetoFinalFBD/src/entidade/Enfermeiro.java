package entidade;

import java.util.ArrayList;


public class Enfermeiro extends Funcionario{
	
	private String coren;
	private String formacao;
	
	public Enfermeiro(){
		
	}
	
	public Enfermeiro(String nome, double salario, String cpf, int carga_horaria, String logradouro, String cep, String cidade, String coren, String formacao, ArrayList<String> telefones){
		this.setNome(nome);
		this.setCpf(cpf);
		this.setCarga_horaria(carga_horaria);
		this.setEndereco(new Endereco(logradouro, cep, cidade));
		this.setCoren(coren);
		this.setFormacao(formacao);
		this.setTelefones(telefones);
		this.setSalario(salario);
	}
	
	public String getCoren() {
		return coren;
	}
	
	public void setCoren(String coren) {
		this.coren = coren;
	}
	
	public String getFormacao() {
		return formacao;
	}
	
	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}
	
	
}
