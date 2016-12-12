package locacao.controls;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import locacao.aplicacao.GAtendente;
import locacao.model.Atendente;

public class ControllerAtendente {
	 @FXML
	  TextArea id, descricao, cpf, endereco, telefone, email, salario;
	  
	  @FXML
	  Button previous, next, novo, editar, salvar, remover;
	  
	  @FXML
	  public void initialize(){   
	    System.out.println(GAtendente.getAtendentes());
	    
	    if(GAtendente.getAtendentes().isEmpty()){ //OK
	      previous.setDisable(true);
	      next.setDisable(true);
	      editar.setDisable(true);
	      remover.setDisable(true);
	    }else{
	      carregarInformacoes(0);
	      previous.setDisable(true);
	      
	      if(GAtendente.getAtendentes().size() == 1){
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
	    salario.setEditable(true);
	        
	    id.setText(String.valueOf(GAtendente.getUltimoID() + 1));
	    
	    descricao.setText("");
	    cpf.setText("");
	    endereco.setText("");
	    telefone.setText("");
	    email.setText("");
	    descricao.requestFocus();
	    salario.setText("");
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
	    salario.setEditable(true);
	    
	    descricao.requestFocus();
	    
	    previous.setDisable(true);
	    next.setDisable(true);
	    novo.setDisable(true);
	    editar.setDisable(true);
	    salvar.setDisable(false);
	    remover.setDisable(true);   
	  }
	  
	  public boolean salvarAtendente(){
	    //TODO RESTRIÇÕES 
	    int vID = Integer.parseInt(id.getText());
	    String vNome  = descricao.getText();
	    String vCPF   = cpf.getText();
	    String vEnd   = endereco.getText();
	    String vTel   = telefone.getText();
	    String vEmail = email.getText();
	    String vSalario = salario.getText();
	    
	    if(vNome.trim().isEmpty()){
	      Alert vazio = new Alert(AlertType.WARNING, "Preencha o nome do atendente", ButtonType.OK);
	      vazio.show();
	      descricao.setText("");
	      descricao.requestFocus();
	    }else if(vCPF.trim().isEmpty()){
	          Alert vazio = new Alert(AlertType.WARNING, "Preencha o CPF do atendente", ButtonType.OK);
	          vazio.show();
	          cpf.setText("");
	          cpf.requestFocus();
	     }else if(vEnd.trim().isEmpty()){
	        Alert vazio = new Alert(AlertType.WARNING, "Preencha o Endereço do atendente", ButtonType.OK);
	        vazio.show();
	        endereco.setText("");
	        endereco.requestFocus();
	     }else if(vSalario.trim().isEmpty()){
	    	 Alert vazio = new Alert(AlertType.WARNING, "Preencha o salário do atendente", ButtonType.OK);
	    	 vazio.show();
	    	 salario.setText("");
	    	 salario.requestFocus();
	     }
	    else{
	      Atendente atendente = new Atendente(vID, vNome, vCPF, vEnd, vTel, vEmail, (float) Double.parseDouble(vSalario));
	      
	      if(GAtendente.getAtendentes().contains(atendente)){
	        GAtendente.getAtendentes().set(GAtendente.getIndex(vID) , atendente);
	      }else{
	        GAtendente.getAtendentes().add(atendente);
	        GAtendente.setUltimoID(GAtendente.getUltimoID() + 1);
	      }
	      nextAtendente();
	      
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
	  
	  public void previousAtendente(){ //OK
		  int indiceAnterior = (GAtendente.getIndex(Integer.parseInt(id.getText())) - 1);  
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
	  
	  
	  public void nextAtendente(){
	    int proximoIndice = (GAtendente.getIndex(Integer.parseInt(id.getText())) + 1);

	    if(proximoIndice < GAtendente.getAtendentes().size()){
	      carregarInformacoes(proximoIndice);  
	    }

	    if(proximoIndice + 1 == GAtendente.getAtendentes().size()){ 
	      next.setDisable(true);
	    }else{
	      next.setDisable(false);
	    }
	    previous.setDisable(false);
	  }
	  
	  public void removerAtendente(){
	    if(!id.getText().isEmpty()){
	      Alert alert = new Alert(AlertType.CONFIRMATION, "Deseja remover o atendente "+ descricao.getText()+" ?", ButtonType.YES, ButtonType.NO);
	      
	      Optional<ButtonType> result = alert.showAndWait();
	      
	      if(result.get() == ButtonType.YES){
	        GAtendente.getAtendentes().remove(GAtendente.getIndex(Integer.parseInt(id.getText())));
	        
	        Alert a = new Alert(AlertType.INFORMATION, "Registro removido com sucesso!!", ButtonType.CLOSE);
	        a.show();
	        
	        if(GAtendente.getAtendentes().size() == 0){
	          id.setText("");
	          descricao.setText("");
	          cpf.setText("");
	          endereco.setText("");
	          telefone.setText("");
	          email.setText("");
	          salario.setText("");
	          
	          previous.setDisable(true);
	          next.setDisable(true);
	          remover.setDisable(true);
	          editar.setDisable(true);
	        }else{
	          carregarInformacoes(GAtendente.getAtendentes().size()- 1);
	          previous.setDisable(false);
	        }
	      }   
	    }
	  }
	  
	  public void carregarInformacoes(int indice){
	    Atendente a = GAtendente.getAtendentes().get(indice);
	    
	    id.setText(String.valueOf(a.getIdPessoa()));
	    descricao.setText(a.getNome());   
	    cpf.setText(a.getCpf());
	    endereco.setText(a.getEndereco());
	    telefone.setText(a.getTelefone());
	    email.setText(a.getEmail());
	    salario.setText(String.valueOf(a.getSalario()));
	  }
}