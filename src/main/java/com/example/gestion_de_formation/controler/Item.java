package com.example.gestion_de_formation.controler;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.gestion_de_formation.modules.Organisation.Viewsession;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Item {

    @FXML
    private Button delete;

    @FXML
    private Label lab1;

    @FXML
    private Label lab2;

    @FXML
    private Label lab3;

    @FXML
    private Label lab4;


    @FXML
    void Delete(ActionEvent event) {

    }



    public  void setdata(Viewsession views){
        lab1.setText(views.getNomf());
        lab2.setText(views.getNomd());
        lab3.setText(String.valueOf(views.getDatedeb()));
        lab4.setText(String.valueOf(views.getDatefin()));
        System.out.println(views.getNomf());
    }


}

