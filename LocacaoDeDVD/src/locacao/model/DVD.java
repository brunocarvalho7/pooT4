package locacao.model;

import java.util.ArrayList;
import java.util.List;

public class DVD {
	private int idDvd;
	private int genero;
	private String titulo;
	private List<Ator> atores = new ArrayList<>();
	private List<Produtor> produtores = new ArrayList<>();
	private int gravadora;
	private int quantEstoque;
	private float rsAluguel;
	
	public DVD(int idDvd, int genero, String titulo, List<Ator> atores, List<Produtor> produtores, int gravadora,
			int quantEstoque, float rsAluguel) {
		super();
		this.idDvd = idDvd;
		this.genero = genero;
		this.titulo = titulo;
		this.atores = atores;
		this.produtores = produtores;
		this.gravadora = gravadora;
		this.quantEstoque = quantEstoque;
		this.rsAluguel = rsAluguel;
	}

	public int getIdDvd() {
		return idDvd;
	}

	public void setIdDvd(int idDvd) {
		this.idDvd = idDvd;
	}

	public int getGenero() {
		return genero;
	}

	public void setGenero(int genero) {
		this.genero = genero;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Ator> getAtores() {
		return atores;
	}

	public void setAtores(List<Ator> atores) {
		this.atores = atores;
	}

	public List<Produtor> getProdutores() {
		return produtores;
	}

	public void setProdutores(List<Produtor> produtores) {
		this.produtores = produtores;
	}

	public int getGravadora() {
		return gravadora;
	}

	public void setGravadora(int gravadora) {
		this.gravadora = gravadora;
	}

	public int getQuantEstoque() {
		return quantEstoque;
	}

	public void setQuantEstoque(int quantEstoque) {
		this.quantEstoque = quantEstoque;
	}

	public float getRsAluguel() {
		return rsAluguel;
	}

	public void setRsAluguel(float rsAluguel) {
		this.rsAluguel = rsAluguel;
	}

	@Override
	public String toString() {
		return "DVD [idDvd=" + idDvd + ", genero=" + genero + ", titulo=" + titulo + ", atores=" + atores
				+ ", produtores=" + produtores + ", gravadora=" + gravadora + ", quantEstoque=" + quantEstoque
				+ ", rsAluguel=" + rsAluguel + "]";
	}
	
	public boolean equals(DVD o){
		if(o.getIdDvd() == getIdDvd()){
			return true;
		}
		
		return false;
	}
	
}
