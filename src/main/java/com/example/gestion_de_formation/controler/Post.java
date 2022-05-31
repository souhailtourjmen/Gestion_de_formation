package com.example.gestion_de_formation.controler;

import java.sql.SQLException;

import com.example.gestion_de_formation.DB.DbConnection;
import com.example.gestion_de_formation.check.Check;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Post {

    @FXML
    private TextField id;

    @FXML
    private TextField nom;
    Check check = new Check();  
    @FXML
    void Addpost(ActionEvent event) throws ClassNotFoundException, SQLException {
            if(check.cheknumber(id.getText())==0 && check.checkchamp(nom.getText())!=-1){
                DbConnection conn = new DbConnection();
                String req="INSERT INTO `admin`(`id`, `post`) VALUES ('"+id.getText()+"','"+nom.getText()+"')";
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
