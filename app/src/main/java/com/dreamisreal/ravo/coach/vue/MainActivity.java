package com.dreamisreal.ravo.coach.vue;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dreamisreal.ravo.coach.R;
import com.dreamisreal.ravo.coach.controlleur.Controle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    //propriété
    private EditText txtPoids;
    private EditText txtTaille;
    private EditText txtAge;
    private RadioButton rdHomme;
    private RadioButton rdFemme;
    private TextView lblIMG;
    private ImageView imgSmiley;
    private Controle controle;

    /**
     * Initialisation de lien avec les objets graphiques
     */
    private void init(){
        txtPoids = (EditText) findViewById(R.id.txtPoids);
        txtTaille = (EditText) findViewById(R.id.txtTaille);
        txtAge = (EditText) findViewById(R.id.txtAge);
        rdHomme = (RadioButton) findViewById(R.id.rdHomme);
        rdFemme = (RadioButton) findViewById(R.id.rdFemme);
        lblIMG = (TextView) findViewById(R.id.lblIMG);
        imgSmiley = (ImageView) findViewById(R.id.imgSmiley);
        this.controle = Controle.getInstance(this);

        ecouteCalcul();

        recupProfil();
    }

    /**
     * Ecoute evenement sur bouton calcul
     */
    private void ecouteCalcul() {
        ((Button)findViewById(R.id.btnCalc)).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                // Toast.makeText(MainActivity.this,"test", Toast.LENGTH_SHORT).show();
                // Log.d("message", "******************* click sur boutton click *********************");
                Integer poids = 0;
                Integer taille = 0;
                Integer age = 0;
                Integer sex = 0;

                // Récuperation des données saisies
                try {
                    poids = Integer.parseInt(txtPoids.getText().toString());
                    taille = Integer.parseInt(txtTaille.getText().toString());
                    age = Integer.parseInt(txtAge.getText().toString());
                }catch (Exception e){

                }

                if (rdHomme.isChecked()){
                    sex = 1;
                }

                // Controle de données saisies
                if (poids==0 | taille==0 | age==0){
                    Toast.makeText(MainActivity.this,"Saisie Incorrecte", Toast.LENGTH_SHORT).show();
                }else {
                    afficheResult(poids, taille, age, sex);
                }
            }
        });
    }

    /**
     * Affichage de l'IMG, du message et de l'image
     * @param poids
     * @param taille
     * @param age
     * @param sex
     */
    public void afficheResult(Integer poids, Integer taille, Integer age, Integer sex){
        // Creation du profil et récupération des informations
        this.controle.creerProfil(poids, taille, age, sex, this);
        float img = controle.getIMG();
        String message = this.controle.getMessage();
        // Affichage
        if (message == "normal"){
            imgSmiley.setImageResource(R.drawable.normal);
            lblIMG.setTextColor(Color.GREEN);
        }else {
            if (message == "trop faible"){
                imgSmiley.setImageResource(R.drawable.maigre);
                lblIMG.setTextColor(Color.RED);
            }else {
                imgSmiley.setImageResource(R.drawable.graisse);
                lblIMG.setTextColor(Color.RED);
            }
        }
        lblIMG.setText(String.format("%.01f",img) + " : IMG " + message);

    }

    /**
     * Recuperation du profile s'il a été bien serialisé
     */
    private void recupProfil(){
        if (controle.getPoids() != null) {
            txtPoids.setText(controle.getPoids().toString());
            txtTaille.setText(controle.getTaille().toString());
            txtAge.setText(controle.getAge().toString());
            rdFemme.setChecked(true);
            if (controle.getSex() == 1) {
                rdHomme.setChecked(true);
            }

            // simule le clic sur le bouton calcul
            ((Button)findViewById(R.id.btnCalc)).performClick();
        }

    }
}
