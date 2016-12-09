package locacao.controls;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import locacao.aplicacao.GAtor;
import locacao.model.Ator;

public class ControllerAtor {

	@FXML
	TextArea id, descricao;
	
	@FXML
	Button previous, next, novo, editar, salvar, remover;
	
	@FXML
	public void initialize(){		
		System.out.println(GAtor.getAtores());
		
		if(GAtor.getAtores().isEmpty()){ //OK
			previous.setDisable(true);
			next.setDisable(true);
			editar.setDisable(true);
			remover.setDisable(true);
		}else{
			carregarInformacoes(0);
			previous.setDisable(true);
			
			if(GAtor.getAtores().size() == 1){
				next.setDisable(true);	
			}else{
				next.setDisable(false);
			}
		}
	}
	
	public void novoRegistro(){ //OK
		descricao.setEditable(true);
		id.setText(String.valueOf(GAtor.getUltimoID() + 1));
		
		descricao.setText("");
		descricao.requestFocus();
		
		previous.setDisable(true);
		next.setDisable(true);
		novo.setDisable(true);
		editar.setDisable(true);
		salvar.setDisable(false);
		remover.setDisable(true);
	}
	
	public void editarRegistro(){
		descricao.setEditable(true);
		descricao.requestFocus();
		
		previous.setDisable(true);
		next.setDisable(true);
		novo.setDisable(true);
		editar.setDisable(true);
		salvar.setDisable(false);
		remover.setDisable(true);		
	}
	
	public boolean salvarAtor(){
		//TODO RESTRIÇÕES 
		int vID = Integer.parseInt(id.getText());
		String vNome = descricao.getText(); 
		
		if(vNome.trim().isEmpty()){
			Alert vazio = new Alert(AlertType.WARNING, "Preencha o nome do ator", ButtonType.OK);
			vazio.show();
			descricao.setText("");
			descricao.requestFocus();
		}else{
		
			Ator ator = new Ator(vID, vNome);
			
			if(GAtor.getAtores().contains(ator)){
				GAtor.getAtores().set(GAtor.getIndex(vID) , ator);
				//GAtor.getAtores().set(GAtor.getIndex(vID) , ator);
			}else{
				GAtor.getAtores().add(ator);
				GAtor.setUltimoID(GAtor.getUltimoID() + 1);
			}
			nextAtor();
			
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
	
	public void previousAtor(){ //OK
		int indiceAnterior = (GAtor.getIndex(Integer.parseInt(id.getText())) - 1);  
		System.out.println(GAtor.getAtores());
			System.out.println("Indice Anterior: "+indiceAnterior );
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
	
	
	public void nextAtor(){
		int proximoIndice = (GAtor.getIndex(Integer.parseInt(id.getText())) + 1);

		if(proximoIndice < GAtor.getAtores().size()){
			carregarInformacoes(proximoIndice);  
		}

		if(proximoIndice + 1 == GAtor.getAtores().size()){ 
		  next.setDisable(true);
		}else{
		  next.setDisable(false);
		}
		previous.setDisable(false);
	}
	
	public void removerAtor(){
		if(!id.getText().isEmpty()){
			Alert alert = new Alert(AlertType.CONFIRMATION, "Deseja remover o ator "+ descricao.getText()+" ?", ButtonType.YES, ButtonType.NO);
			
			Optional<ButtonType> result = alert.showAndWait();
			
			if(result.get() == ButtonType.YES){
				GAtor.getAtores().remove(GAtor.getIndex(Integer.parseInt(id.getText())));
				
				Alert a = new Alert(AlertType.INFORMATION, "Registro removido com sucesso!!", ButtonType.CLOSE);
				a.show();
				
				System.out.println(GAtor.getAtores());
				if(GAtor.getAtores().size() == 0){
					id.setText("");
					descricao.setText("");
					previous.setDisable(true);
					next.setDisable(true);
					remover.setDisable(true);
					editar.setDisable(true);
				}else{
					carregarInformacoes(GAtor.getAtores().size()- 1);
					previous.setDisable(false);
				}
			}		
		}
	}
	
	public void carregarInformacoes(int indice){
		Ator a = (Ator)GAtor.getAtores().get(indice);
		
		id.setText(String.valueOf(a.getIdAtor()));
		descricao.setText(a.getNome());		
	}
	
}
