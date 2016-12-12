package locacao.model;

public class Produtor {

	private int idProdutor;
	private String nome;
	
	public Produtor(int idProdutor, String nome) {
		this.idProdutor = idProdutor;
		this.nome = nome;
	}
	
	public int getIdProdutor() {
		return idProdutor;
	}
	public void setIdProdutor(int idProdutor) {
		this.idProdutor = idProdutor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String toString() {
		return nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idProdutor;
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
		Produtor other = (Produtor) obj;
		if (idProdutor != other.idProdutor)
			return false;
		return true;
	}
	
	
	
}
