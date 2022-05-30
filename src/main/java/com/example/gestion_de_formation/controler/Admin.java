package com.example.gestion_de_formation.controler;

import com.example.gestion_de_formation.Application;
import com.example.gestion_de_formation.DB.DbConnection;
import com.example.gestion_de_formation.check.Check;
import com.example.gestion_de_formation.modules.Organisation.Formateur;
import com.example.gestion_de_formation.modules.Organisation.Formation;
import com.example.gestion_de_formation.modules.Organisation.Participant;
import com.example.gestion_de_formation.modules.Organisation.Session;
import com.example.gestion_de_formation.modules.Organisation.Viewadmin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.util.Callback;
import static com.example.gestion_de_formation.controler.Login.stage;

public class Admin  implements Initializable{

    @FXML
    private Label Account;

    @FXML
    private Button Btn4;

    @FXML
    private Button Btnaddformateur;

    @FXML
    private Button Btnaddformation;

    @FXML
    private Label Btnlogout;

    @FXML
    private Button Btnmenu2;

    @FXML
    private Button Btnoverview;

    @FXML
    private Label Titre;

    @FXML
    private Button btnMenu3;

    @FXML
    private Button btnmenu1;

    @FXML
    private Button btnsesion;
    @FXML
    private TableView<Viewadmin> table;

    @FXML
    private TableColumn<Viewadmin, String> Item1;

    @FXML
    private TableColumn<Viewadmin, String> Item2;

    @FXML
    private TableColumn<Viewadmin, String> Item3;

    @FXML
    private TableColumn<Viewadmin, String> Item4;

    @FXML
    private TableColumn<Viewadmin, String> Item5;
    @FXML
    private TableColumn<Viewadmin, String> btn;
    @FXML
    private TableColumn<Viewadmin, String> IDD;


    ObservableList<Viewadmin> data = FXCollections.observableArrayList();

    public static String account ;
    @FXML
    void Addformateur(ActionEvent event) throws IOException {
        show("Addformateur","Addformateur");
        
    }

    @FXML
    void Addformation(ActionEvent event) throws IOException {
        show("Addforamtion","Addformation");
    }

    @FXML
    void Formateur(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        // show("Tableformateur","Formateur");
        Titre.setText("List Formateur");
        changenomcolumns("Id","Nom","Prenom","Email","Formation");
        data.clear();
        Check.Sql="SELECT `formateur`.`id`, `formateur`.`nom`, `formateur`.`prenom`, `formateur`.`email`, `formateur`.`tel`, `domaine`.`Libelle` FROM `formateur` LEFT JOIN `domaine` ON `formateur`.`domaine` = `domaine`.`idDomaine`;";
        try {
            setdata(Check.Sql);
        } catch (ClassNotFoundException | SQLException e) {
            
            e.printStackTrace();
        }
    }

    @FXML
    void Formation(ActionEvent event) throws IOException {
        // show("Tableformation","Formations");
        Titre.setText("List Formation");
        changenomcolumns("Id","Formation","Domaine","Debut","Prenom Formateur");
        data.clear();
        Check.Sql="SELECT `formation`.`id`, `formation`.`intitule`, `formation`.`domaine`, `session`.`debut`, `formateur`.`id`, `formateur`.`prenom` FROM `formation`   LEFT JOIN `session` ON `session`.`idformation` = `formation`.`id`  LEFT JOIN `formateur` ON `formation`.`idformateur` = `formateur`.`id` ORDER by `session`.`debut` DESC ;";
        try {
            setdata(Check.Sql);
        } catch (ClassNotFoundException | SQLException e) {
            
            e.printStackTrace();
        }
        
        

    }

    @FXML
    void Overview(ActionEvent event) throws IOException {
        Titre.setText("Overview");
        data.clear();
        Check.Sql="SELECT `formation`.`id`, `formation`.`intitule`, `formateur`.`prenom`, `session`.`debut`, `session`.`fin`, `domaine`.`Libelle` FROM `formation` LEFT JOIN `formateur` ON `formation`.`idformateur` = `formateur`.`id` LEFT JOIN `session` ON `session`.`idformation` = `formation`.`id` LEFT JOIN `domaine` ON `formateur`.`domaine` = `domaine`.`idDomaine` WHERE `session`.`debut` IS NOT NULL ;";
        changenomcolumns("Formation","Prenom formateur","Debut de Formation","Fin de Formation","Domaine");
        try {
            setdata(Check.Sql);
        } catch (ClassNotFoundException | SQLException e) {
            
            e.printStackTrace();
        }
       
    }

    @FXML
    void Particpants(ActionEvent event) throws IOException {
        // show("TableParicpant","Particpant");
        Titre.setText("List Overview");
        changenomcolumns("Nom","Prenom","Profil","Formation","Domaine");
        data.clear();
        Check.Sql="SELECT `particpant`.`idparticpant`, `particpant`.`nom`, `particpant`.`prenom`, `profil`.`labelle`, `formation`.`intitule`, `domaine`.`Libelle` FROM `particpant` LEFT JOIN `profil` ON `particpant`.`idprofil` = `profil`.`Idprofil` LEFT JOIN `section` ON `section`.`idparticipant` = `particpant`.`idparticpant` LEFT JOIN `session` ON `section`.`idsession` = `session`.`Idsession` LEFT JOIN `formation` ON `session`.`idformation` = `formation`.`id`  LEFT JOIN `domaine` ON `formation`.`domaine` = `domaine`.`idDomaine`;";
        try {
            setdata(Check.Sql);
        } catch (ClassNotFoundException | SQLException e) {
            
            e.printStackTrace();
        }
    }

    @FXML
    void Session(ActionEvent event) throws IOException {
        show("AddSession","AddSession");
    }

    @FXML
    void logout(MouseEvent event) {

    }
    public void show(String page,String title) throws IOException {
        String path="Views/"+page+".fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(path));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        try {
            stage.getIcons().add(new Image(this.getClass().getResource("Views/Img/Logo.png").toString()));
        }catch (Exception e) {

        }
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Account.setText(account);
        try {
            setdata(Check.Sql);
        } catch (ClassNotFoundException | SQLException e) {
            
            e.printStackTrace();
        }
        try {
            settable();
        } catch (ClassNotFoundException | SQLException e) {
            
            e.printStackTrace();
        }

    }
    public void setdata(String sql) throws ClassNotFoundException, SQLException{
        DbConnection conn = new DbConnection();
            data.clear();
            ResultSet rs=conn.select(sql);
            while (rs.next()){
                Viewadmin views = new Viewadmin();
                views.setId(rs.getInt(1));
                views.setItem1(rs.getString(2));
                views.setItem2(rs.getString(3));
                views.setItem3(rs.getString(4));
                views.setItem4(rs.getString(5));
                views.setItem5(rs.getString(6));
                data.add(views);
               
                table.setItems(data);
            }
    }
    public void settable() throws ClassNotFoundException, SQLException{
        IDD.setCellValueFactory(new PropertyValueFactory<Viewadmin,String>("id"));
        Item1.setCellValueFactory(new PropertyValueFactory<Viewadmin,String>("item1"));
        Item2.setCellValueFactory(new PropertyValueFactory<Viewadmin,String>("item2"));
        Item3.setCellValueFactory(new PropertyValueFactory<Viewadmin,String>("item3"));
        Item4.setCellValueFactory(new PropertyValueFactory<Viewadmin,String>("item4"));
        Item5.setCellValueFactory(new PropertyValueFactory<Viewadmin,String>("item5"));
        button();
        table.getColumns().addAll(Item1,Item2,Item3,Item4,Item5);
 
    }
   
    String Itemselcted="Null";
    public void button () {
        Callback<TableColumn<Viewadmin, String>, TableCell<Viewadmin, String>> cellFoctory = (TableColumn<Viewadmin, String> param) -> {
            // make cell containing buttons
            final TableCell<Viewadmin, String> cell = new TableCell<Viewadmin, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                  
                        Button deleteIcon =new Button("D");
                        Button editIcon =new Button("E");
                        deleteIcon.setStyle("-fx-background-color: #CA0B00; ");
                        editIcon.setStyle("-fx-background-color: #067242; ");
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            Viewadmin views = table.getSelectionModel().getSelectedItem();
                            
                            if(views == null ){
                                
                                Check.showAlerterreur(" Item no Selected");

                             }
                             else if(!Itemselcted.equals(String.valueOf(views.getId()))){
                                System.out.println(views.getId());
                                Itemselcted=String.valueOf(views.getId());
                             }
                          
                            
                           

                          

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            // student = studentsTable.getSelectionModel().getSelectedItem();
                            // FXMLLoader loader = new FXMLLoader ();
                            // loader.setLocation(getClass().getResource("/tableView/addStudent.fxml"));
                            // try {
                            //     loader.load();
                            // } catch (IOException ex) {
                            //     Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
                            // }
                            
                            // AddStudentController addStudentController = loader.getController();
                            // addStudentController.setUpdate(true);
                            // addStudentController.setTextField(student.getId(), student.getName(), 
                            //         student.getBirth().toLocalDate(),student.getAdress(), student.getEmail());
                            // Parent parent = loader.getRoot();
                            // Stage stage = new Stage();
                            // stage.setScene(new Scene(parent));
                            // stage.initStyle(StageStyle.UTILITY);
                            // stage.show();
                            

                           

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        btn.setCellFactory(cellFoctory);
    }


    public void changenomcolumns(String item1, String item2, String item3, String item4, String item5){
        Item1.setText(item1);
        Item2.setText(item2);
        Item3.setText(item3);
        Item4.setText(item4);
        Item5.setText(item5);
    }
    

}
