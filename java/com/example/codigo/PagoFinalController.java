package com.example.codigo;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class PagoFinalController {

    Stage stage;

    @FXML
    public ImageView imgPagoExitoso;

    public void finPagoExitoso(MouseEvent mouseEvent) throws IOException {
        Node source = (Node) mouseEvent.getSource();
        stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
