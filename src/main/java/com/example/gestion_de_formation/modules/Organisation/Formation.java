package com.example.gestion_de_formation.modules.Organisation;

public class Formation {
    private int Id;
    private String Titre;
    private String Type_formation ;
    private int nb_session ;
    private int Duree;
    private Domaine domaine;
    private float budget;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String titre) {
        Titre = titre;
    }

    public String getType_formation() {
        return Type_formation;
    }

    public void setType_formation(String type_formation) {
        Type_formation = type_formation;
    }

    public int getNb_session() {
        return nb_session;
    }

    public void setNb_session(int nb_session) {
        this.nb_session = nb_session;
    }

    public int getDuree() {
        return Duree;
    }

    public void setDuree(int duree) {
        Duree = duree;
    }

    public Domaine getDomaine() {
        return domaine;
    }

    public void setDomaine(Domaine domaine) {
        this.domaine = domaine;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public Formation(int id, String titre, String type_formation, int nb_session, int duree, Domaine domaine, float budget) {
        this.Id = id;
        this.Titre = titre;
        this.Type_formation = type_formation;
        this.nb_session = nb_session;
        this.Duree = duree;
        this.domaine = domaine;
        this.budget = budget;
    }
}
