package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Hotel{
	private IntegerProperty id_hotel;
	private String nome_hotel;
	private String cnpj_hotel;
	private String email;
	private int valor_pagar;


	public Hotel(Integer id_hotel, String nome_hotel, String cnpj_hotel, Integer valor_pagar, String email){
		this.id_hotel = new SimpleIntegerProperty(id_hotel);
		this.setNome_hotel(nome_hotel);
		this.setValor_pagar(valor_pagar);
		this.setCnpj_hotel(cnpj_hotel);
		this.setEmail(email);



	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getCnpj_hotel() {
		return cnpj_hotel;
	}

	public void setCnpj_hotel(String cnpj_hotel) {
		this.cnpj_hotel = cnpj_hotel;
	}

	public String getNome_hotel() {
		return nome_hotel;
	}


	public void setNome_hotel(String nome_hotel) {
		this.nome_hotel = nome_hotel;
	}


	public Integer getIdHotel(){
		return id_hotel.get();
	}

	public void setIdHotel(Integer id_hotel){
		this.id_hotel = new SimpleIntegerProperty(id_hotel);
	}


	public IntegerProperty idHotelProperty(){
		return id_hotel;
	}

	public int getValor_pagar() {
		return valor_pagar;
	}
	public void setValor_pagar(int valor_pagar) {
		this.valor_pagar = valor_pagar;
	}




	public int aprovarRegistro(Connection connection){
		try {
			PreparedStatement instruccion = connection.prepareStatement(
							"update tbl_hotel set situacao_hotel='nada' where id_hotel = ?"
			);
			instruccion.setInt(1, id_hotel.get());
			return instruccion.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static void listarHoteis(Connection connection,
				ObservableList<Hotel> lista){
		try {
			Statement instruccion = connection.createStatement();
			ResultSet resultado = instruccion.executeQuery(
					"select h.nome_hotel, h.email_hotel, h.cnpj, h.id_hotel, truncate(SUM((r.valor_total / 100) * 10), 2) as valor_total FROM tbl_reserva as r inner join tbl_quarto as q on q.id_quarto = r.id_quarto inner join tbl_hotel as h on h.id_hotel = q.id_hotel  where h.situacao_hotel = 'devendo' group by h.nome_hotel;;"
			);
			while(resultado.next()){
				lista.add(
						new Hotel(
								resultado.getInt("id_hotel"),
								resultado.getString("nome_hotel"),
								resultado.getString("cnpj"),
								resultado.getInt("valor_total"),
								resultado.getString("email_hotel"))

						);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}







}