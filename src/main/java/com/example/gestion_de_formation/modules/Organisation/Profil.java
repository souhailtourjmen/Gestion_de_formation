package com.example.gestion_de_formation.modules.Organisation;

public class Profil {
    private int id ;
    private String libelle;

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

    public Profil(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }
}
