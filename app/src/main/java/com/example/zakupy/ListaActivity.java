package com.example.zakupy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListaActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private ProduktAdapter produktAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        RecyclerView recyclerView = findViewById(R.id.ListarecyclerView);
        Button buttonWroc = findViewById(R.id.buttonWroc);

        db = new DatabaseHelper(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        produktAdapter = new ProduktAdapter(this, db.pobierzWybrane());
        recyclerView.setAdapter(produktAdapter);

        buttonWroc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        produktAdapter.updateData(db.pobierzWybrane());
    }

    @Override
    protected void onDestroy() {
        produktAdapter.closeCursor();
        db.close();
        super.onDestroy();
    }
}