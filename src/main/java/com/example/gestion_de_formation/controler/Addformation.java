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

public class Addformation implements Initializable {

    @FXML
    private TextField Nom;

    @FXML
    private ComboBox domaine;

    @FXML
    private TextField idformateur;

    @FXML
    private TextField nbrj;

    @FXML
    private TextField nbrp;
    @FXML
    private DatePicker date;

    @FXML
    void Addformation(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
            insertdat();
            show("Admin");

    }

    ObservableList datadomaine = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setdomaine();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setdomaine() throws SQLException, ClassNotFoundException {
        DbConnection conn = new DbConnection();
        String req="SELECT * FROM `domaine`";
        ResultSet rs=conn.select(req);
        while (rs.next()){
            datadomaine.add(rs.getString("Libelle"));
        }
        domaine.setItems(datadomaine);
    }


    public void insertdat() throws SQLException, ClassNotFoundException {
        DbConnection conn = new DbConnection();
        int id = 0;
        Check check =new Check();
        String domain =domaine.getSelectionModel().getSelectedItem().toString();
        String intitule=Nom.getText();
        int Nbrj=parseInt(nbrj.getText());
        int Nbrp=parseInt(nbrp.getText());
        LocalDate dat=date.getValue();
        String  Date= dat.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(Date);
        int Idformateur=parseInt(idformateur.getText());
        String req2="SELECT * FROM `formateur` WHERE id="+Idformateur;
        int ch=conn.checkaccount(req2);

        if(ch==1) {
            if ((check.cheknumber(nbrj.getText())== 0) & (check.cheknumber(nbrp.getText()) == 0) ) {
                String req1 = "SELECT `idDomaine`, `Libelle` FROM `domaine` WHERE Libelle='" + domain + "'";
                ResultSet rs = conn.select(req1);
                while (rs.next()) {
                    id = rs.getInt("idDomaine");
                }

                String req = "INSERT INTO `formation`(`intitule`, `domaine`, `nbjour`, `date`, `idformateur`, `nbparticipant`) VALUES ('"+intitule+"',"+id+","+Nbrj+",'"+Date+"',"+Idformateur+","+Nbrp+")";
                int v=conn.insert(req);
            } else {
                Check.showAlerterreur(" id formateur pas trouver ="+Idformateur);
            }
        }else{
            Check.showAlerterreur("compte est incorect");
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
