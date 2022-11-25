package com.example.juhasz_csenge_javafxrestclientdolgozat;

import com.google.gson.annotations.Expose;

public class Csillagaszat {
    private int id;
    @Expose
    private String Jelentkezok;
    @Expose
    private String Urallomas;
    @Expose
    private String email;
    @Expose
    private int Letszam;


    public Csillagaszat(int id, String jelentkezok, String urallomas, String email, int letszam) {
        this.id = id;
        this.Jelentkezok = jelentkezok;
        this.Urallomas = urallomas;
        this.email = email;
        this.Letszam = letszam;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJelentkezok() {
        return Jelentkezok;
    }

    public void setJelentkezok(String jelentkezok) {
        Jelentkezok = jelentkezok;
    }

    public String getUrallomas() {
        return Urallomas;
    }

    public void setUrallomas(String urallomas) {
        Urallomas = urallomas;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLetszam() {
        return Letszam;
    }

    public void setLetszam(int letszam) {
        Letszam = letszam;
    }
}

