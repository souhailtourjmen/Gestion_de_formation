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

import static com.example.gestion_de_formation.controler.Login.stage;

public class Admin  implements Initializable{

    @FXML
    private Label Account;

    @FXML
    private Button Btn4;

    @FXML
    private Button Btnaddformateur;

    @FXML
    private Button Btnaddformation;

    @FXML
    private Label Btnlogout;

    @FXML
    private Button Btnmenu2;

    @FXML
    private Button Btnoverview;

    @FXML
    private Label Titre;

    @FXML
    private Button btnMenu3;

    @FXML
    private Button btnmenu1;

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

    @FXML
    void Addformateur(ActionEvent event) throws IOException {
        show("Addformateur","Addformateur");

    }

    @FXML
    void Addformation(ActionEvent event) throws IOException {
        show("Addforamtion","Addformation");
    }

    @FXML
    void Demande(ActionEvent event) throws IOException {

    }

    @FXML
    void Formateur(ActionEvent event) {

    }

    @FXML
    void Formation(ActionEvent event) {

    }

    @FXML
    void Overview(ActionEvent event) {

    }

    @FXML
    void Particpants(ActionEvent event) {

    }

    @FXML
    void Session(ActionEvent event) throws IOException {
        show("AddSession","AddSession");
    }

    @FXML
    void logout(MouseEvent event) {

    }
    public void show(String page,String title) throws IOException {
        String path="Views/"+page+".fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(path));
        Scene scene = new Scene(fxmlLoader.load());

        try {
            Login.stage.getIcons().add(new Image(this.getClass().getResource("Views/Img/Logo.png").toString()));
        }catch (Exception e) {

        }
        Login.stage.setTitle(title);
        Login.stage.setScene(scene);
        Login.stage.show();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        
    }
    


}
