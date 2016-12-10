package locacao.model;

public class Atendente extends Pessoa{
	private float salario;
	
	public float getSalario() {
		return salario;
	}

	public Atendente(int idPessoa, String nome, String cpf, String endereco, String telefone, String email) {
		super(idPessoa, nome, cpf, endereco, telefone, email);
	}
	
	
}
