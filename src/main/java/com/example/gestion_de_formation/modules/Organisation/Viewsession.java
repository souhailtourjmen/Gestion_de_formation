package com.example.gestion_de_formation.modules.Organisation;

import java.sql.Date;

public class Viewsession {
    private String id;
    private String Nomf;
    private String Nomd;
    private Date datedeb;
    private Date datefin;
    private String db;
    private String df;

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public void setDb(String db) {
        this.db = db;
    }
    public void setDf(String df) {
        this.df = df;
    }
    public void setNomf(String nomf) {
        Nomf = nomf;
    }
    public void setNomd(String nomd) {
        Nomd = nomd;
    }
    public void setDatedeb(Date datedeb) {
        this.datedeb = datedeb;
    }
    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }
    public String getNomd() {
        return Nomd;
    }
    public Date getDatedeb() {
        return datedeb;
    }
    public Date getDatefin() {
        return datefin;
    }
    public String getDb() {
        return db;
    }
    public String getDf() {
        return df;
    }
    public Viewsession(){
        
    }

    public String getNomf() {
        return this.Nomf;
    }
}
