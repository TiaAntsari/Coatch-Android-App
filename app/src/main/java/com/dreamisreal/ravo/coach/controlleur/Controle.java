package com.dreamisreal.ravo.coach.controlleur;

import android.content.Context;

import com.dreamisreal.ravo.coach.model.AccesLocal;
import com.dreamisreal.ravo.coach.model.Profil;

import com.dreamisreal.ravo.coach.outils.*;

import java.util.Date;

public class Controle {

    private static Controle instance = null;
    private static Profil profil;
    private static String nomFich = "saveProfil";
    private static AccesLocal accesLocal;

    /**
     * Constructeur privé
     */
    private Controle(){
        super();
    }

    //singleton creation d'une seule instance

    /**
     * Creation d'instance
     * @return instance
     */
    public static final Controle getInstance(Context context){
        if (Controle.instance == null) {
            Controle.instance = new Controle();
            accesLocal = new AccesLocal(context);
            profil = accesLocal.recupDernier();
            // recupSerialize(context);
        }
        return Controle.instance;
    }

    /**
     * creation de profil
     * @param poids
     * @param taille en centimetre
     * @param age
     * @param sex 0 pour femme et 1 pour homme
     */
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sex, Context context){
        profil = new Profil(new Date(),poids,taille,age,sex);
        accesLocal.ajout(profil);
        // Serializer.serialize(nomFich, profil, context);
    }

    /**
     * recuperation img
     * @return img
     */
    public float getIMG(){
        return profil.getImg();
    }

    /**
     * recuperation message
     * @return message
     */
    public String getMessage(){
        return profil.getMessage();
    }

    /**
     * Recuperation des objets sérialisés
     * @param context
     */
    private static void recupSerialize(Context context) {
        profil = (Profil) Serializer.deSerialize(nomFich, context);
    }

    public Integer getPoids() {
        if (profil == null){
            return null;
        }
        else {
            return profil.getPoids();
        }
    }

    public Integer getTaille() {
        if (profil == null) {
            return null;
        }else {
            return profil.getTaille();
        }
    }

    public Integer getAge() {
        if (profil == null) {
            return null;
        }else {
            return profil.getAge();
        }
    }

    public Integer getSex() {
        if (profil == null) {
            return null;
        }else {
            return profil.getSex();
        }
    }
}
