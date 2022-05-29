package com.example.codigo;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;

public class PremiumController {

    Stage stage;

    @FXML
    public Button indivOptPremium;
    public Button familyOptPremium;
    public Button duoOptPremium;
    public Button studentOptPremium;
    public Button backOptPremium;

    public void onBackOptPremiumClick(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Node source = (Node) mouseEvent.getSource();
        stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void onStudentOptPremiumClick(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        SpotifyApplication.pagoView();
    }

    public void onDuoOptPremiumClick(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        SpotifyApplication.pagoView();
    }

    public void onFamilyOptPremiumClick(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        SpotifyApplication.pagoView();
    }

    public void onIndivOptPremiumClick(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        SpotifyApplication.pagoView();
    }
}
