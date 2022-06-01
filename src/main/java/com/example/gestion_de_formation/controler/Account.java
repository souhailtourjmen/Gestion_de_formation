package com.example.gestion_de_formation.controler;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class Account  implements Initializable{

    @FXML
    private CheckBox Idadmin;

    @FXML
    private Button btnRegister;

    @FXML
    private PasswordField cpwd;

    @FXML
    private TextField matricule;

    @FXML
    private Label login;

    @FXML
    private TextField mail;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private PasswordField pwd;

    @FXML
    private TextField tel;
    
    @FXML
    private ComboBox<String> Domaine;

    Check check = new Check();
    DbConnection conn = new DbConnection();
    ObservableList datadomaine = FXCollections.observableArrayList();
    @FXML
    void Login(MouseEvent event) throws IOException {
        show("Login");
    }




    @FXML
    void Register(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
       
        String[]champ=new String[]{nom.getText(),prenom.getText(),mail.getText(),tel.getText(),pwd.getText(),cpwd.getText()};
        if(check.check_champ(champ)!=0){
            if(champ[4].equals(champ[5])){

                if(!Idadmin.isSelected()) {
                    champ = new String[]{nom.getText(), prenom.getText(), mail.getText(), tel.getText(), pwd.getText()};

                    try {
                        String reqcherche="SELECT * FROM `user` WHERE email='"+champ[2]+"'";
                        if(conn.checkaccount(reqcherche) ==0) {
                            String req = "INSERT INTO `particpant`( `nom`, `prenom`, `email`, `tel`, `pwd` ) VALUES ('" + champ[0] + "','" + champ[1] + "','" + champ[2] + "','" + champ[3] + "','" + champ[4] + "')";
                            conn.insert(req);
                            String req1="INSERT INTO `user`(`email`, `password`, `role`) VALUES ('"+champ[2]+"','"+champ[4]+"','U')";
                            conn.insert(req1);
                            User.account=mail.getText();
                            show("UserDashbord");

                        }else{
                            Check.showAlerterreur("Compte exist");
                          
                        }
                    }catch(Exception e){

                    }
                   

                }else{
                    champ = new String[]{nom.getText(), prenom.getText(), mail.getText(), tel.getText(), pwd.getText(),matricule.getText()};
                    try {
                        String reqcherche="SELECT * FROM `admin` WHERE id="+champ[5];
                        if((check.checkchamp(champ[5])==0)&&(conn.checkaccount(reqcherche) ==1)) {
                            String domaine=Domaine.getSelectionModel().getSelectedItem().toString();
                            String reqcontraint="SELECT `idadmin`, `Iddomaine` FROM `administration` WHERE `idadmin`= "+matricule.getText()+" AND `Iddomaine`="+rechdomaine(domaine)+" ;";
                            if(conn.checkaccount(reqcontraint)==0){
                                String req = "INSERT INTO `administration` (`nom`, `prenom`, `email`, `tel`, `pwd`, `idadmin`, `Iddomaine`) VALUES ('"+nom.getText()+"', '"+prenom.getText()+"', '"+mail.getText()+"', '"+tel.getText()+"', '"+pwd.getText()+"', '"+matricule.getText()+"', '"+rechdomaine(domaine)+"');";
                                int i=conn.insert(req);
                                if(i==1){
                                    Check.showAlerterreur( "insert success compte admin !");
                                }
                                String req1="INSERT INTO `user`(`email`, `password`, `role`) VALUES ('"+champ[2]+"','"+champ[4]+"','S')";
                                conn.insert(req1);
                                Admin.account=mail.getText();
                                show("Admin");
                               
                                
                            }else{
                                Check.showAlerterreur("Duplicate entry  for key 'Administration'");
                            }
                           

                        }else{
                            Check.showAlerterreur("compte !");
                           
                        }
                    }catch(Exception e){

                    }
                    
                }

            }else{
                Check.showAlerterreur("password !");
            }


        }else{
            Check.showAlerterreur("champ est vide  !");
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
    
    public int rechdomaine(String domaine) throws ClassNotFoundException, SQLException{
       
        int id=0;
            String req1 ="SELECT `idDomaine` FROM `domaine` WHERE `Libelle`='"+domaine+"'";
            ResultSet rs=conn.select(req1);
            while(rs.next()){
               id=rs.getInt(1);
            }
            return id ;
    }

    public void setdomaine() throws SQLException, ClassNotFoundException {
        
        String req="SELECT * FROM `domaine`";
        ResultSet rs=conn.select(req);
        while (rs.next()){
            datadomaine.add(rs.getString("Libelle"));
        }
        Domaine.setItems(datadomaine);;
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            setdomaine();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}


