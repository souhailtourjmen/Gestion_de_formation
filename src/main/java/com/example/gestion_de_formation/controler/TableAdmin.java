package com.example.gestion_de_formation.controler;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.gestion_de_formation.DB.DbConnection;
import com.example.gestion_de_formation.check.Check;
import com.example.gestion_de_formation.modules.Organisation.Viewadmin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class TableAdmin implements Initializable{

    @FXML
    private ComboBox Direction;

    @FXML
    private TextField Email;

    @FXML
    private TextField Nom;

    @FXML
    private TextField Prenom;

    @FXML
    private TextField Tel;

    @FXML
    private TextField PWD;

    @FXML
    private ComboBox domaine;
    static Viewadmin views ;
    Check check =new Check();
    DbConnection conn = new DbConnection();
    ObservableList dataadmin = FXCollections.observableArrayList();
    ObservableList datadomaine = FXCollections.observableArrayList();

    @FXML
    void Addformateur(ActionEvent event) throws ClassNotFoundException, SQLException {
        Updatedata();
        Admin.stage.close();
    }
    String dom ="";
    String post="";
  
    
    public  ObservableList  setdata( String req) throws SQLException, ClassNotFoundException {
        ObservableList data = FXCollections.observableArrayList();
        ResultSet rs=conn.select(req);
        while (rs.next()){
            data.add(rs.getString(1));
        }
        return data;
    }
    public void setchamp() throws ClassNotFoundException, SQLException{
        String req ="SELECT  `nom`, `prenom`, `email`, `tel`, `pwd`, `idadmin`, `Iddomaine` FROM `administration` WHERE `id` ="+views.getId();
        ResultSet rs = conn.select(req);
        while (rs.next()){

            Nom.setText(rs.getString(1));
            Prenom.setText(rs.getString(2));
            Email.setText(rs.getString(3));
            Tel.setText(rs.getString(4));
            PWD.setText(rs.getString(5));
            dom=rs.getString(6);
            post =rs.getString(7);
        }
         
    }
   
       
    
    public int rechdomaine(String indice ,String req) throws ClassNotFoundException, SQLException{
       
        int id=0;
            String req1 =req+indice+"'";
            ResultSet rs=conn.select(req1);
            while(rs.next()){
               id=rs.getInt(1);
            }
            return id ;
    }
    public void Updatedata() throws ClassNotFoundException, SQLException{

        String nom=Nom.getText();
        String prenom=Prenom.getText();
        String email=Email.getText();
        String tel=Tel.getText();
        String pwd=PWD.getText();
        String newpost="";
        String newDomaine="";
        try {
            newDomaine=domaine.getSelectionModel().getSelectedItem().toString();
        } catch (Exception e) {
            //TODO: handle exception
        }
        try {
            newpost=Direction.getSelectionModel().getSelectedItem().toString();
        } catch (Exception e) {
            //TODO: handle exception
        }
       String[] champ = new String[]{nom,prenom,email,tel};
       if(check.check_champ(champ)!=0) {
        
                if(!newDomaine.equals("")){
                    String req1 ="SELECT `idDomaine` FROM `domaine` WHERE `Libelle`='"+newDomaine+"'";
                    ResultSet rs=conn.select(req1);
                    while(rs.next()){
                        dom=String.valueOf(rs.getInt(1));
                    }
                     }
                if(!newpost.equals("")){
                    String req1 ="SELECT `id`, `post` FROM `admin` WHERE `post`='"+newpost+"'";
                    ResultSet rs=conn.select(req1);
                    while(rs.next()){
                        post=String.valueOf(rs.getInt(1));
                    }
                     }
           String req="UPDATE `administration` SET `nom`='"+nom+"',`prenom`='"+prenom+"',`email`='"+email+"',`tel`='"+tel+"',`pwd`='"+pwd+"',`idadmin`='"+post+"',`Iddomaine`='"+dom+"' WHERE `id` ="+views.getId();
           int result =conn.insert(req);
                     if(result==1){
                        check.showAlerterreur("insert success");
                        }
       }else{
        check.showAlerterreur("Check Idformateur ; nbr de participant ; nbr de jour  " );
       }

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
       try {
        domaine.setItems(setdata("SELECT  `Libelle` FROM `domaine`"));
    } catch (ClassNotFoundException | SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    try {
        Direction.setItems(setdata("SELECT  `post` FROM `admin`"));
    } catch (ClassNotFoundException | SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    try {
        setchamp();
    } catch (ClassNotFoundException | SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

        
    }
}
