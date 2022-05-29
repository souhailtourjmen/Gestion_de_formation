package com.example.gestion_de_formation.controler;

import com.example.gestion_de_formation.Application;
import com.example.gestion_de_formation.DB.DbConnection;
import com.example.gestion_de_formation.modules.Organisation.Formateur;
import com.example.gestion_de_formation.modules.Organisation.Formation;
import com.example.gestion_de_formation.modules.Organisation.Participant;
import com.example.gestion_de_formation.modules.Organisation.Session;
import com.example.gestion_de_formation.modules.Organisation.Viewadmin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.example.gestion_de_formation.controler.Login.stage;

public class Admin  implements Initializable{

    @FXML
    private Label Account;

    @FXML
    private Button Btn4;

    @FXML
    private Button Btnaddformateur;

    @FXML
    private Button Btnaddformation;

    @FXML
    private Label Btnlogout;

    @FXML
    private Button Btnmenu2;

    @FXML
    private Button Btnoverview;

    @FXML
    private Label Titre;

    @FXML
    private Button btnMenu3;

    @FXML
    private Button btnmenu1;

    @FXML
    private Button btnsesion;
    @FXML
    private TableView<Viewadmin> table;

    @FXML
    private TableColumn<Viewadmin, String> Item1;

    @FXML
    private TableColumn<Viewadmin, String> Item2;

    @FXML
    private TableColumn<Viewadmin, String> Item3;

    @FXML
    private TableColumn<Viewadmin, String> Item4;

    @FXML
    private TableColumn<Viewadmin, String> Item5;

    ObservableList<Viewadmin> data = FXCollections.observableArrayList();
    // ObservableList<Formation> formation = FXCollections.observableArrayList();
    // ObservableList<Formateur> formateur = FXCollections.observableArrayList();
    // ObservableList<Session> session = FXCollections.observableArrayList();

    @FXML
    void Addformateur(ActionEvent event) throws IOException {
        show("Addformateur","Addformateur");

    }

    @FXML
    void Addformation(ActionEvent event) throws IOException {
        show("Addforamtion","Addformation");
    }

    @FXML
    void Demande(ActionEvent event) throws IOException {

    }

    @FXML
    void Formateur(ActionEvent event) {

    }

    @FXML
    void Formation(ActionEvent event) {

    }

    @FXML
    void Overview(ActionEvent event) {

    }

    @FXML
    void Particpants(ActionEvent event) {

    }

    @FXML
    void Session(ActionEvent event) throws IOException {
        show("AddSession","AddSession");
    }

    @FXML
    void logout(MouseEvent event) {

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
        String reqFormation="SELECT `formation`.`id`, `formation`.`intitule`, `formation`.`domaine`, `session`.`debut`, `formateur`.`prenom`FROM `formation`LEFT JOIN `session` ON `session`.`idformation` = `formation`.`id` LEFT JOIN `formateur` ON `formation`.`idformateur` = `formateur`.`id`;";
        String reqFormateur="SELECT `formateur`.`id`, `formateur`.`nom`, `formateur`.`prenom`, `formateur`.`email`, `domaine`.`Libelle`FROM `formateur` LEFT JOIN `domaine` ON `formateur`.`domaine` = `domaine`.`idDomaine`;";
        String reqparticpant="SELECT `particpant`.`nom`, `particpant`.`prenom`, `profil`.`labelle`, `section`.`idsession`, `session`.`idformation`, `formation`.`intitule`, `domaine`.`Libelle`FROM `particpant` LEFT JOIN `profil` ON `particpant`.`idprofil` = `profil`.`Idprofil` LEFT JOIN `section` ON `section`.`idparticipant` = `particpant`.`idparticpant` LEFT JOIN `session` ON `section`.`idsession` = `session`.`Idsession` LEFT JOIN `formation` ON `session`.`idformation` = `formation`.`id`  LEFT JOIN `domaine` ON `formation`.`domaine` = `domaine`.`idDomaine`;";
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        String req = "SELECT `formation`.`intitule`, `formateur`.`prenom`, `session`.`debut`, `session`.`fin`, `domaine`.`Libelle` FROM `formation` LEFT JOIN `formateur` ON `formation`.`idformateur` = `formateur`.`id` LEFT JOIN `session` ON `session`.`idformation` = `formation`.`id` LEFT JOIN `domaine` ON `formateur`.`domaine` = `domaine`.`idDomaine` WHERE `session`.`debut` IS NOT NULL ;";
        try {
            setdata(req);
        } catch (ClassNotFoundException | SQLException e) {
            
            e.printStackTrace();
        }
        try {
            settable("intitule","Prenom","Debut Session","Fin Session","Domaine");
        } catch (ClassNotFoundException | SQLException e) {
            
            e.printStackTrace();
        }

    }
    public void setdata(String req) throws ClassNotFoundException, SQLException{
        DbConnection conn = new DbConnection();
            data.clear();
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
    public void settable(String cl1, String cl2, String cl3, String cl4, String cl5) throws ClassNotFoundException, SQLException{
        
        Item1.setText(cl1);
        Item2.setText(cl2);
        Item3.setText(cl3);
        Item4.setText(cl4);
        Item5.setText(cl5);

        Item1.setCellValueFactory(new PropertyValueFactory<Viewadmin,String>("item1"));
        Item2.setCellValueFactory(new PropertyValueFactory<Viewadmin,String>("item2"));
        Item3.setCellValueFactory(new PropertyValueFactory<Viewadmin,String>("item3"));
        Item4.setCellValueFactory(new PropertyValueFactory<Viewadmin,String>("item4"));
        Item5.setCellValueFactory(new PropertyValueFactory<Viewadmin,String>("item4"));
        table.getColumns().addAll(Item1,Item2,Item3,Item4,Item5);
 
    }



}
