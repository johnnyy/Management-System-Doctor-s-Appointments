package entidade;

public class Endereco {

	private String logradouro;
	private String cep;
	private String cidade;
	
	public Endereco(){
		
	}
	
	public Endereco(String logradouro, String cep, String cidade){
		this.logradouro = logradouro;
		this.cep = cep;this.cidade = cidade;
	}
	
	public String getLogradouro() {
		return logradouro;
	}
	
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}	

}
