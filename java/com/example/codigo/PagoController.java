package com.example.codigo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class PagoController {

    @FXML
    public Button paypalButt;
    public Button tarjetaButt;
    public Button backButton;

    public void onTarjetaButtClick(MouseEvent mouseEvent) throws IOException {
        SpotifyApplication.tarjetaView();
    }

    public void onPaypalButtClick(MouseEvent mouseEvent) throws IOException {
        SpotifyApplication.paypalView();
    }

    public void onBackButtClick(MouseEvent mouseEvent) throws IOException {
        SpotifyApplication.premiumView();
    }
}
