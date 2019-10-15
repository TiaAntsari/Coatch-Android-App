package com.dreamisreal.ravo.coach.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProfilTest {

    private Profil profil = new Profil(67,165,35,0);
    //result img faut
    //private float imgF = (float)32.4;
    //result img correct
    private float imgV = (float)32.2;

    //message
    private String message = "femme, trop élevé";

    @Test
    public void getImg() {
        assertEquals(imgV, profil.getImg(), (float)0.1);
    }

    @Test
    public void getMessage() {
        assertEquals(message,profil.getMessage());
    }
}