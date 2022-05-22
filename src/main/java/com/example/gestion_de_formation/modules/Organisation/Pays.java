package com.example.gestion_de_formation.modules.Organisation;

public class Pays {
    private int id ;
    private String  libelle;
    private String adresse;

    public Pays(int id, String libelle, String adresse) {
        this.id = id;
        this.libelle = libelle;
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
