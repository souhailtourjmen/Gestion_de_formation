package com.example.gestion_de_formation.controler;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.gestion_de_formation.modules.Organisation.Viewsession;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Item implements Initializable{

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
    static Viewsession views ;

    @FXML
    void Delete(ActionEvent event) {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
            lab1.setText(views.getNomf());
            lab2.setText(views.getNomd());
            lab3.setText(String.valueOf(views.getDatedeb()));
            lab4.setText(String.valueOf(views.getDatefin()));
        
    }

}
