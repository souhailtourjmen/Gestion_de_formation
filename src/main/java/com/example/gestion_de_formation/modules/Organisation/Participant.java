package com.example.gestion_de_formation.modules.Organisation;

public class Participant {
    private int id;
    private String nom ;
    private String prenom ;
    private Profil profil;
    private String email ;
    private String tel ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }





    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Participant(int id, String nom, String prenom, Profil profil, String email, String tel) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.profil = profil;

        this.email = email;
        this.tel = tel;
    }

    public Participant() {
    }
}
