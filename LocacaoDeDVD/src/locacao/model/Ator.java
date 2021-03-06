package locacao.model;

public class Ator {

	public Ator(int idAtor, String nome) {
		super();
		this.idAtor = idAtor;
		this.nome = nome;
	}

	private int idAtor;
	private String nome;
	
	public int getIdAtor() {
		return idAtor;
	}
	public void setIdAtor(int idAtor) {
		this.idAtor = idAtor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ator other = (Ator) obj;
		if (idAtor != other.idAtor)
			return false;
		return true;
	}
	public String toString() {
		return nome;
	}
	
}
