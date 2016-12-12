package locacao.controls;

import java.time.LocalDate;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import locacao.aplicacao.GAluguel;
import locacao.aplicacao.GAtendente;
import locacao.aplicacao.GCliente;
import locacao.aplicacao.GDVD;
import locacao.model.Aluguel;
import locacao.model.Atendente;
import locacao.model.Cliente;
import locacao.model.DVD;

public class ControllerAluguel {

	@FXML
	TextField id, quant, rs;
	
	@FXML
	DatePicker data, devolucao;
	
	@FXML
	Button novo, salvar, remover, confDevolucao, previous, next, btnAdicionarDVD, btnRemoverDVD;

	@FXML
	ComboBox<Cliente> cliente;
	
	@FXML
	ComboBox<Atendente> atendente;
	
	@FXML
	ComboBox<DVD> dvds;
	
	@FXML
	ListView<DVD> lvDVDS;
	
	ObservableList<DVD> tempDVD;  

	@FXML
	public void initialize(){				
		if(GAluguel.getAlugueis().isEmpty()){ 
			previous.setDisable(true);
			next.setDisable(true);
			remover.setDisable(true);
			confDevolucao.setDisable(true);
		}else{
			carregarInformacoes(0);
			previous.setDisable(true);
			
			if(GAluguel.getAlugueis().size() == 1){
				next.setDisable(true);	
			}else{
				next.setDisable(false);
			}
		}		
		
	}
	
	public void atualizaDVDS(){
		ObservableList<DVD> listaDVDS = FXCollections.observableArrayList(GDVD.getDvds());
		dvds.setItems(listaDVDS);
	}
	
	public void atualizaClientes(){
		ObservableList<Cliente> listaClientes = FXCollections.observableArrayList(GCliente.getClientes());
		cliente.setItems(listaClientes);
	}
	
	public void atualizaAtendentes(){
		ObservableList<Atendente> listaAtendentes = FXCollections.observableArrayList(GAtendente.getAtendentes());
		atendente.setItems(listaAtendentes);
	}
	
	public void novoRegistro(){
		tempDVD = FXCollections.observableArrayList();
		
		habilitaEdicao();
		limparInformacoes();
		
		atualizaAtendentes();
		atualizaClientes();
		atualizaDVDS();
		
		id.setText(String.valueOf(GAluguel.getUltimoID() + 1));
		data.setValue(LocalDate.now());
		data.requestFocus();
		rs.setText("0");
		quant.setText("0");
	}
	
	public boolean salvarRegistro(){
		if(isValid()){
			int vID 	   	     = Integer.parseInt(id.getText());
			LocalDate vData 	 = data.getValue();
			LocalDate vDevolucao = devolucao.getValue();
			int vCliente         = cliente.getValue().getIdPessoa();
			int VAtendente       = atendente.getValue().getIdPessoa();
			int vQuant 	   		 = Integer.parseInt(quant.getText());
			Double vRs 	   		 = Double.parseDouble(rs.getText());			
			
			Aluguel aluguel = new Aluguel(vID, vData, vDevolucao, vCliente, VAtendente, tempDVD, vQuant, vRs, "Aberto");
			
			if(GAluguel.getAlugueis().contains(aluguel)){
				GAluguel.getAlugueis().set(GAluguel.getIndex(vID) , aluguel);
			}else{
				GAluguel.getAlugueis().add(aluguel);
				GAluguel.setUltimoID(GAluguel.getUltimoID() + 1);
			}
			
			GCliente.getClientes().get(GCliente.getIndex(vCliente)).getHistorico().add(aluguel);
			nextRegistro();
			
			confDevolucao.setDisable(false);
			
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
			Alert alert = new Alert(AlertType.CONFIRMATION, "Deseja remover o Aluguel "+ id.getText()+" ?", ButtonType.YES, ButtonType.NO);
			
			Optional<ButtonType> result = alert.showAndWait();
			
			if(result.get() == ButtonType.YES){
				GAluguel.getAlugueis().remove(GAluguel.getIndex(Integer.parseInt(id.getText())));
				
				Alert a = new Alert(AlertType.INFORMATION, "Registro removido com sucesso!!", ButtonType.CLOSE);
				a.show();
				
				if(GAluguel.getAlugueis().size() == 0){
					limparInformacoes();	

					desabilitaEdicao();
					
				}else{
					carregarInformacoes(GAluguel.getAlugueis().size()- 1);
					previous.setDisable(false);
				}
			}		
		}
	}
	
	public void devolucaoA(){
		Aluguel aluguel = GAluguel.getAlugueis().get(GAluguel.getIndex(Integer.parseInt(id.getText())));
		
		Alert alert = new Alert(AlertType.CONFIRMATION, "Deseja finalizar o Aluguel "+ id.getText()+" ?", ButtonType.YES, ButtonType.NO);
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if(result.get() == ButtonType.YES){
			if(aluguel.getSituacao() != "Aberto"){
				Alert a = new Alert(AlertType.WARNING, "Aluguel já finalizado!!", ButtonType.CLOSE);
				a.show();
			}else{
				aluguel.setSituacao("Finalizado");
				aluguel.setDataDevolucao(LocalDate.now());
				
				GAluguel.getAlugueis().set(GAluguel.getIndex(aluguel.getIdAluguel()), aluguel);
				
				GAluguel.getAlugueis().set(GAluguel.getIndex(aluguel.getIdAluguel()), aluguel);
				Alert a = new Alert(AlertType.INFORMATION, "Aluguel finalizado com sucesso!!", ButtonType.CLOSE);
				a.show();
			}
		}
	}
	
	public void adicionarDVD(){
		if(dvds.getValue() != null){
			tempDVD.add(dvds.getValue());
			
			lvDVDS.setItems(tempDVD);		
			
			int novaQtd = Integer.parseInt(quant.getText()) + 1;
			Double novoValor = Double.parseDouble(rs.getText()) + dvds.getValue().getRsAluguel();
			
			quant.setText(Integer.toString(novaQtd));
			rs.setText(Double.toString(novoValor));
		}
	}
	
	public void removerDVD(){
		if(lvDVDS.getSelectionModel().getSelectedItem() != null){		
			int novaQtd = Integer.parseInt(quant.getText()) - 1;
			Double novoValor = Double.parseDouble(rs.getText()) - lvDVDS.getSelectionModel().getSelectedItem().getRsAluguel();
			
			quant.setText(Integer.toString(novaQtd));
			rs.setText(Double.toString(novoValor));
			
			tempDVD.remove(lvDVDS.getSelectionModel().getSelectedItem());
			
			lvDVDS.setItems(tempDVD);
		}
	}
	
	public void carregarInformacoes(int indice){
		Aluguel aluguel = GAluguel.getAlugueis().get(indice);
		
		id.setText(Integer.toString(aluguel.getIdAluguel()));
		data.setValue(aluguel.getData());
		devolucao.setValue(aluguel.getDataDevolucao());
		cliente.setValue(GCliente.getCliente(aluguel.getCliente()));
		atendente.setValue(GAtendente.getAtendente(aluguel.getAtendente()));
		quant.setText(Integer.toString(aluguel.getQuantItens()));
		rs.setText(Double.toString(aluguel.getRsTotal()));
		tempDVD     = FXCollections.observableArrayList(aluguel.getItensAluguel());
		
		lvDVDS.setItems(tempDVD);
		confDevolucao.setDisable(false);
	}
	
	public void previousRegistro(){
		int indiceAnterior = (GAluguel.getIndex(Integer.parseInt(id.getText())) - 1);  
		
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
		int proximoIndice = (GAluguel.getIndex(Integer.parseInt(id.getText())) + 1);

		if(proximoIndice < GAluguel.getAlugueis().size()){
			carregarInformacoes(proximoIndice);  
		}

		if(proximoIndice + 1 == GAluguel.getAlugueis().size()){ 
		  next.setDisable(true);
		}else{
		  next.setDisable(false);
		}
		previous.setDisable(false);		
	}
	
	public void habilitaEdicao(){
		data.setEditable(true);
		devolucao.setEditable(true);
		cliente.setDisable(false);
		atendente.setDisable(false);
		dvds.setDisable(false);
		
		btnAdicionarDVD.setDisable(false);
		btnRemoverDVD.setDisable(false);
		
		previous.setDisable(true);
		next.setDisable(true);
		novo.setDisable(true);
		remover.setDisable(true);
		salvar.setDisable(false);
		confDevolucao.setDisable(true);
	}
	
	public void desabilitaEdicao(){
		data.setEditable(false);
		devolucao.setEditable(false);
		cliente.setDisable(true);
		atendente.setDisable(true);
		dvds.setDisable(true);
		
		btnAdicionarDVD.setDisable(true);
		btnRemoverDVD.setDisable(true);
		
		previous.setDisable(false);
		next.setDisable(false);
		novo.setDisable(false);
		remover.setDisable(false);
		salvar.setDisable(true);
		confDevolucao.setDisable(true);
	}
	
	public void limparInformacoes(){
		id.setText("");
		data.setValue(null);
		devolucao.setValue(null);
		cliente.setValue(null);
		atendente.setValue(null);
		dvds.setValue(null);
		rs.setText("");
		quant.setText("");
		lvDVDS.setItems(null);	
	}
	
	public boolean isValid(){
		if((data.getValue() == null) || (data.getValue().isBefore(LocalDate.now()))){
			Alert a = new Alert(AlertType.WARNING, "Ops. Informe uma data válida!!", ButtonType.CLOSE);
			a.show();
			data.requestFocus();			
			return false;			
		}
		
		if((devolucao.getValue() == null) || 
		   (devolucao.getValue().isBefore(LocalDate.now()) || 
		   (devolucao.getValue().isBefore(data.getValue())))){
			Alert a = new Alert(AlertType.WARNING, "Ops. Informe uma data de devolução válida!!", ButtonType.CLOSE);
			a.show();
			devolucao.requestFocus();			
			return false;			
		}
		
		if(cliente.getValue() == null){
			Alert a = new Alert(AlertType.WARNING, "Ops. Selecione um cliente!!", ButtonType.CLOSE);
			a.show();
			cliente.requestFocus();			
			return false;			
		}
		
		if(atendente.getValue() == null){
			Alert a = new Alert(AlertType.WARNING, "Ops. Selecione um atendente!!", ButtonType.CLOSE);
			a.show();
			atendente.requestFocus();			
			return false;			
		}	
		
		if(tempDVD.isEmpty()){
			Alert a = new Alert(AlertType.WARNING, "Ops. Informe os itens locados!!", ButtonType.CLOSE);
			a.show();
			dvds.requestFocus();			
			return false;				
		}

		return true;
	}	
}
