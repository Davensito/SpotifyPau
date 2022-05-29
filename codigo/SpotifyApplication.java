package com.example.codigo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SpotifyApplication extends Application {
    static Stage stageController;
    @Override
    public void start(Stage stage) throws IOException {
        stageController = stage;
        loginView();
    }
    public static void loginView() throws IOException {
        stageController.close();
        FXMLLoader fxmlLoader = new FXMLLoader(SpotifyApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 345, 479);
        stageController.setTitle("Log In");
        stageController.setScene(scene);
        stageController.show();
    }

    public static void signinView() throws IOException {
        stageController.close();
        FXMLLoader fxmlLoader = new FXMLLoader(SpotifyApplication.class.getResource("signin-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),560 ,409);
        stageController.setTitle("Sign In");
        stageController.setScene(scene);
        stageController.show();
    }

    public static void mainView() throws IOException {
        stageController.close();
        FXMLLoader fxmlLoader = new FXMLLoader(SpotifyApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1310, 700);
        stageController.setTitle("Spotify");
        stageController.setScene(scene);
        stageController.show();
    }

    public static void premiumView() throws IOException {
        stageController.close();
        FXMLLoader fxmlLoader = new FXMLLoader(SpotifyApplication.class.getResource("premium-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 978, 490);
        stageController.setTitle("Premium");
        stageController.setScene(scene);
        stageController.show();
    }

    public static void pagoView() throws IOException {
        stageController.close();
        FXMLLoader fxmlLoader = new FXMLLoader(SpotifyApplication.class.getResource("pago-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 290);
        stageController.setTitle("Selección Pago");
        stageController.setScene(scene);
        stageController.show();
    }

    public static void tarjetaView() throws IOException {
        stageController.close();
        FXMLLoader fxmlLoader = new FXMLLoader(SpotifyApplication.class.getResource("tarjeta-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 687, 504);
        stageController.setTitle("Pago con tarjeta");
        stageController.setScene(scene);
        stageController.show();
    }

    public static void paypalView() throws IOException {
        stageController.close();
        FXMLLoader fxmlLoader = new FXMLLoader(SpotifyApplication.class.getResource("paypal-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 343, 384);
        stageController.setTitle("Pago con paypal");
        stageController.setScene(scene);
        stageController.show();
    }

    public static void pagoFinalView() throws IOException {
        stageController.close();
        FXMLLoader fxmlLoader = new FXMLLoader(SpotifyApplication.class.getResource("pagoFinal-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stageController.setTitle("Congratulations!");
        stageController.setScene(scene);
        stageController.show();
    }


    public static void main(String[] args) {
        launch();
    }
}

    /*public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SpotifyApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1310, 890);
        stage.setTitle("Spotify");
        stage.setScene(scene);
        stage.show();
    }*/
