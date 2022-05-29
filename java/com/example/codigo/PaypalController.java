package com.example.codigo;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class PaypalController {

    public Button backButt;
    public Button makePaymentButt;


    public void onBackButtClick(MouseEvent mouseEvent) throws IOException {
        SpotifyApplication.pagoView();
    }

    public void onMakePaymentButtClick(MouseEvent mouseEvent) throws IOException {
        SpotifyApplication.pagoFinalView();
    }
}
