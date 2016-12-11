
package locacao.model;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Aluguel {

	private final IntegerProperty idAluguel;
	private final ObjectProperty<LocalDate> data;
	private final ObjectProperty<LocalDate> dataDevolucao;
	private final IntegerProperty cliente;
	private final IntegerProperty atendente;
	private final ListProperty<DVD> itensAluguel;
	private final IntegerProperty quantItens;
	private final DoubleProperty rsTotal;
	private final StringProperty situacao;
	
	public Aluguel(int idAluguel, LocalDate data, LocalDate dataDevolucao, int cliente, int atendente, ObservableList<DVD> itensAluguel,
			int quantItens, Double rsTotal, String situacao) {
		this.idAluguel      = new SimpleIntegerProperty(idAluguel);
		this.data 			= new SimpleObjectProperty<LocalDate>(data);
		this.dataDevolucao  = new SimpleObjectProperty<LocalDate>(dataDevolucao);;
		this.cliente 		= new SimpleIntegerProperty(cliente);;
		this.atendente 		= new SimpleIntegerProperty(atendente);;
		this.itensAluguel 	= new SimpleListProperty<>(itensAluguel);
		this.quantItens 	= new SimpleIntegerProperty(quantItens);;
		this.rsTotal 		= new SimpleDoubleProperty(rsTotal);
		this.situacao 		= new SimpleStringProperty(situacao);
	}
	
	@Override
	public String toString() {
		return "Aluguel [idAluguel=" + idAluguel + ", data=" + data + ", dataDevolucao=" + dataDevolucao + ", cliente="
				+ cliente + ", atendente=" + atendente + ", itensAluguel=" + itensAluguel + ", quantItens=" + quantItens
				+ ", rsTotal=" + rsTotal + ", situacao=" + situacao + "]";
	}

	public int getIdAluguel() {
		return idAluguel.get();
	}
	
	public void setIdAluguel(int idAluguel) {
		this.idAluguel.set(idAluguel);
	}
	
	public IntegerProperty idAluguelProperty(){
		return idAluguel;
	}
	
	public LocalDate getData() {
		return data.get();
	}
	
	public void setData(LocalDate data) {
		this.data.set(data);
	}
	
	public ObjectProperty<LocalDate> dataProperty(){
		return data;
	}
	
	public LocalDate getDataDevolucao() {
		return dataDevolucao.get();
	}
	
	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao.set(dataDevolucao);
	}
	
	public ObjectProperty<LocalDate> dataDevolucaoProperty(){
		return dataDevolucao;
	}
	
	public int getCliente() {
		return cliente.get();
	}
	
	public void setCliente(int cliente) {
		this.cliente.set(cliente);
	}
	
	public IntegerProperty clienteProperty(){
		return cliente;
	}
	
	public int getAtendente() {
		return atendente.get();
	}
	
	public void setAtendente(int atendente) {
		this.atendente.set(atendente);
	}
	
	public IntegerProperty atendenteProperty(){
		return atendente;
	}
	
	public ObservableList<DVD> getItensAluguel() {
		return itensAluguel.get();
	}
	
	public void setItensAluguel(ObservableList<DVD> itensAluguel) {
		this.itensAluguel.set(itensAluguel);
	}
	
	public ObservableList<DVD> itensAluguelProperty(){
		return itensAluguel;
	}
	
	public int getQuantItens() {
		return quantItens.get();
	}
	
	public void setQuantItens(int quantItens) {
		this.quantItens.set(quantItens);
	}
	
	public IntegerProperty quantItensProperty(){
		return quantItens;
	}
	
	public Double getRsTotal() {
		return rsTotal.get();
	}
	
	public void setRsTotal(Double rsTotal) {
		this.rsTotal.set(rsTotal);
	}
	
	public DoubleProperty rsTotalProperty(){
		return rsTotal;
	}
	
	public String getSituacao() {
		return situacao.get();
	}
	
	public void setSituacao(String situacao) {
		this.situacao.set(situacao);
	}
	
	public StringProperty situacaoProperty(){
		return situacao;
	}
}
