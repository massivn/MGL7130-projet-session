package com.example.esp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class authentification extends AppCompatActivity {
    private EditText nuEditText;

    private EditText mpEditText;
    private Button connectBtn;
    private TextView createAccountBtn;
    private String nomutilisateur;
    private String motdepasse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);

        nuEditText = findViewById(R.id.nomUtilisateurLabelText);
        mpEditText = findViewById(R.id.motDePasseLabelText);
        connectBtn = findViewById(R.id.connecterText);

        connectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nomutilisateur = nuEditText.getText().toString();
                motdepasse = mpEditText.getText().toString();


            }
        });


createAccountBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent MainActivity = new Intent(getApplicationContext(), com.example.esp.MainActivity.class);
        startActivity(MainActivity);
    }
});

    }}