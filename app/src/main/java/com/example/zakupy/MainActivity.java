package com.example.zakupy;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;
    private ProduktAdapter produktAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        Button buttonAdd = findViewById(R.id.buttonAdd);
        Button buttonDelete = findViewById(R.id.buttonDelete);
        Button buttonCompile = findViewById(R.id.buttonCompile);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        db = new DatabaseHelper(this);

        produktAdapter = new ProduktAdapter(this, db.pobierzWszystko());
        recyclerView.setAdapter(produktAdapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, activity_add.class);
                startActivity(intent);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, activity_delete.class);
                startActivity(intent);

            }
        });

        buttonCompile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, ListaActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        produktAdapter.updateData(db.pobierzWszystko());
    }

    @Override
    protected void onDestroy() {
        produktAdapter.closeCursor();
        db.close();
        super.onDestroy();
    }

}