package com.complaint.entities;


public class Complaint {
    private String kundeEmail;
    private String klage;

    public Complaint(String kundeEmail, String klage) {
        this.kundeEmail = kundeEmail;
        this.klage = klage;
    }
    public Complaint(){}

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
