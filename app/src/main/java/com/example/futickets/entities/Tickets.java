package com.example.futickets.entities;

import android.media.Image;

import java.io.Serializable;
import java.util.Date;

public class Tickets implements Serializable {

    Date date;
    Image logo1;
    String equipo1;
    Image logo2;
    String equipo2;
    boolean disponible;

    public Tickets(Date date, Image logo1, String equipo1, Image logo2, String equipo2, boolean disponible) {
        this.date = date;
        this.logo1 = logo1;
        this.equipo1 = equipo1;
        this.logo2 = logo2;
        this.equipo2 = equipo2;
        this.disponible = disponible;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Image getLogo1() {
        return logo1;
    }

    public void setLogo1(Image logo1) {
        this.logo1 = logo1;
    }

    public String getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(String equipo1) {
        this.equipo1 = equipo1;
    }

    public Image getLogo2() {
        return logo2;
    }

    public void setLogo2(Image logo2) {
        this.logo2 = logo2;
    }

    public String getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(String equipo2) {
        this.equipo2 = equipo2;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
