package locacao.controls;

import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import locacao.aplicacao.GCliente;
import locacao.model.Aluguel;
import locacao.model.Cliente;

public class ControllerCliente {

  @FXML
  TextArea id, descricao, cpf, endereco, telefone, email;
  
  @FXML
  Button previous, next, novo, editar, salvar, remover;
  
  @FXML
  TableView<Aluguel> historico;
  
  @FXML
  TableColumn<Aluguel, String> tcData, tcDevolucao, tcTotal, tcSituacao, tcQuant;
 
  
  @FXML
  public void initialize(){   
    tcData.setCellValueFactory(cellData -> cellData.getValue().dataProperty().asString());
    tcDevolucao.setCellValueFactory(cellData -> cellData.getValue().dataDevolucaoProperty().asString());
    tcTotal.setCellValueFactory(cellData -> cellData.getValue().rsTotalProperty().asString());
    tcSituacao.setCellValueFactory(cellData -> cellData.getValue().situacaoProperty());
    tcQuant.setCellValueFactory(cellData -> cellData.getValue().quantItensProperty().asString());    
    
    
    if(GCliente.getClientes().isEmpty()){ //OK
      previous.setDisable(true);
      next.setDisable(true);
      editar.setDisable(true);
      remover.setDisable(true);
    }else{
      carregarInformacoes(0);
      previous.setDisable(true);
      
      if(GCliente.getClientes().size() == 1){
        next.setDisable(true);  
      }else{
        next.setDisable(false);
      }
    }
  }
  
  public void novoRegistro(){ //OK
    descricao.setEditable(true);
    cpf.setEditable(true);
    endereco.setEditable(true);
    telefone.setEditable(true);
    email.setEditable(true);
        
    id.setText(String.valueOf(GCliente.getUltimoID() + 1));
    
    descricao.setText("");
    cpf.setText("");
    endereco.setText("");
    telefone.setText("");
    email.setText("");
    descricao.requestFocus();
    historico.setItems(null);
    
    previous.setDisable(true);
    next.setDisable(true);
    novo.setDisable(true);
    editar.setDisable(true);
    salvar.setDisable(false);
    remover.setDisable(true);
  }
  
  public void editarRegistro(){
    descricao.setEditable(true);
    cpf.setEditable(true);
    endereco.setEditable(true);
    telefone.setEditable(true);
    email.setEditable(true);

    descricao.requestFocus();
    
    previous.setDisable(true);
    next.setDisable(true);
    novo.setDisable(true);
    editar.setDisable(true);
    salvar.setDisable(false);
    remover.setDisable(true);   
  }
  
  public boolean salvarCliente(){   
    if(isValid()){
	    int vID = Integer.parseInt(id.getText());
	    String vNome  = descricao.getText();
	    String vCPF   = cpf.getText();
	    String vEnd   = endereco.getText();
	    String vTel   = telefone.getText();
	    String vEmail = email.getText();
	    ObservableList<Aluguel> lista = FXCollections.observableArrayList();
      
	    Cliente cliente = new Cliente(vID, vNome, vCPF, vEnd, vTel, vEmail, lista);
      
        if(GCliente.getClientes().contains(cliente)){
          GCliente.getClientes().set(GCliente.getIndex(vID) , cliente);
        }else{
          GCliente.getClientes().add(cliente);
          GCliente.setUltimoID(GCliente.getUltimoID() + 1);
        }
        
        nextCliente();
      
	    novo.setDisable(false);
	    editar.setDisable(false);
	    salvar.setDisable(true);
	    remover.setDisable(false);
 	    descricao.setEditable(false);
	  
	  
	    Alert a = new Alert(AlertType.INFORMATION, "Dados salvos com sucesso!!", ButtonType.CLOSE);
	    a.show();
	  
	    return true;
    }
    return false;
  }
  
  public void previousCliente(){ //OK
	  int indiceAnterior = (GCliente.getIndex(Integer.parseInt(id.getText())) - 1);  
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
  
  
  public void nextCliente(){
    int proximoIndice = (GCliente.getIndex(Integer.parseInt(id.getText())) + 1);

    if(proximoIndice < GCliente.getClientes().size()){
      carregarInformacoes(proximoIndice);  
    }

    if(proximoIndice + 1 == GCliente.getClientes().size()){ 
      next.setDisable(true);
    }else{
      next.setDisable(false);
    }
    previous.setDisable(false);
  }
  
  public void removerCliente(){
    if(!id.getText().isEmpty()){
      Alert alert = new Alert(AlertType.CONFIRMATION, "Deseja remover o cliente "+ descricao.getText()+" ?", ButtonType.YES, ButtonType.NO);
      
      Optional<ButtonType> result = alert.showAndWait();
      
      if(result.get() == ButtonType.YES){
        GCliente.getClientes().remove(GCliente.getIndex(Integer.parseInt(id.getText())));
        
        Alert a = new Alert(AlertType.INFORMATION, "Registro removido com sucesso!!", ButtonType.CLOSE);
        a.show();
        
        if(GCliente.getClientes().size() == 0){
          id.setText("");
          descricao.setText("");
          cpf.setText("");
          endereco.setText("");
          telefone.setText("");
          email.setText("");
          historico.setItems(null);
         
          previous.setDisable(true);
          next.setDisable(true);
          remover.setDisable(true);
          editar.setDisable(true);
        }else{
          carregarInformacoes(GCliente.getClientes().size()- 1);
          previous.setDisable(false);
        }
      }   
    }
  }
  
  public void carregarInformacoes(int indice){
    Cliente a = GCliente.getClientes().get(indice);
    
    id.setText(String.valueOf(a.getIdPessoa()));
    descricao.setText(a.getNome());   
    cpf.setText(a.getCpf());
    endereco.setText(a.getEndereco());
    telefone.setText(a.getTelefone());
    email.setText(a.getEmail());
    
    historico.setItems(GCliente.getClientes().get(indice).getHistorico());
  }
  
  public boolean isValid(){
	  if((descricao.getText() == null) || (descricao.getText().isEmpty())){
	      Alert vazio = new Alert(AlertType.WARNING, "Preencha o nome do cliente", ButtonType.OK);
	      vazio.show();
	      descricao.setText("");
	      descricao.requestFocus();
	      return false;
	    }
	  if((cpf.getText() == null) || (cpf.getText().isEmpty())){
          Alert vazio = new Alert(AlertType.WARNING, "Preencha o CPF do cliente", ButtonType.OK);
          vazio.show();
          cpf.setText("");
          cpf.requestFocus();
          return false;
	  }
		    
	  if((cpf.getText().matches("\\d+") == false) || (cpf.getText() == "") || (cpf.getText().length()<11)){
		  Alert vazio = new Alert(AlertType.WARNING, "Preencha um CPF válido!", ButtonType.OK);
    	  vazio.show();
    	  cpf.setText("");
    	  cpf.requestFocus();
		  return false;
		}
	  
	  if(endereco.getText() == null){
        Alert vazio = new Alert(AlertType.WARNING, "Preencha o Endereço do cliente", ButtonType.OK);
        vazio.show();
        endereco.setText("");
        endereco.requestFocus();
        return false;
	   }
	  
	  if((telefone.getText().matches("\\d+") == false) || (telefone.getText() == "")){
		  Alert vazio = new Alert(AlertType.WARNING, "Preencha um telefone válido!!", ButtonType.OK);
	      vazio.show();
	      telefone.setText("");
	      telefone.requestFocus();
		  return false;
		}
		
	  return true;
  }
  
}
