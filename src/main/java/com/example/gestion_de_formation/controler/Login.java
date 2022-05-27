package com.example.gestion_de_formation.controler;

import com.example.gestion_de_formation.Application;
import com.example.gestion_de_formation.DB.DbConnection;
import com.example.gestion_de_formation.check.Check;
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
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    static Stage stage = new Stage();
    Check check = new Check();

    @FXML
    private Button btnlogin;

    @FXML
    private TextField mail;

    @FXML
    private PasswordField pwd;

    @FXML
    void Account(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Views/Account.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        try {
            stage.getIcons().add(new Image(this.getClass().getResource("Views/Img/Logo.png").toString()));
        }catch (Exception e) {

        }
        stage.setTitle("Welcome !");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void Login(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        DbConnection conn = new DbConnection();

        String role="";
        if((check.checkzone(mail.getText())==0)&(check.checkzone(pwd.getText())==0)){
            String req="SELECT `role` FROM `user` WHERE email='"+mail.getText()+"'and password='"+pwd.getText()+"'";
            ResultSet rs = conn.select(req);
            while (rs.next()){
                role = rs.getString("role");
            }
            if(role.equals("U")){
                FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Views/UserDashbord.fxml"));
                Scene scene = new Scene(fxmlLoader.load());

                try {
                    stage.getIcons().add(new Image(this.getClass().getResource("Views/Img/Logo.png").toString()));
                }catch (Exception e) {

                }
                stage.setTitle("Welcome !");
                stage.setScene(scene);
                stage.show();
            }else if (role.equals("A")||(role.equals("S"))){
                FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Views/Admin.fxml"));
                Scene scene = new Scene(fxmlLoader.load());

                try {
                    stage.getIcons().add(new Image(this.getClass().getResource("Views/Img/Logo.png").toString()));
                }catch (Exception e) {

                }
                stage.setTitle("Welcome !");
                stage.setScene(scene);
                stage.show();
            }
            else{
                //alert
            }
        }
        else{
            //alert
             }

    }

}
