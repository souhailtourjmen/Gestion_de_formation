package com.example.gestion_de_formation.controler;

import com.example.gestion_de_formation.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {

    @FXML
    private Button btnlogin;

    @FXML
    private TextField mail;

    @FXML
    private PasswordField pwd;

    @FXML
    void Account(MouseEvent event) {

    }

    @FXML
    void Login(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Views/Admin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        try {
            stage.getIcons().add(new Image(this.getClass().getResource("Views/Img/Logo.png").toString()));
        }catch (Exception e) {

        }
        stage.setTitle("Welcome !");
        stage.setScene(scene);
        stage.show();
    }

}
