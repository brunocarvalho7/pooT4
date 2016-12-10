package locacao.controls;

import locacao.aplicacao.GGravadora;
import locacao.model.Gravadora;

public class ControllerGravadora {
	@FXML
	TextArea id, descricao;

	@FXML
	Button previous, next, novo, editar, salvar, remover;

	@FXML
	public void initialize(){
		System.out.println(GGravadora.getGravadoras());

		if(GGravadora.getGravadoras().isEmpty()){ //OK
			previous.setDisable(true);
			next.setDisable(true);
			editar.setDisable(true);
			remover.setDisable(true);
		}else{
			carregarInformacoes(0);
			previous.setDisable(true);

			if(GGravadora.getGravadoras().size() == 1){
				next.setDisable(true);
			}else{
				next.setDisable(false);
			}
		}
	}

	public void novoRegistro(){ //OK
		descricao.setEditable(true);
		id.setText(String.valueOf(GGravadora.getUltimoID() + 1));

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

	public boolean salvarGravadora(){
		//TODO RESTRI��ES
		int vID = Integer.parseInt(id.getText());
		String vNome = descricao.getText();

		if(vNome.trim().isEmpty()){
			Alert vazio = new Alert(AlertType.WARNING, "Preencha o nome da gravadora", ButtonType.OK);
			vazio.show();
			descricao.setText("");
			descricao.requestFocus();
		}else{

			Gravadora gravadora = new Gravadora(vID, vNome);

			if(GGravadora.getGravadoras().contains(gravadora)){
				GGravadora.getGravadoras().set(GGravadora.getIndex(vID) , gravadora);
				//GAtor.getAtores().set(GAtor.getIndex(vID) , ator);
			}else{
				GGravadora.getGravadoras().add(gravadora);
				GGravadora.setUltimoID(GGravadora.getUltimoID() + 1);
			}
			nextGravadora();

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

	public void previousGravadora(){ //OK
		int indiceAnterior = (GGravadora.getIndex(Integer.parseInt(id.getText())) - 1);
		System.out.println(GGravadora.getGravadoras());
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


	public void nextGravadora(){
		int proximoIndice = (GGravadora.getIndex(Integer.parseInt(id.getText())) + 1);

		if(proximoIndice < GGravadora.getGravadoras().size()){
			carregarInformacoes(proximoIndice);
		}

		if(proximoIndice + 1 == GGravadora.getGravadoras().size()){
		  next.setDisable(true);
		}else{
		  next.setDisable(false);
		}
		previous.setDisable(false);
	}

	public void removerGravadora(){
		if(!id.getText().isEmpty()){
			Alert alert = new Alert(AlertType.CONFIRMATION, "Deseja remover a gravadora "+ descricao.getText()+" ?", ButtonType.YES, ButtonType.NO);

			Optional<ButtonType> result = alert.showAndWait();

			if(result.get() == ButtonType.YES){
				GGravadora.getGravadoras().remove(GGravadora.getIndex(Integer.parseInt(id.getText())));

				Alert a = new Alert(AlertType.INFORMATION, "Registro removido com sucesso!!", ButtonType.CLOSE);
				a.show();

				System.out.println(GGravadora.getGravadoras());
				if(GGravadora.getGravadoras().size() == 0){
					id.setText("");
					descricao.setText("");
					previous.setDisable(true);
					next.setDisable(true);
					remover.setDisable(true);
					editar.setDisable(true);
				}else{
					carregarInformacoes(GGravadora.getGravadoras().size()- 1);
					previous.setDisable(false);
				}
			}
		}
	}

	public void carregarInformacoes(int indice){
		Gravadora a = (Gravadora)GGravadora.getGravadoras().get(indice);

		id.setText(String.valueOf(a.getIdGravadora()));
		descricao.setText(a.getNome());
	}

}
