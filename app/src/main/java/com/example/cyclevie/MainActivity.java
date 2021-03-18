package com.example.cyclevie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    /**
     * Exécuté chaque fois que l'utilisateur clique sur l'icône de l'application pour une première fois.
     *
     * La fonction onCreate() est suivie d'un onStart().
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnQuitter = (Button) findViewById(R.id.btnQuitter);
        btnQuitter.setOnClickListener(btnQuitterOnClickListener);

        Button btnEnvoyer = (Button) findViewById(R.id.btnEnvoyer);
        btnEnvoyer.setOnClickListener(btnEnvoyerOnClickListener);
        Button btnActiver = (Button) findViewById(R.id.btnAct2);
        btnActiver.setOnClickListener(new View.OnClickListener() {
          //  private static final String EXTRA_MESSAGE = "com.example.cyclevie";

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SecondActivity.class);
                intent.putExtra("as",getTxtValeur());
                startActivity(intent);

            }
        });
        btnQuitter.setOnClickListener(btnQuitterOnClickListener);
        popUp("onCreate()");

        /**Question c
         * ///////////////////////////////////////
         */
       /*if (savedInstanceState != null){
            String valeur = savedInstanceState.getString("cleee");
            setTxTValeur(valeur);
        }*/






    }
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("cleee", getTxtValeur());
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String valeur = savedInstanceState.getString("cleee");
        setTxTValeur(valeur);
    }
    /** =============================================================
     * Exécuté que l'activité arrêtée via un "stop" redémarre.
     *
     * La fonction onRestart() est suivie de la fonction onStart().
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        popUp("onRestart()");
    }
    /** ==============================================================
     * Exécuté lorsque l'activité devient visible à l'utilisateur.
     *
     * La fonction onStart() est suivie de la fonction onResume().
     */
    @Override
    protected void onStart() {
        super.onStart();
        popUp("onStart()");
    }
    /** ==============================================================
     * Exécutée à chaque passage en premier plan de l'activité.
     * Ou bien, si l'activité passe à nouveau en premier
     * (si une autre activité était passée en premier plan entre temps).
     *
     * La fonction onResume() est suivie de l'exécution de l'activité.
     */

   /* public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String valeur = savedInstanceState.getString("clé");
    }*/
    @Override
    protected void onResume() {
        super.onResume();
        popUp("onResume()");
        SharedPreferences settings=getSharedPreferences("cycle_vie_prefs",Context.MODE_PRIVATE);
        setTxTValeur(settings.getString("valeur",""));
    }
    /** =============================================================
     * La fonction onPause() est suivie :
     * - d'un onResume() si l'activité passe à nouveau en premier plan
     * - d'un onStop() si elle devient invisible à l'utilisateur
     *
     * L'exécution de la fonction onPause() doit être rapide,
     * car la prochaine activité ne démarrera pas tant que l'exécution
     * de la fonction onPause() n'est pas terminée.
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            popUp("onPause, l'utilisateur à demandé la fermeture via un finish()");
        } else {
            popUp("onPause, l'utilisateur n'a pas demandé la fermeture via un finish()");
        }
        SharedPreferences settings = getSharedPreferences("cycle_vie_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("valeur",getTxtValeur());
        editor.commit();

    }
    /** ==============================================================
     * La fonction onStop() est exécutée :
     * - lorsque l'activité n'est plus en premier plan
     * - ou bien lorsque l'activité va être détruite
     *
     * Cette fonction est suivie :
     * - de la fonction onRestart() si l'activité passe à nouveau en premier plan
     * - de la fonction onDestroy() lorsque l'activité se termine
     * ou bien lorsque le système décide de l'arrêter
     Tous les évènements concernant le cycle de vie sont sollicités. On y a ajouté une invocation de la méthode popUp(
     message) décrite ci-dessus. Elle fait apparaître une fenêtre sur l’écran contenant le message pendant quelques
     secondes au moyen de la classe prédéfinie Toast.
     Pour que le bouton Quitter soit actif au clic, il faut, dans la méthode onCreate(), retrouver ce bouton à partir de son
     id (déclaré dans le layout) et lui affecter un écouteur de clic, instance de la classe prédéfinie
     View.OnClickListener, dans laquelle on surcharge la méthode onClick() par le code à exécuter si on clique sur le
     bouton.
     Exécution du programme
     Note : certains temps d’attente peuvent énerver. Vous êtes prévenus.
     Tout d’abord, vérifiez que vous avez accès aux « options développeur » en recherchant la page Paramètres>
     Options pour le développeur. Si la page n’apparaît pas, il faut la rendre visible (Android est cachottier !). Pour
     cela, allez dans la page Paramètres>A propos du téléphone et appuyez 7 fois sur le « Numéro de build ». Vérifiez
     maintenant que vous avez accès aux options développeur.
     Via l’émulateur
     Créer et installer un appareil Android virtuel via Tools/AVD Manager.
     Normalement, un Nexus 5 API 23 a été créé par défaut. Si ce n’est pas le cas, cliquez sur « Create Virtual
     Device » et choisissez un appareil de votre choix.
     Puis configurer le système via Run/Edit Configurations. Cliquer dans Target Device sur Emulator et choisissez
     l’appareil Android virtuel que vous avez installé.
     Enfin cliquer sur Run/Run ou le triangle vert pointe à droite.
     Via votre appareil Android
     Branchez votre appareil à une prise USB. Normalement AndroidStudio a installé le bridge ADB qui permet une
     communication entre tout appareil Android et AndroidStudio. Si votre appareil n’est pas reconnu, il faut installer
     */
    @Override
    protected void onStop() {
        super.onStop();
        popUp("onStop()");
    }
    /** =============================================================
     * Cette fonction est exécutée lorsque l'activité se termine ou bien lorsque
     * le système décide de l'arrêter.
     *
     * La fonction onCreate() devra à nouveau être exécutée pour obtenir à nouveau l'activité.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        popUp("onDestroy()");
    }
    //=================================================================
    View.OnClickListener btnQuitterOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    View.OnClickListener btnEnvoyerOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            popUp("valeur saisie = " + getTxtValeur());
        }

    };
    public String getTxtValeur() {
        EditText zoneValeur = (EditText) findViewById(R.id.EditTxtValeur);
        return zoneValeur.getText().toString();
    }
    public void setTxTValeur(String valeur) {
        EditText zoneValeur = (EditText) findViewById(R.id.EditTxtValeur);
        zoneValeur.setText(valeur);
    }


    public void popUp(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}