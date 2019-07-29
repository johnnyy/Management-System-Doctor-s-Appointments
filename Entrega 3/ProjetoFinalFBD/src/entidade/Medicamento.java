package entidade;

public class Medicamento {

	private int id_remedio;
	private String nome;
	private boolean tarja_preta;
	private String tipo_remedio;
	private int periodicidade;
	private int qtd_dias;
	private int dosagem;
	private int id_prescricao;
	private String cpf_enfermeiro = null;
	
	public Medicamento(){
		
	}
	
	public Medicamento(int id_remedio, String nome, boolean tarja_preta, String tipo_remedio, int periodicidade, int qtd_dias, int dosagem, int id_prescricao) {
		this.setId_remedio(id_remedio);
		this.setNome(nome);
		this.setTarja_preta(tarja_preta);
		this.setTipo_remedio(tipo_remedio);
		this.setPeriodicidade(periodicidade);
		this.setQtd_dias(qtd_dias);
		this.setDosagem(dosagem);
		this.setId_prescricao(id_prescricao);
	}

	public int getId_remedio() {
		return id_remedio;
	}

	public void setId_remedio(int id_remedio) {
		this.id_remedio = id_remedio;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isTarja_preta() {
		return tarja_preta;
	}

	public void setTarja_preta(boolean tarja_preta) {
		this.tarja_preta = tarja_preta;
	}

	public String getTipo_remedio() {
		return tipo_remedio;
	}

	public void setTipo_remedio(String tipo_remedio) {
		this.tipo_remedio = tipo_remedio;
	}

	public int getPeriodicidade() {
		return periodicidade;
	}

	public void setPeriodicidade(int periodicidade) {
		this.periodicidade = periodicidade;
	}

	public int getQtd_dias() {
		return qtd_dias;
	}

	public void setQtd_dias(int qtd_dias) {
		this.qtd_dias = qtd_dias;
	}

	public int getDosagem() {
		return dosagem;
	}

	public void setDosagem(int dosagem) {
		this.dosagem = dosagem;
	}

	public int getId_prescricao() {
		return id_prescricao;
	}

	public void setId_prescricao(int id_prescricao) {
		this.id_prescricao = id_prescricao;
	}

	public String getCpf_enfermeiro() {
		return cpf_enfermeiro;
	}

	public void setCpf_enfermeiro(String cpf_enfermeiro) {
		this.cpf_enfermeiro = cpf_enfermeiro;
	}
	
}
