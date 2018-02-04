package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;

public class Reservas {
	private IntegerProperty id_reserva;
	private String nome_hotel;
	private String nome_quarto;
	private String nome_usuario;
	private String status_reserva;
	private String data_entrada;
	private String data_saida;
	private String lugar_reserva;


	public Reservas(Integer id_reserva, String nome_hotel, String nome_quarto, String nome_usuario, String status_reserva, String data_entrada, String data_saida, String lugar_reserva){
		this.id_reserva = new SimpleIntegerProperty(id_reserva);
		this.setNome_hotel(nome_hotel);
		this.setNome_quarto(nome_quarto);
		this.setNome_usuario(nome_usuario);
		this.setStatus_reserva(status_reserva);
		this.setData_entrada(data_entrada);
		this.setData_saida(data_saida);
		this.setLugar_reserva(lugar_reserva);



	}



	public IntegerProperty getId_reserva() {
		return id_reserva;
	}

	public void setId_reserva(IntegerProperty id_reserva) {
		this.id_reserva = id_reserva;
	}



	public String getNome_hotel() {
		return nome_hotel;
	}

	public void setNome_hotel(String nome_hotel) {
		this.nome_hotel = nome_hotel;
	}

	public String getNome_quarto() {
		return nome_quarto;
	}

	public void setNome_quarto(String nome_quarto) {
		this.nome_quarto = nome_quarto;
	}

	public String getNome_usuario() {
		return nome_usuario;
	}

	public void setNome_usuario(String nome_usuario) {
		this.nome_usuario = nome_usuario;
	}

	public String getStatus_reserva() {
		return status_reserva;
	}

	public void setStatus_reserva(String status_reserva) {
		this.status_reserva = status_reserva;
	}

	public String getData_entrada() {
		return data_entrada;
	}

	public void setData_entrada(String data_entrada) {
		this.data_entrada = data_entrada;
	}

	public String getData_saida() {
		return data_saida;
	}

	public void setData_saida(String data_saida) {
		this.data_saida = data_saida;
	}

	public String getLugar_reserva() {
		return lugar_reserva;
	}

	public void setLugar_reserva(String lugar_reserva) {
		this.lugar_reserva = lugar_reserva;
	}


	public static void listarReservas(Connection connection,
			ObservableList<Reservas> lista){
	try {
		Statement instruccion = connection.createStatement();
		ResultSet resultado = instruccion.executeQuery(
				"select u.nome_usuario, h.nome_hotel, r.id_reserva, r.status_reserva, q.nome_quarto, DATE_FORMAT(STR_TO_DATE(r.data_entrada, '%Y-%m-%d'), '%d/%m/%Y') as data_entrada, DATE_FORMAT(STR_TO_DATE(r.data_saida, '%Y-%m-%d'), '%d/%m/%Y') as data_saida, r.lugar_reserva from tbl_reserva as r inner join tbl_quarto as q on q.id_quarto = r.id_quarto inner join tbl_hotel as h on h.id_hotel = q.id_hotel inner join tbl_usuario as u on u.id_usuario = r.id_usuario;"
		);
		while(resultado.next()){
			lista.add(
					new Reservas(
							resultado.getInt("id_reserva"),
							resultado.getString("nome_usuario"),
							resultado.getString("nome_hotel"),
							resultado.getString("status_reserva"),
							resultado.getString("nome_quarto"),
							resultado.getString("data_entrada"),
							resultado.getString("data_saida"),
							resultado.getString("lugar_reserva"))


					);

		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
}






}
