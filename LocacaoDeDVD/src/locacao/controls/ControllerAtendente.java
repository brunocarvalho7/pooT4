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
	    if(isValid()){
	    	int vID = Integer.parseInt(id.getText());
	    
		    String vNome  = descricao.getText();
		    String vCPF   = cpf.getText();
		    String vEnd   = endereco.getText();
		    String vTel   = telefone.getText();
		    String vEmail = email.getText();
		    String vSalario = salario.getText();
	    
		    Atendente atendente = new Atendente(vID, vNome, vCPF, vEnd, vTel, vEmail, Float.parseFloat(vSalario));
	      
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
	    }else{
	    	return false;
	    }
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

	  public boolean isValid(){
		  if((descricao.getText() == null) || (descricao.getText().isEmpty())){
		      Alert vazio = new Alert(AlertType.WARNING, "Preencha o nome do atendente", ButtonType.OK);
		      vazio.show();
		      descricao.setText("");
		      descricao.requestFocus();
		      return false;
		    }
		  if((cpf.getText() == null) || (cpf.getText().isEmpty())){
	          Alert vazio = new Alert(AlertType.WARNING, "Preencha o CPF do atendente", ButtonType.OK);
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
	        Alert vazio = new Alert(AlertType.WARNING, "Preencha o Endereço do atendente", ButtonType.OK);
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
			
		  
		  if((salario.getText() == null) || (salario.getText().isEmpty())){
	    	 Alert vazio = new Alert(AlertType.WARNING, "Preencha o salário do atendente", ButtonType.OK);
	    	 vazio.show();
	    	 salario.setText("");
	    	 salario.requestFocus();
	    	 return false;
	     }
		  
		  try{
				Double.parseDouble(salario.getText());
			}catch(NumberFormatException e){
				Alert a = new Alert(AlertType.WARNING, "Ops. Informe um salário válido!!", ButtonType.CLOSE);
				a.show();
				salario.requestFocus();
				return false;
			}
		  
		  return true;
	  }

}