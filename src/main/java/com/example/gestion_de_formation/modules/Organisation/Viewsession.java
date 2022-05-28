package com.example.gestion_de_formation.modules.Organisation;

import java.sql.Date;

public class Viewsession {
    private String Nomf;
    private String Nomd;
    private Date datedeb;
    private Date datefin;

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
    public String getNomf() {
        return Nomf;
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
    public Viewsession(){
        
    }

}
