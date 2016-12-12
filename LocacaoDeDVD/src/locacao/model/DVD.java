package locacao.model;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DVD {
	private int idDvd;
	private int genero;
	private String titulo;
	private ObservableList<Ator> atores = FXCollections.observableArrayList();
	private List<Produtor> produtores = new ArrayList<>();
	private int gravadora;
	private int quantEstoque;
	private Double rsAluguel;
	
	public DVD(int idDvd, int genero, String titulo, ObservableList<Ator> atores, List<Produtor> produtores, int gravadora,
			int quantEstoque, Double rsAluguel) {
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

	public ObservableList<Ator> getAtores() {
		return atores;
	}

	public void setAtores(ObservableList<Ator> atores) {
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

	public Double getRsAluguel() {
		return rsAluguel;
	}

	public void setRsAluguel(Double rsAluguel) {
		this.rsAluguel = rsAluguel;
	}

	@Override
	public String toString() {
		return titulo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idDvd;
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
		DVD other = (DVD) obj;
		if (idDvd != other.idDvd)
			return false;
		return true;
	}
	
	
	
}
