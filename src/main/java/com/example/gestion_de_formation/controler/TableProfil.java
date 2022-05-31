package com.example.gestion_de_formation.controler;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.gestion_de_formation.DB.DbConnection;
import com.example.gestion_de_formation.check.Check;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class TableProfil implements Initializable{

    @FXML
    private ComboBox domaine;

    @FXML
    private TextField nom;
    Check check = new Check(); 
    DbConnection conn = new DbConnection();
    ObservableList datadomaine = FXCollections.observableArrayList();
    @FXML
    void Addprofil(ActionEvent event) throws ClassNotFoundException, SQLException {
        if(check.checkchamp(nom.getText())!=-1){
            String Domaine="";
            try {
                Domaine=domaine.getSelectionModel().getSelectedItem().toString();
                String req1 ="SELECT `idDomaine` FROM `domaine` WHERE `Libelle`='"+Domaine+"'";
                ResultSet rs=conn.select(req1);
                while(rs.next()){
                    Domaine=String.valueOf(rs.getInt(1));
                }
            } catch (Exception e) {
                //TODO: handle exception
            }
            DbConnection conn = new DbConnection();
            String req="INSERT INTO `profil`(`labelle`, `Iddomaine`) VALUES ('"+nom.getText()+"','"+Domaine+"')";
            int result =conn.insert(req);
            if(result==1){
                check.showAlerterreur("insert success ");
                Admin.stage.close();
            }
        }else{
            check.showAlerterreur("champ est vide ");
        }
    }
    public void setdomaine() throws SQLException, ClassNotFoundException {
        
        String req="SELECT * FROM `domaine`";
        ResultSet rs=conn.select(req);
        while (rs.next()){
            datadomaine.add(rs.getString("Libelle"));
        }
        domaine.setItems(datadomaine);
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            setdomaine() ;
        } catch (ClassNotFoundException | SQLException e) {
           
            e.printStackTrace();
        }
    }
}
