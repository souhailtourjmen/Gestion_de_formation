package com.example.gestion_de_formation.DB;

public class Parameter {

        public static String IPHOST = "127.0.0.1";
        public static String USERNAME_DB = "root";
        public static String PASSWORD_DB = "";//"root"
        public static int PORT = 11111;
        public static String Name_DB ="Formation";

    public static String HOST_DB = "jdbc:mysql://" + IPHOST + ":"+PORT+"/"+Name_DB;

}

