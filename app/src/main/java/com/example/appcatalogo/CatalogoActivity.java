package com.example.appcatalogo;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CatalogoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LivroAdapter livroAdapter;
    private DBHelper dbHelper;
    private ArrayList<Livro> listaLivros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        recyclerView = findViewById(R.id.recyclerViewLivros);
        dbHelper = new DBHelper(this);
        listaLivros = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        livroAdapter = new LivroAdapter(listaLivros, new LivroAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Livro livro) {
                // Navegar para a tela de avaliação
                Intent intent = new Intent(CatalogoActivity.this, AvaliacaoActivity.class);
                intent.putExtra("idLivro", livro.getId());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(livroAdapter);

        // Carregar os livros do banco de dados
        carregarLivros();
    }

    private void carregarLivros() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM livros";
        Cursor cursor = db.rawQuery(query, null);

        listaLivros.clear();
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String titulo = cursor.getString(cursor.getColumnIndex("titulo"));
                @SuppressLint("Range") String autor = cursor.getString(cursor.getColumnIndex("autor"));
                @SuppressLint("Range") String categoria = cursor.getString(cursor.getColumnIndex("categoria"));
                listaLivros.add(new Livro(id, titulo, autor, categoria));
            } while (cursor.moveToNext());
        }

        livroAdapter.notifyDataSetChanged();
        cursor.close();
    }
}



