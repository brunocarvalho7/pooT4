package locacao.model;

public abstract class Pessoa {

	private int idPessoa;
	private String nome;
	private String cpf;
	private String endereco;
	private String telefone;
	private String email;
	
	public Pessoa(int idPessoa, String nome, String cpf, String endereco, String telefone, String email) {
		super();
		this.idPessoa = idPessoa;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
	}

	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String toString() {
		return "Pessoa [idPessoa=" + idPessoa + ", nome=" + nome + ", cpf=" + cpf + ", endereco=" + endereco
				+ ", telefone=" + telefone + ", email=" + email + "]";
	}
	
	public boolean equals(Pessoa p){
		if(p.getCpf().equals(getCpf())){
			return true;
		}
		
		return false;
	}
	
	
}
