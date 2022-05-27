package com.example.gestion_de_formation.DB;

import java.sql.*;

import java.net.Socket;


public class DbConnection {

    Connection connection;
    Statement statement;
    String SQL;


    String IPHOST = "127.0.0.1";
    int PORT = 3325;
    String Name_DB ="Formation";
    String username="root";
    String password="";
    String url = "jdbc:mysql://" + IPHOST + ":"+PORT+"/"+Name_DB;
    Socket client;

    public DbConnection() {
    }

    public void connexionDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

             connection = DriverManager.getConnection(url, username, password);
            System.out.println("connection valide");
        } catch (Exception e)
        {
            System.err.println(e);//
        }

    }


    public Connection closeconnexion() {

        try {
            connection.close();
            return connection;
        } catch (Exception e) {System.err.println(e);//
        }

        return null;
    }

    public  ResultSet select( String request ) throws ClassNotFoundException, SQLException {
        ResultSet rs = null;
        connexionDatabase();
        statement = connection.createStatement();
        return  rs = statement.executeQuery(request);


    }





    //verification compte client
    public  int checkaccount(String sql  ) throws ClassNotFoundException {


        int rsn =0;
        try {
            connexionDatabase();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                rsn++;

            }
            closeconnexion();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsn;
    }
    // methode insert
    public int insert(String req  ) throws SQLException, ClassNotFoundException{
        int rs=0;
        try {
            connexionDatabase();
            statement = connection.createStatement();
            rs=statement.executeUpdate(req);
            closeconnexion();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs ;

    }
}