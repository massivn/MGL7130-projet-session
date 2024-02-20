package com.example.esp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.json.JSONObject;

public class authentification extends AppCompatActivity {
    private EditText nuEditText;
    private EditText mpEditText;
    private Button connectBtn;
    private TextView createAccountBtn;
    private TextView resetPasswordTextView; // Ajout du TextView pour réinitialiser le mot de passe
    private String nomutilisateur;
    private String motdepasse;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);

        nuEditText = findViewById(R.id.nomUtilisateurLabelText);
        mpEditText = findViewById(R.id.motDePasseLabelText);
        connectBtn = findViewById(R.id.connecterText);
        createAccountBtn = findViewById(R.id.creerUnCompteText);
        resetPasswordTextView = findViewById(R.id.resetPasswordTextView); // Assurez-vous d'avoir l'ID correct

        connectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nomutilisateur = nuEditText.getText().toString();
                motdepasse = mpEditText.getText().toString();

                OkHttpClient client = new OkHttpClient();

                String url = "http://172.29.223.10/espoir/createAcount2.php";

                RequestBody formBody = new FormBody.Builder()
                        .add("nom_utilisateur", nomutilisateur)
                        .add("mot_de_passe", motdepasse)
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
                            String responseData = response.body().string();
                            try {
                                JSONObject json = new JSONObject(responseData);
                                String status = json.getString("status");
                                if (status.equals("success")) {
                                    // Si le serveur renvoie un statut de succès, vous pouvez rediriger l'utilisateur vers une autre activité
                                    Intent MainActivity = new Intent(getApplicationContext(), com.example.esp.MainActivity.class);
                                    startActivity(MainActivity);
                                } else {
                                    // Si le serveur renvoie un statut d'erreur, afficher un message d'erreur à l'utilisateur
                                    runOnUiThread(new Runnable() {
                                        public void run() {
                                            Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            // Gérer l'échec de la requête ici
                            System.out.println("Request failed: " + response.code());
                        }
                    }
                });
            }
        });

        // Logique du bouton de création de compte
        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainActivity = new Intent(getApplicationContext(), com.example.esp.MainActivity.class);
                startActivity(MainActivity);
            }
        });

        // Logique du TextView pour réinitialiser le mot de passe
        resetPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lorsque l'utilisateur clique sur le TextView, redirigez-le vers ResetPasswordActivity
                Intent sendPinIntent = new Intent(getApplicationContext(), ResetPasswordActivity.class);
                startActivity(sendPinIntent);
            }
        });
    }
}
