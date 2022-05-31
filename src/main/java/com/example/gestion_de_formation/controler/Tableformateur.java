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

public class Tableformateur implements Initializable{

    @FXML
    private TextField Email;

    @FXML
    private TextField Nom;

    @FXML
    private TextField Prenom;

    @FXML
    private TextField Tel;

    @FXML
    private ComboBox domaine;
    static Viewadmin views ;
    Check check =new Check();
    String Domaine="";
    DbConnection conn = new DbConnection();
    ObservableList datadomaine = FXCollections.observableArrayList();
    @FXML
    void Addformateur(ActionEvent event) throws ClassNotFoundException, SQLException {
        senddate();
    }

    public void setchamp() throws ClassNotFoundException, SQLException{
        setdomaine();
        String req ="SELECT `id`, `nom`, `prenom`, `domaine`, `email`, `tel` FROM `formateur` WHERE  `id`="+views.getId();
        ResultSet rs = conn.select(req);
        while (rs.next()){

            Nom.setText(rs.getString(2));
            Prenom.setText(rs.getString(3));
            Domaine=(rs.getString(4));
            Email.setText(rs.getString(5));
            Tel.setText(rs.getString(6));
        
           
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
    public void senddate() throws ClassNotFoundException, SQLException{

        String newDomaine="";
            try {
                newDomaine=domaine.getSelectionModel().getSelectedItem().toString();
            } catch (Exception e) {
                //TODO: handle exception
            }
            if(!newDomaine.equals("")){
                String req1 ="SELECT `idDomaine` FROM `domaine` WHERE `Libelle`='"+newDomaine+"'";
                ResultSet rs=conn.select(req1);
                while(rs.next()){
                    Domaine=String.valueOf(rs.getInt(1));
                }
           }

        String nom=Nom.getText();
        String prenom=Prenom.getText();
        String email=Email.getText();
        String tel=Tel.getText();
        String[] champ = new String[]{nom,prenom,email,tel};
        if(check.check_champ(champ)!=0) {
            String req = "UPDATE `formateur` SET `nom`='"+nom+"',`prenom`='"+prenom+"',`domaine`='"+Domaine+"',`email`='"+email+"',`tel`='"+tel+"' WHERE `id` ="+views.getId();
            int reuslt =conn.insert(req);
            if(reuslt==1){
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
