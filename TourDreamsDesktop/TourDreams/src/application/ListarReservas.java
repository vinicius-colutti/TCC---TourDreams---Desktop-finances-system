package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.ConectarBanco;

import modelo.Reservas;

public class ListarReservas implements Initializable {
	@FXML private TableColumn<Reservas,String> clnUsuario;
	@FXML private TableColumn<Reservas,String> clnHotel;
	@FXML private TableColumn<Reservas,String> clnQuarto;
	@FXML private TableColumn<Reservas,String> clnDataEntrada;
	@FXML private TableColumn<Reservas,String> clnDataSaida;
	@FXML private TableColumn<Reservas,String> clnSituacaoHotel;
	@FXML private TableColumn<Reservas,String> clnPlataforma;

	@FXML private TableColumn<QtdReservas,Number> clnQtdWeb;
	@FXML private TableColumn<QtdReservas,Number> clnQtdApp;
	@FXML private TableColumn<QtdReservas,Number> clnQtdTotal;

	@FXML private TableView<Reservas> tblListaReservas;

	@FXML private TableView<QtdReservas> tblQtdReserva;

	private ObservableList<Reservas> lista;

	private ObservableList<QtdReservas> lista_total;

	private ConectarBanco conexion;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try{


			conexion = new ConectarBanco();
			conexion.establecerConec();

			clnUsuario.setCellValueFactory(new PropertyValueFactory<Reservas,String>("nome_usuario"));
			clnHotel.setCellValueFactory(new PropertyValueFactory<Reservas,String>("nome_hotel"));
			clnQuarto.setCellValueFactory(new PropertyValueFactory<Reservas,String>("nome_quarto"));
			clnDataEntrada.setCellValueFactory(new PropertyValueFactory<Reservas,String>("data_entrada"));
			clnDataSaida.setCellValueFactory(new PropertyValueFactory<Reservas,String>("data_saida"));
			clnSituacaoHotel.setCellValueFactory(new PropertyValueFactory<Reservas,String>("status_reserva"));
			clnPlataforma.setCellValueFactory(new PropertyValueFactory<Reservas,String>("lugar_reserva"));




			lista = FXCollections.observableArrayList();



			Reservas.listarReservas(conexion.getConnection(), lista);



			tblListaReservas.setItems(lista);


			clnQtdWeb.setCellValueFactory(new PropertyValueFactory<QtdReservas,Number>("qtd_web"));
			clnQtdApp.setCellValueFactory(new PropertyValueFactory<QtdReservas,Number>("qtd_app"));
			clnQtdTotal.setCellValueFactory(new PropertyValueFactory<QtdReservas,Number>("qtd_total"));

			lista_total = FXCollections.observableArrayList();

			QtdReservas.listarReservasTotais(conexion.getConnection(), lista_total);

			tblQtdReserva.setItems(lista_total);

			conexion.fechaConec();
			}catch(Exception e){

				System.out.println(e);;
			}

	}


	public void voltar_tela(){

		Main.abrirTela("formulario");
	}

}
