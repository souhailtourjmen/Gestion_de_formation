package com.example.gestion_de_formation.modules.Sign_in;

public class Utilisatuer {
    private int code ;
    private String login ;
    private String password ;

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Utilisatuer(int code, String login, String password) {
        this.setCode(code);
        this.setLogin(login);
        this.setPassword(password);
    }

}
