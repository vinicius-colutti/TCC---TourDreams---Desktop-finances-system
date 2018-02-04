package application;

import javafx.application.Application;

import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

import com.sun.glass.ui.Screen;



public class Main extends Application {
	static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		Main.primaryStage = primaryStage;

		abrirTela("login");
	}

	public static void abrirTela(String arquivo){
		Parent tela;

		try {
			tela = FXMLLoader.load(Main.class.getResource(arquivo + ".fxml"));

			Scene sc = new Scene(tela);


			primaryStage.setScene(sc);
			//primaryStage.setResizable(false);
			primaryStage.show();


		} catch (IOException e) {

			e.printStackTrace();
		}



	}

	public static void main(String[] args) {
		launch(args);
	}
}
