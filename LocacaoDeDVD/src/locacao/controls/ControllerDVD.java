package locacao.controls;

import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import locacao.aplicacao.GAtor;
import locacao.aplicacao.GDVD;
import locacao.aplicacao.GGenero;
import locacao.aplicacao.GGravadora;
import locacao.aplicacao.GProdutor;
import locacao.model.Ator;
import locacao.model.DVD;
import locacao.model.Genero;
import locacao.model.Gravadora;
import locacao.model.Produtor;

public class ControllerDVD {

	@FXML
	TextField id, descricao, quant, rs;
	
	@FXML
	Button novo, salvar, editar, remover, previous, next, btnAdicionarAtor, btnRemoverAtor, btnAdicionarProdutor, btnRemoverProdutor;

	@FXML
	ComboBox<Genero> genero;
	
	@FXML
	ComboBox<Gravadora> gravadora;
	
	@FXML
	ComboBox<Ator> atores;
	
	@FXML
	ListView<Ator> lvAtores;
	
	ObservableList<Ator> tempAtores;  
	
	@FXML
	ComboBox<Produtor> produtores;
	
	@FXML
	ListView<Produtor> lvProdutores;
	
	ObservableList<Produtor> tempProdutores;  
	
	@FXML
	public void initialize(){		
		/*atualizaGeneros();
		atualizaGravadoras();
		atualizaAtores();
		
		gravadora.setDisable(true);
		genero.setDisable(true);*/
				
		if(GDVD.getDvds().isEmpty()){ 
			previous.setDisable(true);
			next.setDisable(true);
			editar.setDisable(true);
			remover.setDisable(true);
		}else{
			carregarInformacoes(0);
			previous.setDisable(true);
			
			if(GDVD.getDvds().size() == 1){
				next.setDisable(true);	
			}else{
				next.setDisable(false);
			}
		}		
		
	}
	
	public void atualizaGeneros(){
		ObservableList<Genero> listaGeneros = FXCollections.observableArrayList(GGenero.getGeneros());
		genero.setItems(listaGeneros);
	}
	
	public void atualizaGravadoras(){
		ObservableList<Gravadora> listaGravadoras = FXCollections.observableArrayList(GGravadora.getGravadoras());
		gravadora.setItems(listaGravadoras);
	}
	
	public void atualizaAtores(){
		ObservableList<Ator> listaAtores = FXCollections.observableArrayList(GAtor.getAtores());
		atores.setItems(listaAtores);
	}
	
	public void atualizaProdutores(){
		ObservableList<Produtor> listaProdutores = FXCollections.observableArrayList(GProdutor.getProdutores());
		produtores.setItems(listaProdutores);
	}
	
	public void novoRegistro(){
		tempAtores = FXCollections.observableArrayList();
		tempProdutores = FXCollections.observableArrayList();
		
		habilitaEdicao();
		limparInformacoes();
		
		atualizaGeneros();
		atualizaGravadoras();
		atualizaAtores();
		atualizaProdutores();
		id.setText(String.valueOf(GDVD.getUltimoID() + 1));
		descricao.requestFocus();
	}
	
	public boolean salvarRegistro(){
		if(isValid()){
			int vID 	   = Integer.parseInt(id.getText());
			String vTitulo = descricao.getText(); 
			int vGenero    = genero.getValue().getIdGenero();
			int vGravadora = gravadora.getValue().getIdGravadora();
			int vQuant 	   = Integer.parseInt(quant.getText());
			Double vRs 	   = Double.parseDouble(rs.getText());
			
			DVD dvd = new DVD(vID, vGenero, vTitulo, tempAtores, tempProdutores, vGravadora, vQuant, vRs);
			
			if(GDVD.getDvds().contains(dvd)){
				GDVD.getDvds().set(GDVD.getIndex(vID) , dvd);
			}else{
				GDVD.getDvds().add(dvd);
				GDVD.setUltimoID(GDVD.getUltimoID() + 1);
			}
			nextRegistro();
			
			desabilitaEdicao();
			
			Alert a = new Alert(AlertType.INFORMATION, "Dados salvos com sucesso!!", ButtonType.CLOSE);
			a.show();
	
			return true;
		}else{
			return false;
		}
	}
	
	public void removerRegistro(){
		if(!id.getText().isEmpty()){
			Alert alert = new Alert(AlertType.CONFIRMATION, "Deseja remover o DVD "+ descricao.getText()+" ?", ButtonType.YES, ButtonType.NO);
			
			Optional<ButtonType> result = alert.showAndWait();
			
			if(result.get() == ButtonType.YES){
				GDVD.getDvds().remove(GDVD.getIndex(Integer.parseInt(id.getText())));
				
				Alert a = new Alert(AlertType.INFORMATION, "Registro removido com sucesso!!", ButtonType.CLOSE);
				a.show();
				
				if(GDVD.getDvds().size() == 0){
					limparInformacoes();	

					desabilitaEdicao();
					
				}else{
					carregarInformacoes(GDVD.getDvds().size()- 1);
					previous.setDisable(false);
				}
			}		
		}
	}
	
	public void editarRegistro(){
		DVD dvd = GDVD.getDvds().get(GDVD.getIndex(Integer.parseInt(id.getText())));
		
		habilitaEdicao();
		tempAtores     = FXCollections.observableArrayList(dvd.getAtores());
		tempProdutores = FXCollections.observableArrayList(dvd.getProdutores());
		descricao.requestFocus();
	}
	
	public void adicionarAtor(){
		if(!tempAtores.contains(atores.getValue())){
			tempAtores.add(atores.getValue());
		
			lvAtores.setItems(tempAtores);
		}
	}
	
	public void removerAtor(){
		tempAtores.remove(lvAtores.getSelectionModel().getSelectedItem());
		
		lvAtores.setItems(tempAtores);
	}
	
	public void adicionarProdutor(){
		if(!tempProdutores.contains(produtores.getValue())){
			tempProdutores.add(produtores.getValue());
			
			lvProdutores.setItems(tempProdutores);			
		}
	}
	
	public void removerProdutor(){
		tempProdutores.remove(lvProdutores.getSelectionModel().getSelectedItem());
		
		lvProdutores.setItems(tempProdutores);
	}
	
	public void carregarInformacoes(int indice){
		DVD dvd = GDVD.getDvds().get(indice);
		
		id.setText(Integer.toString(dvd.getIdDvd()));
		descricao.setText(dvd.getTitulo());
		genero.setValue(GGenero.getGenero(dvd.getGenero()));
		gravadora.setValue(GGravadora.getGravadora(dvd.getGravadora()));
		quant.setText(Integer.toString(dvd.getQuantEstoque()));
		rs.setText(Double.toString(dvd.getRsAluguel()));
		
		tempAtores     = FXCollections.observableArrayList(dvd.getAtores());
		tempProdutores = FXCollections.observableArrayList(dvd.getProdutores());
		
		lvAtores.setItems(tempAtores);
		lvProdutores.setItems(tempProdutores);
	}
	
	public void previousRegistro(){
		int indiceAnterior = (GDVD.getIndex(Integer.parseInt(id.getText())) - 1);  
		
		if(indiceAnterior >= 0){
		  carregarInformacoes(indiceAnterior);
		}
		
		if(indiceAnterior == 0){
		  previous.setDisable(true);
		}else{
		  previous.setDisable(false);
		}
		next.setDisable(false);	
	}
	
	public void nextRegistro(){
		int proximoIndice = (GDVD.getIndex(Integer.parseInt(id.getText())) + 1);

		if(proximoIndice < GDVD.getDvds().size()){
			carregarInformacoes(proximoIndice);  
		}

		if(proximoIndice + 1 == GDVD.getDvds().size()){ 
		  next.setDisable(true);
		}else{
		  next.setDisable(false);
		}
		previous.setDisable(false);		
	}
	
	public void habilitaEdicao(){
		descricao.setEditable(true);
		genero.setDisable(false);
		gravadora.setDisable(false);
		quant.setEditable(true);
		rs.setEditable(true);
		atores.setDisable(false);
		btnAdicionarAtor.setDisable(false);
		btnRemoverAtor.setDisable(false);
		produtores.setDisable(false);
		btnAdicionarProdutor.setDisable(false);
		btnRemoverProdutor.setDisable(false);	
		
		previous.setDisable(true);
		next.setDisable(true);
		novo.setDisable(true);
		editar.setDisable(true);
		salvar.setDisable(false);
		remover.setDisable(true);
	}
	
	public void desabilitaEdicao(){
		descricao.setEditable(false);
		genero.setDisable(true);
		gravadora.setDisable(true);
		quant.setEditable(false);
		rs.setEditable(false);
		atores.setDisable(true);
		btnAdicionarAtor.setDisable(true);
		btnRemoverAtor.setDisable(true);
		produtores.setDisable(true);
		btnAdicionarProdutor.setDisable(true);
		btnRemoverProdutor.setDisable(true);
		
		previous.setDisable(false);
		next.setDisable(false);
		novo.setDisable(false);
		editar.setDisable(false);
		salvar.setDisable(true);
		remover.setDisable(false);
	}
	
	public void limparInformacoes(){
		id.setText("");
		descricao.setText("");
		rs.setText("");
		quant.setText("");
		genero.setValue(null);
		gravadora.setValue(null);
		lvAtores.setItems(null);
		lvProdutores.setItems(null);	
	}
	
	public boolean isValid(){
		if((descricao.getText() == null) || (descricao.getText().isEmpty())){
			Alert a = new Alert(AlertType.WARNING, "Ops. Informe uma descrição válida!!", ButtonType.CLOSE);
			a.show();
			descricao.requestFocus();			
			return false;
		}
		
		if(genero.getValue() == null){
			Alert a = new Alert(AlertType.WARNING, "Ops. Selecione um gênero!!", ButtonType.CLOSE);
			a.show();
			genero.requestFocus();			
			return false;			
		}
		
		if(gravadora.getValue() == null){
			Alert a = new Alert(AlertType.WARNING, "Ops. Selecione uma gravadora!!", ButtonType.CLOSE);
			a.show();
			gravadora.requestFocus();			
			return false;			
		}		
		
		if((quant.getText() == null) || (quant.getText().isEmpty())){
			Alert a = new Alert(AlertType.WARNING, "Ops. Informe uma quantidade!!", ButtonType.CLOSE);
			a.show();
			quant.requestFocus();			
			return false;
		}
		
		try{
			Integer.parseInt(quant.getText());
		}catch(NumberFormatException e){
			Alert a = new Alert(AlertType.WARNING, "Ops. Informe uma quantidade válida!!", ButtonType.CLOSE);
			a.show();
			quant.requestFocus();
			return false;
		}
		
		if((rs.getText() == null) || (rs.getText().isEmpty())){
			Alert a = new Alert(AlertType.WARNING, "Ops. Informe um valor de locação!!", ButtonType.CLOSE);
			a.show();
			rs.requestFocus();			
			return false;
		}	
		
		try{
			Double.parseDouble(rs.getText()); 	
		}catch(NumberFormatException e){
			Alert a = new Alert(AlertType.WARNING, "Ops. Informe um valor válido!!", ButtonType.CLOSE);
			a.show();
			rs.requestFocus();
			return false;
		}

		return true;
	}	
}
