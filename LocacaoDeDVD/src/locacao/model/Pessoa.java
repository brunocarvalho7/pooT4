package locacao.model;

public abstract class Pessoa {

	private int idPessoa;
	private String nome;
	private String cpf;
	private String endereco;
	private String telefone;
	private String email;
	
	public Pessoa(){
		this(0,"","","","","");
	}
	
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
		return nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPessoa;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (idPessoa != other.idPessoa)
			return false;
		return true;
	}
	

	
	
}
