package com.example.gestion_de_formation.modules.Organisation;



import java.sql.Date;
import java.util.ArrayList;

public class Session {
    private int id ;
    private Formateur formateur;
    private Organisme organisme;
    private Formation formation;
    private ArrayList<Participant> participants ;
    private Pays lieu ;
    private Date date_debut ;
    private Date date_fin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Formateur getFormateur() {
        return formateur;
    }

    public void setFormateur(Formateur formateur) {
        this.formateur = formateur;
    }

    public Organisme getOrganisme() {
        return organisme;
    }

    public void setOrganisme(Organisme organisme) {
        this.organisme = organisme;
    }

    public Pays getLieu() {
        return lieu;
    }

    public void setLieu(Pays lieu) {
        this.lieu = lieu;
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
