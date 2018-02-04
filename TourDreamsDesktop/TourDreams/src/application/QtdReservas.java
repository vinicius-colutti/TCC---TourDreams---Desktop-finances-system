package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import modelo.Reservas;

public class QtdReservas {
	private Integer qtd_web;
	private Integer qtd_app;
	private Integer qtd_total;

	public QtdReservas (Integer qtd_web, Integer qtd_app, Integer qtd_total){
		this.qtd_web = new Integer(qtd_web);
		this.qtd_app = new Integer(qtd_app);
		this.qtd_total = new Integer(qtd_total);



	}

	public Integer getQtd_web() {
		return qtd_web;
	}



	public void setQtd_web(Integer qtd_web) {
		this.qtd_web = qtd_web;
	}



	public Integer getQtd_app() {
		return qtd_app;
	}



	public void setQtd_app(Integer qtd_app) {
		this.qtd_app = qtd_app;
	}



	public Integer getQtd_total() {
		return qtd_total;
	}



	public void setQtd_total(Integer qtd_total) {
		this.qtd_total = qtd_total;
	}



	public static void listarReservasTotais(Connection connection,
			ObservableList<QtdReservas> lista){
	try {
		Statement instruccion = connection.createStatement();
		ResultSet resultado = instruccion.executeQuery(
				"select (SELECT COUNT(*) as contador_web FROM tbl_reserva where lugar_reserva = 'website') as contador_web, (SELECT COUNT(*) as contador_web FROM tbl_reserva where lugar_reserva = 'app') as contador_app, (SELECT COUNT(*) as contador_web FROM tbl_reserva ) as contador_total;"
		);
		while(resultado.next()){

			lista.add(
					new QtdReservas(
							resultado.getInt("contador_web"),
							resultado.getInt("contador_app"),
							resultado.getInt("contador_total"))


					);

		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
}





}
