package com.example.gestion_de_formation.controler;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.gestion_de_formation.Application;
import com.example.gestion_de_formation.DB.DbConnection;
import com.example.gestion_de_formation.modules.Organisation.Viewadmin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

public class TableParticpant implements Initializable {

    @FXML
    private TableColumn<Viewadmin, String> Domaine;

    @FXML
    private TableColumn<Viewadmin, String> Formation;

    @FXML
    private TableColumn<Viewadmin, String> Nom;

    @FXML
    private TableColumn<Viewadmin, String> Prenom;

    @FXML
    private TableColumn<Viewadmin, String> Profil;
    @FXML
    private TableView<Viewadmin> table;

    
    @FXML
    void Retour(ActionEvent event) throws IOException {
       show("Admin","Home");
    }
    ObservableList<Viewadmin> data = FXCollections.observableArrayList();
    public void setdata() throws ClassNotFoundException, SQLException{
        DbConnection conn = new DbConnection();
            data.clear();
            String req ="SELECT `particpant`.`nom`, `particpant`.`prenom`, `profil`.`labelle`, `section`.`idsession`, `session`.`idformation`, `formation`.`intitule`, `domaine`.`Libelle` FROM `particpant` LEFT JOIN `profil` ON `particpant`.`idprofil` = `profil`.`Idprofil` LEFT JOIN `section` ON `section`.`idparticipant` = `particpant`.`idparticpant` LEFT JOIN `session` ON `section`.`idsession` = `session`.`Idsession` LEFT JOIN `formation` ON `session`.`idformation` = `formation`.`id`  LEFT JOIN `domaine` ON `formation`.`domaine` = `domaine`.`idDomaine`;";
            ResultSet rs=conn.select(req);
            while (rs.next()){
                Viewadmin views = new Viewadmin();
                views.setItem1(rs.getString(1));
                views.setItem2(rs.getString(2));
                views.setItem3(rs.getString(3));
                views.setItem4(rs.getString(4));
                views.setItem5(rs.getString(5));
                data.add(views);
                table.setItems(data);
            }
    }
    public void settable() throws ClassNotFoundException, SQLException{
        
        Nom.setCellValueFactory(new PropertyValueFactory<Viewadmin,String>("item1"));
        Prenom.setCellValueFactory(new PropertyValueFactory<Viewadmin,String>("item2"));
        Profil.setCellValueFactory(new PropertyValueFactory<Viewadmin,String>("item3"));
        Formation.setCellValueFactory(new PropertyValueFactory<Viewadmin,String>("item4"));
        Domaine.setCellValueFactory(new PropertyValueFactory<Viewadmin,String>("item5"));
        table.getColumns().addAll(Nom,Prenom,Profil,Formation,Domaine);
 
    }
    public void show(String page,String title) throws IOException {
        String path="Views/"+page+".fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(path));
        Scene scene = new Scene(fxmlLoader.load());

        try {
            Login.stage.getIcons().add(new Image(this.getClass().getResource("Views/Img/Logo.png").toString()));
        }catch (Exception e) {

        }
        Login.stage.setTitle(title);
        Login.stage.setScene(scene);
        Login.stage.show();
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            setdata();
        } catch (ClassNotFoundException | SQLException e) {
            
            e.printStackTrace();
        }
        try {
            settable();
        } catch (ClassNotFoundException | SQLException e) {
            
            e.printStackTrace();
        }
    }

}
