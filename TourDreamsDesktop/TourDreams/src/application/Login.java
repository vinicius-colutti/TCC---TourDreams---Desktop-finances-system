package application;

import java.beans.EventHandler;
import java.security.Provider.Service;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.ConectarBanco;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import com.jfoenix.controls.JFXCheckBox;



public class Login {




	@FXML TextField txtEmail;
	@FXML TextField txtSenha;
	@FXML ProgressBar idProgressBar;
	@FXML StackPane stackPane;




	Connection connection = null;
	PreparedStatement pst = null;
	ResultSet resultSet = null;
	private ConectarBanco conec;
	@FXML JFXCheckBox idCheckBox;


	@FXML public void logar()  {


		String email = txtEmail.getText();
		String senha = txtSenha.getText();



		if(email.equals("") || senha.equals("") || !idCheckBox.isSelected()){
			JFXDialogLayout content = new JFXDialogLayout();

			stackPane.setVisible(true);
			JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);


			content.setHeading(new Text("Ops..."));

			content.setBody(new Text("Por favor, preencha todos os campos."));

			JFXButton button = new JFXButton("Okay");

			button.setOnAction(new javafx.event.EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {


					dialog.close();
					stackPane.setVisible(false);
				}
			});
			content.setActions(button);


			dialog.show();
			System.out.println("ok");


		}else{




		conec = new ConectarBanco();
		conec.establecerConec();

		connection = conec.getConnection();
		String sql = "select * from tbl_funcionarios where email_funcionario = ? and senha = ?;";
		try {

			pst=(PreparedStatement)connection.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, senha);
			resultSet=pst.executeQuery();

			if(resultSet.next()){




				Main.abrirTela("formulario");


			}else{
				JFXDialogLayout content = new JFXDialogLayout();

				stackPane.setVisible(true);
				JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);


				content.setHeading(new Text("Ops..."));

				content.setBody(new Text("E- mail ou senha inválidos. Por favor, tente novamente"));

				JFXButton button = new JFXButton("Okay");

				button.setOnAction(new javafx.event.EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {


						dialog.close();
						stackPane.setVisible(false);
					}
				});
				content.setActions(button);


				dialog.show();


				System.out.println("Erro");




			}

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, e);
	    }
		}
		//if(email.equals("root") && senha.equals("bcd127")){


			//Main.abrirTela("formulario");


		//}else{

			//Alert mensagem = new Alert(AlertType.INFORMATION);
			//mensagem.setTitle("Problema encontrado");
			//mensagem.setContentText("E- mail ou senha inválidos, por favor, tente novamente");
			//mensagem.setHeaderText("Resultado:");
			//mensagem.show();

		//}




	}





}
