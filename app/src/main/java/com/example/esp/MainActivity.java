package com.example.esp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private EditText nomutilisateurEditText;
    private EditText emailEditText;
    private EditText motdepasseEditText;
    private EditText confirmermotdepasseEditText;
    private Button suivantBtn;
    private String nomutilisateur;
    private String email;
    private String motdepasse;
    private String confirmermotdepasse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Appeler la méthode pour établir la connexion
        Connection connection = DatabaseManager.connect();
        // Utilisez Log.d() pour afficher un message de débogage
        Log.d("MainActivity", "Message de débogage");
        nomutilisateurEditText = findViewById(R.id.nomUtilisateur1Text);
        emailEditText = findViewById(R.id.email1Text);
        motdepasseEditText = findViewById(R.id.motDePasse1Text);
        confirmermotdepasseEditText = findViewById(R.id.confirmerMotDePasse1Text);
        suivantBtn = findViewById(R.id.creerText1);

        suivantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nomutilisateur = nomutilisateurEditText.getText().toString();
                email = emailEditText.getText().toString();
                motdepasse = motdepasseEditText.getText().toString();
                confirmermotdepasse = confirmermotdepasseEditText.getText().toString();

  Intent SuivantCreate =new Intent(getApplicationContext(), com.example.esp.SuivantCreate.class);
  startActivity(SuivantCreate);
            }
        }

        );

}

}
