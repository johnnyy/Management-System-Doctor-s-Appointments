package entidade;
import java.sql.Date;
import java.util.ArrayList;

public class Paciente {

	private String nome;
	private String cpf;
	private Date data_nasc;
	private int idade;
	private ArrayList<String> telefones;
	private Endereco endereco;
	
	public Paciente(){
		
	}
	
	public Paciente(String nome, String cpf, Date data_nasc, int idade, ArrayList<String> telefones, String logradouro, String cep, String cidade){
		this.setNome(nome);
		this.setCpf(cpf);
		this.setData_nasc(data_nasc);
		this.setIdade(idade);
		this.setTelefones(telefones);
		this.setEndereco(new Endereco(logradouro, cep, cidade));
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Date getData_nasc() {
		return data_nasc;
	}
	
	public void setData_nasc(Date data_nasc) {
		this.data_nasc = data_nasc;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public ArrayList<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(ArrayList<String> telefones) {
		this.telefones = telefones;
	}
	
}
