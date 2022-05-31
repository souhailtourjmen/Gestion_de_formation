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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Tableformation implements Initializable{

    @FXML
    private TextField Nom;

    @FXML
    private Label date;

    @FXML
    private ComboBox domaine;

    @FXML
    private TextField idformateur;

    @FXML
    private TextField nbrj;

    @FXML
    private TextField nbrp;
    static Viewadmin views ;
    Check check ;
    String datefil="";
    ObservableList datadomaine = FXCollections.observableArrayList();
    DbConnection conn = new DbConnection();
    String Domaine="";
    
    @FXML
    void Addformation(ActionEvent event) throws ClassNotFoundException, SQLException {
        updateItem();
         Admin.stage.close();
    }
    public void setchamp() throws ClassNotFoundException, SQLException{
        setdomaine();
        String req ="SELECT `id`, `intitule`, `domaine`, `nbjour`, `date`, `idformateur`, `nbparticipant` FROM `formation` WHERE `id`="+views.getId();
        ResultSet rs = conn.select(req);
        while (rs.next()){

            Nom.setText(rs.getString(2));
            Domaine=rs.getString(3);
            nbrj.setText(rs.getString(4));
            datefil=(rs.getString(5));
            idformateur.setText(rs.getString(6));
            nbrp.setText(rs.getString(7));
            date.setText(rs.getString(5));
           
        }    
        
    }
    public void setdomaine() throws SQLException, ClassNotFoundException {
        
        String req="SELECT * FROM `domaine`";
        ResultSet rs=conn.select(req);
        while (rs.next()){
            datadomaine.add(rs.getString("Libelle"));
        }
        domaine.setItems(datadomaine);
    }
    
    public void updateItem() throws ClassNotFoundException, SQLException{
            String newDomaine="";
            try {
                newDomaine=domaine.getSelectionModel().getSelectedItem().toString();
            } catch (Exception e) {
                //TODO: handle exception
            }
            String nom=Nom.getText();
            String Nbrj=nbrj.getText();
            String Idf=idformateur.getText();
            String Nbrp=nbrp.getText();
            if(!newDomaine.equals("")){
                 String req1 ="SELECT `idDomaine` FROM `domaine` WHERE `Libelle`='"+newDomaine+"'";
                 ResultSet rs=conn.select(req1);
                 while(rs.next()){
                     Domaine=String.valueOf(rs.getInt(1));
                 }
            }
             if ((cheknumber(Nbrp)==0) && (cheknumber(Nbrj)==0)&& (cheknumber(Idf)==0) ){

                String req = "UPDATE `formation` SET `intitule`='"+nom+"',`domaine`='"+Domaine+"',`nbjour`='"+Nbrj+"',`date`='"+datefil+"',`idformateur`='"+Idf+"',`nbparticipant`='"+Nbrp+"' WHERE `id`="+views.getId();
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
            setchamp();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public  int cheknumber(String strNum){
        if (strNum == null) {
            return -1;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return -1;
        }
        return 0;
    }
}
