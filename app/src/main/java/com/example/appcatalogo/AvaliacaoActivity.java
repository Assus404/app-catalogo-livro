package com.example.appcatalogo;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AvaliacaoActivity extends AppCompatActivity {

    private EditText edtNota, edtComentario;
    private DBHelper dbHelper;
    private int idLivro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao);

        edtNota = findViewById(R.id.edtNota);
        edtComentario = findViewById(R.id.edtComentario);
        dbHelper = new DBHelper(this);

        // Recuperar o id do livro
        idLivro = getIntent().getIntExtra("idLivro", -1);

        findViewById(R.id.btnSalvarAvaliacao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarAvaliacao();
            }
        });
    }

    private void salvarAvaliacao() {
        int nota = Integer.parseInt(edtNota.getText().toString());
        String comentario = edtComentario.getText().toString();

        if (nota <= 0 || comentario.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Inserir avaliação no banco de dados
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "INSERT INTO avaliacoes (nota, comentario, id_livro) VALUES (" +
                nota + ", '" + comentario + "', " + idLivro + ")";
        db.execSQL(query);

        Toast.makeText(this, "Avaliação salva com sucesso!", Toast.LENGTH_SHORT).show();
    }
}


