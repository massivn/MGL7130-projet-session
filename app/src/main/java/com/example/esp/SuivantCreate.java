package com.example.esp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SuivantCreate extends AppCompatActivity {

    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suivant_create);

        username = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");

        EditText nomUtilisateur2Text = findViewById(R.id.nomUtilisateur2Text);
        EditText email2Text = findViewById(R.id.emailText);
        EditText motDePasse2Text = findViewById(R.id.motDePasse2Text);
        EditText confirmerMotDePasse2Text = findViewById(R.id.confirmerMotDePasse2Text);
        Button creer2Text1 = findViewById(R.id.creer2Text1);

        creer2Text1.setOnClickListener(v -> {
            String nomUtilisateur = nomUtilisateur2Text.getText().toString().trim();
            String email = email2Text.getText().toString().trim();
            String motDePasse = motDePasse2Text.getText().toString().trim();
            String confirmerMotDePasse = confirmerMotDePasse2Text.getText().toString().trim();

            if (validateInput(nomUtilisateur, email, motDePasse, confirmerMotDePasse)) {
                createAccount(username, password, nomUtilisateur, email, motDePasse);
            }
        });
    }

    private boolean validateInput(String nomUtilisateur, String email, String motDePasse, String confirmerMotDePasse) {
        return true;
    }

    private void createAccount(String username, String password, String nomUtilisateur, String email, String motDePasse) {
        String url = "http://localhost/api/actions/createAcount.php";

        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);


            String postData = "username=" + URLEncoder.encode(username, "UTF-8") +
                    "&password=" + URLEncoder.encode(password, "UTF-8") +
                    "&nomUtilisateur=" + URLEncoder.encode(nomUtilisateur, "UTF-8") +
                    "&email=" + URLEncoder.encode(email, "UTF-8") +
                    "&motDePasse=" + URLEncoder.encode(motDePasse, "UTF-8");

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = postData.getBytes("UTF-8");
                os.write(input, 0, input.length);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }

                Log.d("Server Response", response.toString());
            }

            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
