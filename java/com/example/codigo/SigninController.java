package com.example.codigo;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.sql.*;

import static com.example.codigo.Conexion.conexion;

public class SigninController {
    @FXML
    public Button registrerButt;
    public TextField emailTxtlb;
    public TextField usernameTxtlb;
    public TextField passwordTxtlb;
    public TextField countryTxtlb;
    public TextField cpTxtlb;
    public Label emailMsg;
    public Label usuarioMsg;
    public Button maleButt;
    public Button femaleButt;
    public DatePicker datePicker;


    String gender;

    public void onRegistrerClick(MouseEvent mouseEvent) throws IOException {
        //comprobamos
        try {
            comprobacionSignin();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //insertamos
        Connection con = conexion();
        Statement s = null;
        if(usernameTxtlb.getText().isEmpty() || passwordTxtlb.getText().isEmpty() || emailTxtlb.getText().isEmpty() || gender.isEmpty() || countryTxtlb.getText().isEmpty() || cpTxtlb.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No has rellenado todos los campos");
            alert.showAndWait();
        }else{
            try {
                s = con.createStatement();
                s.executeUpdate("INSERT INTO usuario " +
                        " (username, password, email, genero, fecha_nacimiento, pais, codigo_postal) " +
                        " VALUES ('"+usernameTxtlb.getText()+"', '"+passwordTxtlb.getText()+"', '"+emailTxtlb.getText()+"', '"+gender+"', '"+datePicker.getValue()+"', '"+countryTxtlb.getText()+"', '"+cpTxtlb.getText()+"')");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setTitle("Excellent");
                alert.setContentText("Te has registrado correctamente");
                alert.showAndWait();
                SpotifyApplication.loginView();
            } catch (SQLIntegrityConstraintViolationException r){
                emailMsg.setTextFill(Color.RED);
                usuarioMsg.setTextFill(Color.RED);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    public void comprobacionSignin() throws IOException, SQLException {
        // establecemos conexion
        Connection con = conexion();
        //comprobamos email

        ResultSet email = consultaEmail(con);
        boolean hasEmail = false;
        while(email.next()){
            String smail = email.getString(1);
            emailMsg.setTextFill(Color.rgb(24, 24, 24));
            hasEmail = true;
        }
        if(hasEmail) {
            emailMsg.setTextFill(Color.RED);
        }

        //comprobamos usuario
        ResultSet username = consultaUsername(con);
        boolean hasUser = false;
        while(username.next()){
            String susername = username.getString(1);
            usuarioMsg.setTextFill(Color.rgb(24, 24, 24));
            hasUser = true;
        }
        if(hasUser) {
            usuarioMsg.setTextFill(Color.RED);
        }

    }

    public ResultSet consultaEmail(Connection con) {
        Statement s = null;
        ResultSet rs = null;
        try {
            s = con.createStatement();
            rs = s.executeQuery ("SELECT email FROM usuario WHERE email = '"+ emailTxtlb.getText()+"'" );

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet consultaUsername(Connection con) {
        Statement s = null;
        ResultSet rs = null;
        try {
            s = con.createStatement();
            rs = s.executeQuery ("SELECT username FROM usuario WHERE username = '"+ usernameTxtlb.getText()+"'" );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void onMaleButtClick(MouseEvent mouseEvent) {
        this.gender ="M";
    }

    public void onFemaleButtClick(MouseEvent mouseEvent) {
        this.gender ="F";
    }

}
