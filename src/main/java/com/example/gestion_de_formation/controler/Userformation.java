package com.example.gestion_de_formation.controler;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.gestion_de_formation.Application;
import com.example.gestion_de_formation.DB.DbConnection;
import com.example.gestion_de_formation.modules.Organisation.Participant;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static java.lang.Integer.parseInt;

import java.io.IOException;

public class Userformation implements Initializable {

    @FXML
    private ComboBox<String> Formation;

    @FXML
    private Label datedeb;

    @FXML
    private Label datefin;
    ObservableList datasession = FXCollections.observableArrayList();
    Participant particpant = new Participant();

    @FXML
    void Addformation(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        insertdat();
        show();
    }
    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            setSession();
            Formation.setOnAction(arg01 -> {
                try {
                    toStirngdate(arg01);
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
         
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    public void setSession() throws SQLException, ClassNotFoundException {
        DbConnection conn = new DbConnection();
        String req="SELECT `F`.`intitule` AS `Nom` FROM `session` AS `S` LEFT JOIN `formation` AS `F` ON `S`.`idformation` = `F`.`id`;";
        ResultSet rs=conn.select(req);
        while (rs.next()){
            datasession.add(rs.getString("Nom"));
        }
        Formation.setItems(datasession);
    }
    int idsession = 0;
    public void toStirngdate(ActionEvent event) throws ClassNotFoundException, SQLException{
        DbConnection conn = new DbConnection();
        String parms=Formation.getSelectionModel().getSelectedItem().toString();
        String req="SELECT `formation`.`intitule`,`session`.`Idsession`, `session`.`debut`, `session`.`fin` FROM `formation` LEFT JOIN `session` ON `session`.`idformation` = `formation`.`id`WHERE  `formation`.`intitule`='"+parms+"';";
        ResultSet rs=conn.select(req);
        while (rs.next()){
                datedeb.setText(rs.getString(2));
                datefin.setText(rs.getString(3));
                idsession=parseInt(rs.getString(2));
        }
     
    }
    public void insertdat() throws ClassNotFoundException, SQLException{
    DbConnection conn = new DbConnection();
    String sql = "INSERT INTO `section`(`idsession`, `idparticipant`) VALUES ("+idsession+","+User.particpant.getId()+")";
    conn.insert(sql);
   }
    
     public void show() throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Views/UserDashbord.fxml"));
         Scene scene = new Scene(fxmlLoader.load());
         Stage stage = new Stage();
         try {
             stage.getIcons().add(new Image(this.getClass().getResource("Views/Img/Logo.png").toString()));
         }catch (Exception e) {

         }
         stage.setTitle("Welcome !");
         stage.setScene(scene);
         stage.show();
    }
                     
   
}
                 
