package com.example.gestion_de_formation.controler;
import java.io.IOException;
import java.sql.SQLException;

import com.example.gestion_de_formation.Application;

import com.example.gestion_de_formation.DB.DbConnection;
import com.example.gestion_de_formation.check.Check;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class Account {

    @FXML
    private CheckBox Idadmin;

    @FXML
    private Button btnRegister;

    @FXML
    private PasswordField cpwd;

    @FXML
    private TextField matricule;

    @FXML
    private Label login;

    @FXML
    private TextField mail;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private PasswordField pwd;

    @FXML
    private TextField tel;

    Check check = new Check();

    @FXML
    void Login(MouseEvent event) throws IOException {
        show("Login");
    }




    @FXML
    void Register(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        DbConnection conn = new DbConnection();
        String[]champ=new String[]{nom.getText(),prenom.getText(),mail.getText(),tel.getText(),pwd.getText(),cpwd.getText()};
        if(check.check_champ(champ)!=0){
            if(champ[4].equals(champ[5])){

                if(!Idadmin.isSelected()) {
                    champ = new String[]{nom.getText(), prenom.getText(), mail.getText(), tel.getText(), pwd.getText()};

                    try {
                        String reqcherche="SELECT * FROM `user` WHERE email='"+champ[2]+"'";
                        if(conn.checkaccount(reqcherche) ==0) {
                            String req = "INSERT INTO `particpant`( `nom`, `prenom`, `email`, `tel`, `pwd` ) VALUES ('" + champ[0] + "','" + champ[1] + "','" + champ[2] + "','" + champ[3] + "','" + champ[4] + "')";
                            conn.insert(req);
                            String req1="INSERT INTO `user`(`email`, `password`, `role`) VALUES ('"+champ[2]+"','"+champ[4]+"','U')";
                            conn.insert(req1);

                        }else{
                            Check.showAlerterreur("Compte exist");
                          
                        }
                    }catch(Exception e){

                    }
                    show("UserDashbord");

                }else{
                    champ = new String[]{nom.getText(), prenom.getText(), mail.getText(), tel.getText(), pwd.getText(),matricule.getText()};
                    try {
                        String reqcherche="SELECT * FROM `admin` WHERE id="+champ[5];
                        if((check.checkchamp(champ[5])==0)&(conn.checkaccount(reqcherche) ==1)) {
                            String req = "INSERT INTO `administration`( `nom`, `prenom`, `email`, `tel`, `pwd`, `idadmin`) VALUES ('"+champ[0]+"','"+champ[1]+"','"+champ[2]+"','"+champ[3]+"','"+champ[4]+"',"+champ[5]+")";
                            conn.insert(req);
                            String req1="INSERT INTO `user`(`email`, `password`, `role`) VALUES ('"+champ[2]+"','"+champ[4]+"','S')";
                            conn.insert(req1);

                        }else{
                            Check.showAlerterreur("compte !");
                           
                        }
                    }catch(Exception e){

                    }
                    show("Admin");
                }

            }else{
                Check.showAlerterreur("password !");
            }


        }
        else{

        }
    }

    public void show(String page) throws IOException {
        String path="Views/"+page+".fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(path));
        Scene scene = new Scene(fxmlLoader.load());

        try {
            Login.stage.getIcons().add(new Image(this.getClass().getResource("Views/Img/Logo.png").toString()));
        }catch (Exception e) {

        }
        Login.stage.setTitle("Welcome !");
        Login.stage.setScene(scene);
        Login.stage.show();
    }

}


