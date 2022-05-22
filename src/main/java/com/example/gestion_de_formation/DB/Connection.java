package com.example.gestion_de_formation.DB;

import java.sql.*;
public class Connection {
        Connection con;
        public Connection(){
            try{
                Class.forName("com.mysql.jdbc.Driver");
            }catch(ClassNotFoundException e){
                System.err.println(e);
//pour afficher l erreur
            }
            try{
                con= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/etude","root","");
            }catch(SQLException e){System.err.println(e);}
        }
        Connection obtenirconnexion(){return con;}

    }

