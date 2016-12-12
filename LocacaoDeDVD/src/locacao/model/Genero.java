package locacao.model;

public class Genero {

	private int idGenero;
	private String nome;
	
	public Genero(int idGenero, String nome) {
		super();
		this.idGenero = idGenero;
		this.nome = nome;
	}

	public int getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idGenero;
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
		Genero other = (Genero) obj;
		if (idGenero != other.idGenero)
			return false;
		return true;
	}
	
	
	
}
