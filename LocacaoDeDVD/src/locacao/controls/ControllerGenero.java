package locacao.controls;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import locacao.aplicacao.GGenero;
import locacao.model.Genero;
import javafx.scene.control.Alert.AlertType;

public class ControllerGenero {
	@FXML
	TextArea id, descricao;
	
	@FXML
	Button previous, next, novo, editar, salvar, remover;
	
	@FXML
	public void initialize(){		
		if(GGenero.getGeneros().isEmpty()){ //OK
			previous.setDisable(true);
			next.setDisable(true);
			editar.setDisable(true);
			remover.setDisable(true);
		}else{
			carregarInformacoes(0);
			previous.setDisable(true);
			
			if(GGenero.getGeneros().size() == 1){
				next.setDisable(true);	
			}else{
				next.setDisable(false);
			}
		}
	}
	
	public void novoRegistro(){ //OK
		descricao.setEditable(true);
		id.setText(String.valueOf(GGenero.getUltimoID() + 1));
		
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
	
	public boolean salvarGenero(){
		//TODO RESTRIÇÕES 
		int vID = Integer.parseInt(id.getText());
		String vNome = descricao.getText(); 
		
		if(vNome.trim().isEmpty()){
			Alert vazio = new Alert(AlertType.WARNING, "Preencha o nome do gênero", ButtonType.OK);
			vazio.show();
			descricao.setText("");
			descricao.requestFocus();
		}else{
		
			Genero genero = new Genero(vID, vNome);
			
			if(GGenero.getGeneros().contains(genero)){
				GGenero.getGeneros().set(GGenero.getIndex(vID) , genero);
				//GGenero.getGeneros().set(GGenero.getIndex(vID) , genero);
			}else{
				GGenero.getGeneros().add(genero);
				GGenero.setUltimoID(GGenero.getUltimoID() + 1);
			}
			nextGenero();
			
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
	
	public void previousGenero(){ //OK
		int indiceAnterior = (GGenero.getIndex(Integer.parseInt(id.getText())) - 1);  
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
	
	
	public void nextGenero(){
		int proximoIndice = (GGenero.getIndex(Integer.parseInt(id.getText())) + 1);

		if(proximoIndice < GGenero.getGeneros().size()){
			carregarInformacoes(proximoIndice);  
		}

		if(proximoIndice + 1 == GGenero.getGeneros().size()){ 
		  next.setDisable(true);
		}else{
		  next.setDisable(false);
		}
		previous.setDisable(false);
	}
	
	public void removerGenero(){
		if(!id.getText().isEmpty()){
			Alert alert = new Alert(AlertType.CONFIRMATION, "Deseja remover o gênero "+ descricao.getText()+" ?", ButtonType.YES, ButtonType.NO);
			
			Optional<ButtonType> result = alert.showAndWait();
			
			if(result.get() == ButtonType.YES){
				GGenero.getGeneros().remove(GGenero.getIndex(Integer.parseInt(id.getText())));
				
				Alert a = new Alert(AlertType.INFORMATION, "Registro removido com sucesso!!", ButtonType.CLOSE);
				a.show();
				
				if(GGenero.getGeneros().size() == 0){
					id.setText("");
					descricao.setText("");
					previous.setDisable(true);
					next.setDisable(true);
					remover.setDisable(true);
					editar.setDisable(true);
				}else{
					carregarInformacoes(GGenero.getGeneros().size()- 1);
					previous.setDisable(false);
				}
			}		
		}
	}
	
	public void carregarInformacoes(int indice){
		Genero a = (Genero)GGenero.getGeneros().get(indice);
		
		id.setText(String.valueOf(a.getIdGenero()));
		descricao.setText(a.getNome());		
	}
	
}
