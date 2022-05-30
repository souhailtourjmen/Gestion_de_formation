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
public class Tableformation implements Initializable{

    @FXML
    private TableColumn<Viewadmin, String> Debut;

    @FXML
    private TableColumn<Viewadmin, String> Domaine;

    @FXML
    private TableColumn<Viewadmin, String> Formation;

    @FXML
    private TableColumn<Viewadmin, String> Id;

    @FXML
    private TableColumn<Viewadmin, String> Idfor;

    @FXML
    private TableColumn<Viewadmin, String> PrF;

    @FXML
    private TableView<Viewadmin> table;

    @FXML
    void Retour(ActionEvent event) throws IOException {
        show("Admin","Home ");
    }
    ObservableList<Viewadmin> data = FXCollections.observableArrayList();
    public void setdata() throws ClassNotFoundException, SQLException{
        DbConnection conn = new DbConnection();
            data.clear();
            String req="SELECT `formation`.`id`, `formation`.`intitule`, `formation`.`domaine`, `session`.`debut`, `formateur`.`id`, `formateur`.`prenom` FROM `formation`   LEFT JOIN `session` ON `session`.`idformation` = `formation`.`id`  LEFT JOIN `formateur` ON `formation`.`idformateur` = `formateur`.`id` ORDER by `session`.`debut` DESC ;";
            ResultSet rs=conn.select(req);
            while (rs.next()){
                Viewadmin views = new Viewadmin();
                views.setItem1(rs.getString(1));
                views.setItem2(rs.getString(2));
                views.setItem3(rs.getString(3));
                views.setItem4(rs.getString(4));
                views.setItem5(rs.getString(5));
                views.setItem6(rs.getString(6));
                data.add(views);
                System.out.println(views.getItem1());
                table.setItems(data);
            }
    }
    public void settable() throws ClassNotFoundException, SQLException{
        
        Id.setCellValueFactory(new PropertyValueFactory<Viewadmin,String>("item1"));
        Formation.setCellValueFactory(new PropertyValueFactory<Viewadmin,String>("item2"));
        Domaine.setCellValueFactory(new PropertyValueFactory<Viewadmin,String>("item3"));
        Debut.setCellValueFactory(new PropertyValueFactory<Viewadmin,String>("item4"));
        Idfor.setCellValueFactory(new PropertyValueFactory<Viewadmin,String>("item5"));
        PrF.setCellValueFactory(new PropertyValueFactory<Viewadmin,String>("item6"));
        table.getColumns().addAll(Id,Formation,Domaine,Debut,Idfor);
 
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
