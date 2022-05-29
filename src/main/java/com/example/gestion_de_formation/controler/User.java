package com.example.gestion_de_formation.controler;

import com.example.gestion_de_formation.Application;
import com.example.gestion_de_formation.DB.DbConnection;
import com.example.gestion_de_formation.modules.Organisation.Participant;
import com.example.gestion_de_formation.modules.Organisation.Viewsession;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import static java.lang.Integer.parseInt;

public class User implements Initializable {

    @FXML
    private Label Account;

    @FXML
    private Button Btnaddformation;

    @FXML
    private Label Btnlogout;

    @FXML
    private Button Btnoverview;

    @FXML
    private Label Titre;

    @FXML
    private Button btnsesion;

    

    @FXML
    private TableColumn<Viewsession, String> nomd;

    @FXML
    private TableColumn<Viewsession, String> nomf;
    @FXML
    private TableColumn<Viewsession, String> btnnn;

    @FXML
    private TableColumn<Viewsession, String> dab;

    @FXML
    private TableColumn<Viewsession, String> daf;
    @FXML
    private Label titel;

    

    @FXML
    private TableView<Viewsession> table;

    static String account ;
    static Participant particpant = new Participant();
    ObservableList<Viewsession> data = FXCollections.observableArrayList();
    @FXML
    void Addformation(ActionEvent event) throws IOException {
        show("userformation");

    }

    @FXML
    void Addprofil(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        show("Addprofil");
        
    }

    @FXML
    void Overview(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {

        setdata();
        setuser();

    }

    @FXML
    void Session(ActionEvent event) {

    }

    @FXML
    void logout(MouseEvent event) {

        }

   

    public void settable() throws ClassNotFoundException, SQLException{
        setdata();
       
        nomf.setCellValueFactory(new PropertyValueFactory<Viewsession,String>("Nomf"));
        nomd.setCellValueFactory(new PropertyValueFactory<Viewsession,String>("Nomd"));
        dab.setCellValueFactory(new PropertyValueFactory<Viewsession,String>("db"));
        daf.setCellValueFactory(new PropertyValueFactory<Viewsession,String>("df"));
        table.getColumns().addAll(nomf, nomd,dab,daf);
 
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
    public   void setuser() throws SQLException, ClassNotFoundException {
        DbConnection conn = new DbConnection();
        String req="SELECT `idparticpant`, `nom`, `prenom`, `email`, `tel`, `pwd`, `idprofil` FROM `particpant` WHERE email='"+User.account+"'";
        ResultSet rs=conn.select(req);
        while (rs.next()){
            particpant.setId(parseInt(rs.getString(1)));
            particpant.setNom( rs.getString(2));
            particpant.setPrenom(rs.getString(3));
            particpant.setEmail(rs.getString(4));
            particpant.setTel(rs.getString(5));
        }
        
    }
    
    public void setdata() throws ClassNotFoundException, SQLException{
        DbConnection conn = new DbConnection();
            data.clear();
            String req="SELECT   `F`.`intitule` AS `Nom de formation`, `D`.`Libelle` AS `Nom de domaine`,`SE`.`debut` AS `Date de debut`, `SE`.`fin` AS `Date de fin`FROM `section` AS `S` LEFT JOIN `session` AS `SE` ON `S`.`idsession` = `SE`.`Idsession`  LEFT JOIN `formation` AS `F` ON `SE`.`idformation` = `F`.`id` LEFT JOIN `domaine` AS `D` ON `F`.`domaine` = `D`.`idDomaine` WHERE `S`.`idparticipant`='"+User.particpant.getId()+"';";
            ResultSet rs=conn.select(req);
            while (rs.next()){
                Viewsession views =new Viewsession();
                views.setNomf(rs.getString(1));
                views.setNomd(rs.getString(2));
                views.setDatedeb(rs.getDate(3));
                views.setDatefin(rs.getDate(4));
                views.setDb(String.valueOf(rs.getDate(3)));
                views.setDf(String.valueOf(rs.getDate(4)));
                data.add(views);
                table.setItems(data);
            }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Account.setText(account);
       
        
            try {
                setdata();
            } catch (ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
       
        try {
            setuser();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            settable();
        } catch (ClassNotFoundException | SQLException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
    }
   
}
