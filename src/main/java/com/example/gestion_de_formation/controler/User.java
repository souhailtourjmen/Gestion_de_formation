package com.example.gestion_de_formation.controler;

import com.example.gestion_de_formation.Application;
import com.example.gestion_de_formation.DB.DbConnection;
import com.example.gestion_de_formation.modules.Organisation.Participant;
import com.example.gestion_de_formation.modules.Organisation.Viewsession;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import static java.lang.Integer.parseInt;

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
    private GridPane grid;

    @FXML
    private Label label5;
    static String account ;
    static Participant particpant = new Participant();
    ObservableList<Viewsession> data = FXCollections.observableArrayList();
    @FXML
    void Addformation(ActionEvent event) throws IOException {
        show("userformation");

    }

    @FXML
    void Addprofil(ActionEvent event) throws IOException {
        show("Addprofil");
    }

    @FXML
    void Overview(ActionEvent event) throws ClassNotFoundException, SQLException {
            setdata();
            setGrid();
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
        try {
            setuser();
        } catch (ClassNotFoundException e) {
        
            e.printStackTrace();
        } catch (SQLException e) {
            
            e.printStackTrace();
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
    public   void setuser() throws SQLException, ClassNotFoundException {
        DbConnection conn = new DbConnection();
        String req="SELECT `idparticpant`, `nom`, `prenom`, `email`, `tel`, `pwd`, `idprofil` FROM `particpant` WHERE email='"+User.account+"'";
        ResultSet rs=conn.select(req);
        while (rs.next()){
            particpant.setId(parseInt(rs.getString(1)));
            particpant.setNom( rs.getString(2));
            particpant.setPrenom(rs.getString(3));
            particpant.setEmail(rs.getString(4));
            particpant.setTel(rs.getString(5));
        }
        
    }
    
    public void setdata() throws ClassNotFoundException, SQLException{
        DbConnection conn = new DbConnection();
            String req="SELECT   `F`.`intitule` AS `Nom de formation`, `D`.`Libelle` AS `Nom de domaine`,`SE`.`debut` AS `Date de debut`, `SE`.`fin` AS `Date de fin`FROM `section` AS `S` LEFT JOIN `session` AS `SE` ON `S`.`idsession` = `SE`.`Idsession`  LEFT JOIN `formation` AS `F` ON `SE`.`idformation` = `F`.`id` LEFT JOIN `domaine` AS `D` ON `F`.`domaine` = `D`.`idDomaine` WHERE `S`.`idparticipant`='"+User.particpant.getId()+"';";
            ResultSet rs=conn.select(req);
            while (rs.next()){
                Viewsession views =new Viewsession();
                views.setNomf(rs.getString(1));
                views.setNomd(rs.getString(2));
                views.setDatedeb(rs.getDate(3));
                views.setDatefin(rs.getDate(4));
                data.add(views);
            }
    }
    public void setGrid() {
        int row = 0;
        for(Viewsession i :data) {
            Item.views=i;
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Item.fxml"));
            AnchorPane anchorPane;
            try {
                anchorPane = fxmlLoader.load();
                grid.add(anchorPane, 0, row); //(child,column,row)
        
            } catch (IOException e) {
                
                e.printStackTrace();
            } 
            //set grid width
            grid.setMinWidth(Region.USE_COMPUTED_SIZE);
            grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
            grid.setMaxWidth(Region.USE_PREF_SIZE);
    
            //set grid height
            grid.setMinHeight(Region.USE_COMPUTED_SIZE);
            grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
            grid.setMaxHeight(Region.USE_PREF_SIZE);
            row++;
        }
    }


}
