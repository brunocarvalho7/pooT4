package locacao.aplicacao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends javafx.application.Application{
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {	
		Parent root = FXMLLoader.load(getClass().getResource("../view/FormPrincipal.fxml"));
		
		primaryStage.setTitle("Sistema de loca��o de DVD");
		primaryStage.setScene(new Scene(root,600,500));
		primaryStage.show();
		
	}
	
}
