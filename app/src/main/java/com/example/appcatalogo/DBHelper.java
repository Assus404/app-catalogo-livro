package com.example.appcatalogo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "catalogo.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_LIVROS =
            "CREATE TABLE livros (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "titulo TEXT NOT NULL," +
                    "autor TEXT NOT NULL," +
                    "categoria TEXT);";

    private static final String CREATE_TABLE_AVALIACOES =
            "CREATE TABLE avaliacoes (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nota INTEGER NOT NULL," +
                    "comentario TEXT," +
                    "id_livro INTEGER," +
                    "FOREIGN KEY (id_livro) REFERENCES livros(id));";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_LIVROS);
        db.execSQL(CREATE_TABLE_AVALIACOES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS livros");
        db.execSQL("DROP TABLE IF EXISTS avaliacoes");
        onCreate(db);
    }
}

