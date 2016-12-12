package locacao.model;

public class Gravadora {

	private int idGravadora;
	private String nome;
	
	public Gravadora(int idGravadora, String nome) {
		super();
		this.idGravadora = idGravadora;
		this.nome = nome;
	}

	public int getIdGravadora() {
		return idGravadora;
	}

	public void setIdGravadora(int idGravadora) {
		this.idGravadora = idGravadora;
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
		result = prime * result + idGravadora;
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
		Gravadora other = (Gravadora) obj;
		if (idGravadora != other.idGravadora)
			return false;
		return true;
	}
	

	
}
