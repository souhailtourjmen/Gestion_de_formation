package com.example.gestion_de_formation.controler;

import com.example.gestion_de_formation.Application;
import com.example.gestion_de_formation.DB.DbConnection;
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
        show("UserDashbord");
    }
    ObservableList dataprofil = FXCollections.observableArrayList();
    Participant particpant = new Participant();

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

    public void setdata() throws SQLException, ClassNotFoundException {
        DbConnection conn = new DbConnection();
        String req="SELECT * FROM `particpant` WHERE email='"+User.account+"'";
        System.out.println(User.account);
        ResultSet rs=conn.select(req);
        while (rs.next()){
            particpant.setId(parseInt(rs.getString("idparticpant")));
            particpant.setNom( rs.getString("`nom"));
            particpant.setPrenom(rs.getString("prenom"));
            particpant.setEmail(rs.getString("email"));
            particpant.setTel(rs.getString("tel"));
        }
        nom.setText(particpant.getNom());
        prenom.setText(particpant.getPrenom());
        tel.setText(particpant.getTel());
    }
    public void update() throws SQLException, ClassNotFoundException {
        String Profil= profil.getSelectionModel().getSelectedItem().toString();
        String req="UPDATE `particpant` SET `nom`='"+nom.getText()+"',`prenom`='"+prenom.getText()+"',`tel`='"+tel.getText()+"',`idprofil'="+cherche(Profil)+" WHERE idparticpant= "+particpant.getId();

    }
    public int cherche(String profil) throws SQLException, ClassNotFoundException {
        String req1="SELECT `Idprofil`, `labelle` FROM `profil` WHERE  Libelle='"+profil+"'";
        int id = 0;
        DbConnection conn = new DbConnection();
        ResultSet rs =conn.select(req1);
        while (rs.next()){
            id=rs.getInt("idDomaine");

        }
        return id;
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
