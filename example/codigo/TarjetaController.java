package com.example.codigo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.codigo.Conexion.conexion;

public class TarjetaController {

    @FXML
    public Button backButt;
    @FXML
    public Button makePaymentButt;
    @FXML
    public Label labelErrorCVV;
    @FXML
    public Label labelErrorTarjeta;
    @FXML
    public TextField numTarjeta;
    @FXML
    public TextField apellidoDuenyo;
    @FXML
    public TextField segundoApellidoDuenyo;
    @FXML
    public TextField mesCaducidad;
    @FXML
    public TextField anyoCaducidad;
    @FXML
    public TextField codigoSeguridad;
    @FXML
    public TextField duenyoTarjeta;
    @FXML
    public Label labelErrorAnyo;
    int contadorFormapago = 31;

    public void onBackButtClick(MouseEvent mouseEvent) throws IOException {
        SpotifyApplication.pagoView();
    }

    public void onMakePaymentButtClick(MouseEvent mouseEvent) throws IOException {
        validarMetodoPago();

    }

    private void validarMetodoPago() throws IOException {
        if(numTarjeta.getText().isEmpty() || apellidoDuenyo.getText().isEmpty() || segundoApellidoDuenyo.getText().isEmpty() || mesCaducidad.getText().isEmpty() || anyoCaducidad.getText().isEmpty() || codigoSeguridad.getText().isEmpty() || duenyoTarjeta.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No has rellenado todos los campos");
            alert.showAndWait();
        }else if(numTarjeta.getText().length() < 16 || numTarjeta.getText().length() > 16 ){
            labelErrorTarjeta.setTextFill(Color.RED);
        }else if(codigoSeguridad.getText().length() < 3 || codigoSeguridad.getText().length() > 3 ){
            labelErrorCVV.setTextFill(Color.RED);
        }else if(anyoCaducidad.getText().length() < 4 || anyoCaducidad.getText().length() > 4 ) {
            labelErrorAnyo.setTextFill(Color.RED);
        }else {
            try{
                contadorFormapago++;
                Connection con = conexion();
                Statement st = con.createStatement();
                st.executeUpdate("INSERT INTO tarjeta_credito " +
                        " (numero_tarjeta, mes_caducidad, anyo_caducidad, codigo_seguridad, forma_pago_id) " +
                        " VALUES ('"+numTarjeta.getText()+"', '"+mesCaducidad.getText()+"', "+anyoCaducidad.getText()+", '"+codigoSeguridad.getText()+"', '"+contadorFormapago+"')");

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            SpotifyApplication.pagoFinalView();
            //SpotifyController.paneEresPremium.setVisible(true);
        }

    }

}