package com.example.gestion_de_formation.modules.Organisation;



import java.sql.Date;
import java.util.ArrayList;

public class Session {
    private int id ;
    private Formation formation;
    private Date date_debut ;
    private Date date_fin;
    public Session(){
        
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }


}
