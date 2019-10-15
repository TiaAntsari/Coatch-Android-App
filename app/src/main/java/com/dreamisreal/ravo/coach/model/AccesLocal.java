package com.dreamisreal.ravo.coach.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dreamisreal.ravo.coach.outils.MySQLiteOpenHelper;

import java.util.Date;

public class AccesLocal {
    // propriétés
    private String nomBase = "dbCoach.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesDB;
    private SQLiteDatabase db;

    /**
     * Constructeur
     * @param context
     */
    public AccesLocal(Context context){
        accesDB = new MySQLiteOpenHelper(context, nomBase, null, versionBase);
    }

    /**
     * Ajout d'un profil dans la base de donnée
     * @param profil
     */
    public void ajout(Profil profil) {
        db = accesDB.getWritableDatabase();
        String req = "insert into profil (datemesure, poids, taille, age, sex) values "
                + "(\"" + profil.getDateMesure()
                + "\"," + profil.getPoids()
                + "," + profil.getTaille()
                + "," + profil.getAge()
                + "," + profil.getSex() + ")";

        db.execSQL(req);
    }

    /**
     * Recuperation du dernier profil du DB
     * @return
     */
    public Profil recupDernier() {
        db = accesDB.getReadableDatabase();
        Profil profil = null;
        String req = "select * from profil";
        Cursor cursor = db.rawQuery(req, null);
        cursor.moveToLast();

        if (!cursor.isAfterLast()){
            Date date = new Date();
            Integer poids = cursor.getInt(1);
            Integer taille = cursor.getInt(2);
            Integer age = cursor.getInt(3);
            Integer sex = cursor.getInt(4);
            profil = new Profil(date, poids, taille, age, sex);

        }
        cursor.close();
        return profil;

    }
}
