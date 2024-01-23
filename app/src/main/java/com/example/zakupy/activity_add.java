package com.example.zakupy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class activity_add extends AppCompatActivity {

    TextView nazwa_input, kategoria_input;
    CheckBox wybrany_input;
    Button dodaj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nazwa_input = findViewById(R.id.AddC_Nazwa);
        kategoria_input = findViewById(R.id.AddC_Kategoria);
        wybrany_input = findViewById(R.id.AddC_Wybrany);
        dodaj = findViewById(R.id.AddButton);
        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(activity_add.this);
                db.dodajProdukt(nazwa_input.getText().toString().trim(), kategoria_input.getText().toString().trim(),wybrany_input.isChecked());
                Intent intent = new Intent(activity_add.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}