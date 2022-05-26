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

    public ResultSet exécutionQuery(String sql) {
        connexionDatabase();
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            System.out.println(sql);
        } catch (SQLException ex) {System.err.println(ex);//
        }
        return resultSet;
    }

    public String exécutionUpdate(String sql) {
        connexionDatabase();
        String result = "";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            result = "secces";

        } catch (SQLException ex) {
            result = ex.toString();
        }
        return result;

    }


    public ResultSet querySelectAll(String nomTable) {

        connexionDatabase();
        SQL = "SELECT * FROM " + nomTable;
        System.out.println(SQL);
        return this.exécutionQuery(SQL);

    }
    public int Check(String nomTable,String etat) throws SQLException {
        int rsn=0;
        connexionDatabase();
        SQL = "SELECT * FROM " + nomTable+"where email="+etat;
        System.out.println(SQL);
         ResultSet rs=exécutionQuery(SQL);
        while (rs.next()) {
            rsn++;
        }
        return rsn;

    }


    public ResultSet querySelectAll(String nomTable, String état) {

        connexionDatabase();
        SQL = "SELECT * FROM " + nomTable + " WHERE " + état;
        return this.exécutionQuery(SQL);

    }


    public ResultSet querySelect(String[] nomColonne, String nomTable) {

        connexionDatabase();
        int i;
        SQL = "SELECT ";

        for (i = 0; i <= nomColonne.length - 1; i++) {
            SQL += nomColonne[i];
            if (i < nomColonne.length - 1) {
                SQL += ",";
            }
        }

        SQL += " FROM " + nomTable;
        return this.exécutionQuery(SQL);

    }


    public ResultSet fcSelectCommand(String[] nomColonne, String nomTable, String état) {

        connexionDatabase();
        int i;
        SQL = "SELECT ";

        for (i = 0; i <= nomColonne.length - 1; i++) {
            SQL += nomColonne[i];
            if (i < nomColonne.length - 1) {
                SQL += ",";
            }
        }

        SQL += " FROM " + nomTable + " WHERE " + état;
        return this.exécutionQuery(SQL);

    }

    public String queryInsert(String nomTable, String[] contenuTableau) {

        connexionDatabase();
        int i;
        SQL = "INSERT INTO " + nomTable + " VALUES(";

        for (i = 0; i <= contenuTableau.length - 1; i++) {
            SQL += "'" + contenuTableau[i] + "'";
            if (i < contenuTableau.length - 1) {
                SQL += ",";
            }
        }

        SQL += ")";
        return this.exécutionUpdate(SQL);

    }
//Fungsi eksekusi query insert

    public String queryInsert(String nomTable, String[] nomColonne, String[] contenuTableau) {

        connexionDatabase();
        int i;
        SQL = "INSERT INTO " + nomTable + "(";
        for (i = 0; i <= nomColonne.length - 1; i++) {
            SQL += nomColonne[i];
            if (i < nomColonne.length - 1) {
                SQL += ",";
            }
        }
        SQL += ") VALUES(";
        for (i = 0; i <= contenuTableau.length - 1; i++) {
            SQL += "'" + contenuTableau[i] + "'";
            if (i < contenuTableau.length - 1) {
                SQL += ",";
            }
        }

        SQL += ")";
        return this.exécutionUpdate(SQL);

    }

    //Fungsi eksekusi query update
    public String queryUpdate(String nomTable, String[] nomColonne, String[] contenuTableau, String état) {

        connexionDatabase();
        int i;
        SQL = "UPDATE " + nomTable + " SET ";

        for (i = 0; i <= nomColonne.length - 1; i++) {
            SQL += nomColonne[i] + "='" + contenuTableau[i] + "'";
            if (i < nomColonne.length - 1) {
                SQL += ",";
            }
        }

        SQL += " WHERE " + état;
        return this.exécutionUpdate(SQL);

    }

    //Fungsi eksekusi query delete
    public String queryDelete(String nomtable) {

        connexionDatabase();
        SQL = "DELETE FROM " + nomtable;
        return this.exécutionUpdate(SQL);

    }

    //Overload fungsi eksekusi query delete dengan where
    public String queryDelete(String nomTable, String état) {

        connexionDatabase();
        SQL = "DELETE FROM " + nomTable + " WHERE " + état;
        return this.exécutionUpdate(SQL);

    }
}