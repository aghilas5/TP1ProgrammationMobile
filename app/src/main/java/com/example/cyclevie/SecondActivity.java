package com.example.cyclevie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        /**Question B
         * ///////////////////////////////////////
         */
        Intent intent = getIntent();
        String v= "" ;
        if (intent != null) {
            v=intent.getStringExtra("as") ;
        }
        TextView afficher=(TextView) findViewById(R.id.TextView02);
        afficher.setText(v);











    }
   /* public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String valeur = savedInstanceState.getString("clé");
        TextView afficher=(TextView) findViewById(R.id.TextView02);
        afficher.setText(valeur);
    }*/

    @Override
    protected void onRestart() {
        super.onRestart();
        popUp("onRestart() activity2");
    }
    public void popUp(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        popUp("onStart() activity2");
    }
    @Override
    protected void onResume() {
        super.onResume();
        popUp("onResume() activity2");

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            popUp("onPause, l'utilisateur à demandé la fermeture via un finish()");
        } else {
            popUp("onPause, l'utilisateur n'a pas demandé la fermeture via un finish()");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        popUp("onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        popUp("onDestroy()");
    }



}