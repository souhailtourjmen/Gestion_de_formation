package com.example.gestion_de_formation.controler;

import com.example.gestion_de_formation.Application;
import com.example.gestion_de_formation.DB.DbConnection;
import com.example.gestion_de_formation.check.Check;
import com.example.gestion_de_formation.modules.Organisation.Domaine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.converter.StringConverter;
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

public class AddFormateur implements Initializable {

    @FXML
    private TextField Nom;

    @FXML
    private ComboBox domaine;

    @FXML
    private TextField email;

    @FXML
    private TextField prenom;

    @FXML
    private TextField tel;

    @FXML
    void Addformateur(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
            insertdat();
            show("Admin");

    }

    ObservableList datadomaine = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setdomaine();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setdomaine() throws SQLException, ClassNotFoundException {
        DbConnection conn = new DbConnection();
        String req="SELECT * FROM `domaine`";
        ResultSet rs=conn.select(req);
        while (rs.next()){
            datadomaine.add(rs.getString("Libelle"));
        }
        domaine.setItems(datadomaine);
    }


    public void insertdat() throws SQLException, ClassNotFoundException {
        DbConnection conn = new DbConnection();
        int id = 0;
        Check check =new Check();
        String domain =domaine.getSelectionModel().getSelectedItem().toString();
        String nom=Nom.getText();
        String Prenom=prenom.getText();
        String Email=email.getText();
        String Tel=tel.getText();
       String[] champ = new String[]{nom,Prenom,Email,Tel};
        if(check.check_champ(champ)!=0) {
            String req1="SELECT `idDomaine`, `Libelle` FROM `domaine` WHERE Libelle='"+domain+"'";
            ResultSet rs =conn.select(req1);
            while (rs.next()){
                id=rs.getInt("idDomaine");

            }
            String req = "INSERT INTO `formateur`( `nom`, `prenom`, `domaine`, `email`, `tel`) VALUES ('" + nom + "','" + Prenom + "','"+String.valueOf(id)+"','" + Email + "','" + Tel + "')";

            conn.insert(req);
        }else{
            //alert
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