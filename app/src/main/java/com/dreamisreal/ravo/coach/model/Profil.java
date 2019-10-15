package com.dreamisreal.ravo.coach.model;

import java.io.Serializable;
import java.util.Date;

public class Profil implements Serializable {

    public static final Integer minFemme = 15; //maigre si en dessous
    public static final Integer maxFemme = 16; //gras si au dessus
    public static final Integer minHomme = 10; //maigre si en dessous
    public static final Integer maxHomme = 25; //gras si au dessus

    // propriétés
    private Date dateMesure;
    private Integer poids;
    private Integer taille;
    private Integer age;
    private Integer sex; //0 pour Femme et 1 pour Homme
    private float img;
    private String message ;

    public Profil(Date dateMesure,Integer poids, Integer taille, Integer age, Integer sex) {
        this.dateMesure = dateMesure;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sex = sex;
        this.calculIMG();
        this.resultIMG();
    }

    public Integer getPoids() {
        return poids;
    }

    public Integer getTaille() {
        return taille;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getSex() {
        return sex;
    }

    public float getImg() {
        return img;
    }

    public String getMessage() {
        return message;
    }

    public Date getDateMesure() {
        return dateMesure;
    }

    private void calculIMG() {
        float tailleM = ((float)taille)/100;
        this.img = (float) ((1.2*poids / (tailleM*tailleM)) + (0.23*age) - (10.83*sex) - 5.4);
    }

    private void resultIMG() {
        Integer min;
        Integer max;

        if(sex == 0) { //Femme
            min = minFemme;
            max = maxFemme;
        }
        else { //Homme
            min = minHomme;
            max = maxHomme;
        }
        //message correspondant
        message = "normal";
        if (img<(float)min){
            message = "trop maigre";
        }
        else {
            if(img>(float)max) {
                message = "trop de graisse";
            }
        }
    }

}
