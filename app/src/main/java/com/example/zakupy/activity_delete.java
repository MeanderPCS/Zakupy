package com.example.zakupy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class activity_delete extends AppCompatActivity {

    TextView nazwa_input;
    Button dodaj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        nazwa_input = findViewById(R.id.DeleteC_Nazwa);
        dodaj = findViewById(R.id.DeleteButton);
        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(activity_delete.this);
                db.usunProdukt(nazwa_input.getText().toString().trim());
                Intent intent = new Intent(activity_delete.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}