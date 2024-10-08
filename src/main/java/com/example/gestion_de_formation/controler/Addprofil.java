package com.example.gestion_de_formation.controler;

import com.example.gestion_de_formation.Application;
import com.example.gestion_de_formation.DB.DbConnection;
import com.example.gestion_de_formation.check.Check;
import com.example.gestion_de_formation.modules.Organisation.Participant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;


import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class Addprofil implements Initializable {

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private ComboBox profil;

    @FXML
    private TextField tel;
    
    @FXML
    void Addprofil(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        update();
        setdata();
        User.stage.close();
    }
    ObservableList dataprofil = FXCollections.observableArrayList();
    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setprofil();
            setdata();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void setprofil() throws SQLException, ClassNotFoundException {
        DbConnection conn = new DbConnection();
        String req="SELECT * FROM `profil`";
        ResultSet rs=conn.select(req);
        while (rs.next()){
            dataprofil.add(rs.getString("labelle"));
        }
        profil.setItems(dataprofil);
    }

    public void setdata()  {
        nom.setText(User.particpant.getNom());
        prenom.setText(User.particpant.getPrenom());
        tel.setText(User.particpant.getTel());
    }
    public void update() throws SQLException, ClassNotFoundException {
        String Profil="";
        try {
            Profil= profil.getSelectionModel().getSelectedItem().toString();
        } catch (Exception e) {
            //TODO: handle exception
        }
      
        if(tel.getText().length()<9){
        String req="UPDATE `particpant` SET `nom` = '"+nom.getText()+"', `prenom` = '"+prenom.getText()+"',`tel` = '"+tel.getText()+"' ,`idprofil` = '"+cherche(Profil)+"' WHERE `particpant`.`idparticpant` = "+User.particpant.getId()+" AND `particpant`.`email` = '"+User.account+"';";
        DbConnection conn = new DbConnection();
        conn.insert(req);
        }else{
            Check.showAlerterreur("tel incorrect");
        }
    }
    public int cherche(String profil) throws SQLException, ClassNotFoundException {
        String req1="SELECT `Idprofil`, `labelle` FROM `profil` WHERE  Labelle='"+profil+"'";
        int id = 0;
        DbConnection conn = new DbConnection();
        ResultSet rs =conn.select(req1);
        while (rs.next()){
            id=rs.getInt("idprofil");

        }
        return id;
    }
   }
