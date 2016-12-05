package locacao.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa{

	private List<Aluguel> historico = new ArrayList<>();
	private float rsAlugado;
	
	public Cliente(int idPessoa, String nome, String cpf, String endereco, String telefone, String email) {
		super(idPessoa, nome, cpf, endereco, telefone, email);
		rsAlugado = 0;
	}

	public List<Aluguel> getHistorico() {
		return historico;
	}

	public void setHistorico(List<Aluguel> historico) {
		this.historico = historico;
	}

	public float getRsAlugado() {
		return rsAlugado;
	}

	public void setRsAlugado(float rsAlugado) {
		this.rsAlugado = rsAlugado;
	}

	@Override
	public String toString() {
		return "Pessoa [idPessoa=" + getIdPessoa() + ", nome=" + getNome() + ", cpf=" + getCpf() + ", endereco=" + getEndereco()
				+ ", telefone=" + getTelefone() + ", email=" + getEmail() + ", historico=" + historico 
				+ ", rsAlugado=" + rsAlugado + "]";
	}

}
