package com.example.esp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Connection;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SuivantCreate extends AppCompatActivity {
    private EditText telephone2Text;
    private EditText motDePasse2Text;
    private EditText adresseText;
    private EditText groupeSanguinText;
    private Button creer2Text1;

    private Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suivant_create);

        String nomUtilisateur = getIntent().getStringExtra("nomUtilisateur");
        String email = getIntent().getStringExtra("email");
        String motDePasse = getIntent().getStringExtra("motDePasse");
        connection = (Connection) getIntent().getSerializableExtra("connection");

        telephone2Text = findViewById(R.id.telephone2Text);
        motDePasse2Text = findViewById(R.id.motDePasse2Text);
        adresseText = findViewById(R.id.adresseText);
        groupeSanguinText = findViewById(R.id.groupesinguinText);
        creer2Text1 = findViewById(R.id.creer2Text1);

        creer2Text1.setOnClickListener(v -> {
            String telephone = telephone2Text.getText().toString().trim();
            String adresse = adresseText.getText().toString().trim();
            String groupe_sanguin = groupeSanguinText.getText().toString().trim();

            if (validateInput(telephone, adresse, motDePasse, groupe_sanguin)) {
                OkHttpClient client = new OkHttpClient();

                String url = "http://172.29.223.10/espoir/createAcount.php";

                RequestBody formBody = new FormBody.Builder()
                        .add("nom_utilisateur", nomUtilisateur)
                        .add("email", email)
                        .add("mot_de_passe", motDePasse)
                        .add("telephone", telephone)
                        .add("adresse", adresse)
                        .add("groupe_sanguin", groupe_sanguin)
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

                            SuivantCreate.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.d("Response", myResponse);
                                    // Passer à la nouvelle activité
                                    Intent authentefication = new Intent(getApplicationContext(), authentification.class);
                                    startActivity(authentefication);
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    private boolean validateInput(String telephone, String adresse, String motDePasse, String groupeSanguin) {
        String telephoneRegex = "^\\d{10}$";
        String groupeSanguinRegex = "^(A|B|AB|O)[+-]$";

        if (telephone.isEmpty() || !telephone.matches(telephoneRegex)) {
            Toast.makeText(this, "Le téléphone n'est pas valide", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (adresse.isEmpty()) {
            Toast.makeText(this, "L'adresse ne peut pas être vide", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (motDePasse.isEmpty()) {
            Toast.makeText(this, "Le mot de passe ne peut pas être vide", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (groupeSanguin.isEmpty() || !groupeSanguin.matches(groupeSanguinRegex)) {
            Toast.makeText(this, "Le groupe sanguin n'est pas valide", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
