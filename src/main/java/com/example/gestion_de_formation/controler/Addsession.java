package com.example.gestion_de_formation.controler;

import com.example.gestion_de_formation.Application;
import com.example.gestion_de_formation.DB.DbConnection;
import com.example.gestion_de_formation.check.Check;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class Addsession implements Initializable {

    @FXML
    private DatePicker debut;

    @FXML
    private DatePicker fin;

    @FXML
    private ComboBox formation;

    @FXML
    private TextField nbrp;

    @FXML
    void Add(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        insertdat();
        show("Admin");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setformation();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    ObservableList dataformation = FXCollections.observableArrayList();
    public void setformation() throws SQLException, ClassNotFoundException {
        DbConnection conn = new DbConnection();
        String req="SELECT * FROM `formation`";
        ResultSet rs=conn.select(req);
        while (rs.next()){
            dataformation.add(rs.getString("intitule"));
        }
        formation.setItems(dataformation);
    }


    public void insertdat() throws SQLException, ClassNotFoundException {
        DbConnection conn = new DbConnection();
        int id = 0;
        Check check =new Check();
        String Formation =formation.getSelectionModel().getSelectedItem().toString();
        LocalDate dat=debut.getValue();
        String  Datedeb= dat.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate dat1=fin.getValue();
        String  Datefin= dat1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if(check.cheknumber(nbrp.getText())==0) {
            int Nbrp=parseInt(nbrp.getText());
            String req1="SELECT `id`, `intitule` FROM `formation` WHERE intitule='"+Formation+"'";
            ResultSet rs =conn.select(req1);
            while (rs.next()){
                id=rs.getInt("id");

            }
            String req = "INSERT INTO `session`( `idformation`, `debut`, `fin`, `Nbparticipant`) VALUES ("+id+",'"+Datedeb+"','"+Datefin+"',"+Nbrp+")";

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


