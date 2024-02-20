package com.example.esp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private EditText nomUtilisateurEditText;
    private EditText emailEditText;
    private EditText motDePasseEditText;
    private EditText confirmerMotDePasseEditText;
    private Button suivantBtn;
    private String nomUtilisateur;
    private String email;
    private String motDePasse;
    private String confirmerMotDePasse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nomUtilisateurEditText = findViewById(R.id.nomUtilisateur1Text);
        emailEditText = findViewById(R.id.email1Text);
        motDePasseEditText = findViewById(R.id.motDePasse1Text);
        confirmerMotDePasseEditText = findViewById(R.id.confirmerMotDePasse1Text);
        suivantBtn = findViewById(R.id.creerText1);

        suivantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nomUtilisateur = nomUtilisateurEditText.getText().toString();
                email = emailEditText.getText().toString();
                motDePasse = motDePasseEditText.getText().toString();
                confirmerMotDePasse = confirmerMotDePasseEditText.getText().toString();


                if (validateInput(nomUtilisateur, email, motDePasse, confirmerMotDePasse)) {
                    insertUser();
                }
            }
        });
    }

    private boolean validateInput(String nomUtilisateur, String email, String motDePasse, String confirmerMotDePasse) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";

        if (nomUtilisateur.isEmpty()) {
            Toast.makeText(MainActivity.this, "Veuillez entrer un nom d'utilisateur.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (email.isEmpty() || !email.matches(emailRegex)) {
            Toast.makeText(MainActivity.this, "Veuillez entrer un email valide.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (motDePasse.isEmpty() || !motDePasse.matches(passwordRegex)) {
            Toast.makeText(MainActivity.this, "Le mot de passe doit contenir au moins un chiffre, une minuscule, une majuscule, et avoir une longueur d'au moins 8 caractères.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!motDePasse.equals(confirmerMotDePasse)) {
            Toast.makeText(MainActivity.this, "Les mots de passe ne correspondent pas.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void insertUser() {
        OkHttpClient client = new OkHttpClient();

        String url = "http://172.29.223.10/espoir/createAcount.php";

        RequestBody formBody = new FormBody.Builder()
                .add("nom_utilisateur", nomUtilisateur)
                .add("email", email)
                .add("mot_de_passe", motDePasse)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("Response", myResponse);
                            // Passer à la deuxième activité
                            Intent SuivantCreate = new Intent(getApplicationContext(), SuivantCreate.class);
                            SuivantCreate.putExtra("nomUtilisateur", nomUtilisateur);
                            SuivantCreate.putExtra("email", email);
                            SuivantCreate.putExtra("motDePasse", motDePasse);
                            startActivity(SuivantCreate);
                        }
                    });
                }
            }
        });
    }
}
