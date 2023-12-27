package com.complaint.entities;


public class Klage {
    private String kundeEmail;
    private String klage;

    public Klage(String kundeEmail, String klage) {
        this.kundeEmail = kundeEmail;
        this.klage = klage;
    }
    public Klage(){}

    public String getKundeEmail() {
        return kundeEmail;
    }

    public void setKundeEmail(String kundeEmail) {
        this.kundeEmail = kundeEmail;
    }

    public String getKlage() {
        return klage;
    }

    public void setKlage(String klage) {
        this.klage = klage;
    }
}
