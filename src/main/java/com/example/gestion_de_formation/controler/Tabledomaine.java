package com.example.gestion_de_formation.controler;

import java.sql.SQLException;

import com.example.gestion_de_formation.DB.DbConnection;
import com.example.gestion_de_formation.check.Check;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Tabledomaine {
    @FXML
    private TextField nom;
    Check check = new Check();  

    @FXML
    void Addprofil(ActionEvent event) throws ClassNotFoundException, SQLException {
        if(nom.getText().length()>0){
            DbConnection conn = new DbConnection();
            String req="INSERT INTO `domaine`(`Libelle`) VALUES ('"+nom.getText()+"')";
            int result =conn.insert(req);
            if(result==1){
                check.showAlerterreur("insert success ");
                Admin.stage.close();
            }
        }else{
            check.showAlerterreur("champ est vide ");
        }
        
    }
}


