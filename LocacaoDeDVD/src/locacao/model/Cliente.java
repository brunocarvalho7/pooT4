package locacao.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Cliente extends Pessoa{

	ObservableList<Aluguel> historico = FXCollections.observableArrayList();
	private float rsAlugado;
	
	public Cliente(int idPessoa, String nome, String cpf, String endereco, String telefone, String email) {
		super(idPessoa, nome, cpf, endereco, telefone, email);
		rsAlugado = 0;
	}
	
	public Cliente(int idPessoa, String nome, String cpf, String endereco, String telefone, String email, 
			ObservableList<Aluguel> historico) {
		super(idPessoa, nome, cpf, endereco, telefone, email);
		this.historico = historico;
		rsAlugado = 0;
	}

	public ObservableList<Aluguel> getHistorico() {
		return historico;
	}

	public void setHistorico(ObservableList<Aluguel> historico) {
		this.historico = historico;
	}

	public float getRsAlugado() {
		return rsAlugado;
	}

	public void setRsAlugado(float rsAlugado) {
		this.rsAlugado = rsAlugado;
	}

}
