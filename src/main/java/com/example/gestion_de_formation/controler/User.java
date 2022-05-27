package com.example.gestion_de_formation.controler;

import com.example.gestion_de_formation.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class User implements Initializable {

    @FXML
    private Label Account;

    @FXML
    private Button Btnaddformation;

    @FXML
    private Label Btnlogout;

    @FXML
    private Button Btnoverview;

    @FXML
    private Label Titre;

    @FXML
    private Button btnsesion;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label label4;

    @FXML
    private Label label5;
    static String account ;
    @FXML
    void Addformation(ActionEvent event) {

    }

    @FXML
    void Addprofil(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Views/Addprofil.fxml"));
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

    @FXML
    void Overview(ActionEvent event) {

    }

    @FXML
    void Session(ActionEvent event) {

    }

    @FXML
    void logout(MouseEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Account.setText(account);
    }
}
