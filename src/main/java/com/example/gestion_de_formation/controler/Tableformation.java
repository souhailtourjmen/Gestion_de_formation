package com.example.gestion_de_formation.controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class Tableformation {

    @FXML
    private TextField Nom;

    @FXML
    private DatePicker date;

    @FXML
    private ComboBox<?> domaine;

    @FXML
    private TextField idformateur;

    @FXML
    private TextField nbrj;

    @FXML
    private TextField nbrp;

    @FXML
    void Addformation(ActionEvent event) {

    }

}
