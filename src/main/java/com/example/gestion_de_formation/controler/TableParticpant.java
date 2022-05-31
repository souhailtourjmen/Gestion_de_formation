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

public class TableParticpant implements Initializable{

    @FXML
    private TextField email;

    @FXML
    private TextField mdp;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private ComboBox profil;

    @FXML
    private TextField tel;
    ObservableList dataprofil = FXCollections.observableArrayList();
    public static Viewadmin views ;
    DbConnection conn = new DbConnection();
    int id ;
    @FXML
    void Addprofil(ActionEvent event) throws ClassNotFoundException, SQLException {
        insertdat();
        Admin.stage.close();
    }

    public void setprofil() throws SQLException, ClassNotFoundException {
        DbConnection conn = new DbConnection();
        String req="SELECT * FROM `profil` ";
        ResultSet rs=conn.select(req);
        while (rs.next()){
            dataprofil.add(rs.getString("labelle"));
        }
        profil.setItems(dataprofil);
    }

    public void setchamp() throws ClassNotFoundException, SQLException{
        setprofil();
        String req ="SELECT `idparticpant`, `nom`, `prenom`, `email`, `tel`, `pwd`, `idprofil` FROM `particpant` WHERE `idparticpant`="+views.getId();
        ResultSet rs = conn.select(req);
        while (rs.next()){

            nom.setText(rs.getString(2));
            prenom.setText(rs.getString(3));
            email.setText(rs.getString(4));
            tel.setText(rs.getString(5));
            mdp.setText(rs.getString(6));
            id=rs.getInt(7);
        }
           
            
        
    }
    
    public void insertdat() throws SQLException, ClassNotFoundException {
        
        
        Check check =new Check();
        String profile="";
        
            try {
                profile=profil.getSelectionModel().getSelectedItem().toString();
            } catch (Exception e) {
                //TODO: handle exception
            }
         
        String Nom=nom.getText();
        String Prenom=prenom.getText();
        String Email=email.getText();
        String Tel=tel.getText();
        String PWD=mdp.getText();
    
       String[] champ = new String[]{Nom,Prenom,Email,Tel};
        if(check.check_champ(champ)!=0) {

               if(!profile.equals("")){

                   String req1="SELECT `Idprofil` FROM `profil` WHERE `labelle`='"+views.getItem3()+"'";
                   ResultSet rs =conn.select(req1);
                   while (rs.next()){
                       id=rs.getInt(1);
   
                   }
                   
                }
                    String req = "UPDATE `particpant` SET `nom`='"+Nom+"',`prenom`='"+Prenom+"',`email`='"+Email+"',`tel`='"+Tel+"',`pwd`='"+PWD+"' ,`idprofil`='"+id+"' WHERE  `idparticpant`="+views.getId();
                    int result =conn.insert(req);
                     if(result==1){
                        check.showAlerterreur("insert success");
                        }

           
        }else{
            check.showAlerterreur("check champ");
        }
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    
        try {
            setchamp();
        } catch (ClassNotFoundException | SQLException e) {
           
        }
    }

}
