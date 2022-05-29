package com.example.codigo;

import com.example.codigo.clases.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.codigo.Conexion.conexion;

public class LoginController {

    @FXML
    public Button loginButt;
    public Button signingButt;
    public TextField usernameTxtfl;
    @FXML
    private PasswordField passwordTxtfl;
    public Label errorMsg;
    Stage stage = new Stage();


    public void onLoginClick(MouseEvent mouseEvent) throws IOException, SQLException {
        comprobacionUsuario();
    }

    public void onSigninClick(MouseEvent mouseEvent) throws IOException {
        SpotifyApplication.signinView();
    }

    public void comprobacionUsuario() throws IOException, SQLException {
        Connection con = conexion();
        ResultSet id = consultaUsuario(con);
        int od =0;
        boolean foundAnyUser = false;
        while (id.next()) od = id.getInt(1);
        Statement st = con.createStatement();
        ResultSet query = st.executeQuery("select id,username,password from usuario where id = "+od+";");
        while (query.next()){
            int id3 = query.getInt(1);
            String username = query.getString(2);
            String password = query.getString(3);

            Usuario user = new Usuario(id3,username,password);
            foundAnyUser = true;
            SpotifyController.user = user;
        }
           if(foundAnyUser){
                   FXMLLoader fxmlLoader = new FXMLLoader(SpotifyApplication.class.getResource("main-view.fxml"));
                   Parent root = fxmlLoader.load();
                   SpotifyController controller = fxmlLoader.getController();
                   Scene scene = new Scene(root);
                   stage.setTitle("Spotify");
                   stage.setScene(scene);
                   controller.nombreUsuario(usernameTxtfl.getText(), stage, this);
                   stage.show();
           }else{
               errorMsg.setTextFill(Color.RED);
           }
    }

    public ResultSet consultaUsuario(Connection con) {
        Statement s = null;
        ResultSet rs = null;
        try {
            s = con.createStatement();
            rs = s.executeQuery ("SELECT id FROM usuario WHERE username= '" + usernameTxtfl.getText()+ "' AND password = '" + passwordTxtfl.getText()+"'");
           // System.out.println("SELECT id FROM usuario WHERE username= '" + usernameTxtfl.getText()+ "' AND password = '" + passwordTxtfl.getText()+"'");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
