package com.example.zakupy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

import androidx.annotation.NonNull;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Zakupy.db";
    public static final int DATABASE_VERSION = 1;
    public static final String N_PRODUKTY = "PRODUKTY";
    public static final String C_ID = "ID";
    public static final String C_NAZWA = "Nazwa";
    public static final String C_KATEGORIA = "Kategoria";
    public static final String C_WYBRANY = "Wybrany";

    public DatabaseHelper(@NonNull Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + N_PRODUKTY + " ("
                + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + C_NAZWA + " TEXT, "
                + C_KATEGORIA + " TEXT, "
                + C_WYBRANY + " INTEGER)";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + N_PRODUKTY);
        onCreate(db);
    }

    public void dodajProdukt(String Nazwa, String Kategoria, Boolean Wybrany) {
        SQLiteDatabase db = this.getWritableDatabase();
        Object[] dane = new Object[]{Nazwa, Kategoria, Wybrany ? 1 : 0};
        db.execSQL("INSERT INTO PRODUKTY (Nazwa, Kategoria, Wybrany) VALUES (?,?,?)",dane);
    }

    public void usunProdukt(String nazwa) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] dane = {nazwa};
        db.execSQL("DELETE FROM PRODUKTY WHERE NAZWA = ?", dane);
    }

    public void aktualizujProdukty(Object id, boolean Wybrany) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("wybrany", Wybrany ? 1 : 0);

        String Zapytanie = "id = ?";
        String[] arg = {String.valueOf(id)};

        db.update("Produkty", values, Zapytanie, arg);
    }

    public Cursor pobierzWszystko() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] kolumny = {C_ID, C_NAZWA, C_KATEGORIA, C_WYBRANY};

        return db.query(N_PRODUKTY, kolumny, null, null, null, null, null);
    }

    public Cursor pobierzWybrane() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] kolumny = {C_ID, C_NAZWA, C_KATEGORIA, C_WYBRANY};

        return db.query(N_PRODUKTY, kolumny, "wybrany = 1", null, null, null, null);
    }
}
