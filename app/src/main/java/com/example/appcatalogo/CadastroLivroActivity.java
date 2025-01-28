package com.example.appcatalogo;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CadastroLivroActivity extends AppCompatActivity {

    private EditText edtTitulo, edtAutor, edtCategoria;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_livro);

        edtTitulo = findViewById(R.id.edtTitulo);
        edtAutor = findViewById(R.id.edtAutor);
        edtCategoria = findViewById(R.id.edtCategoria);
        dbHelper = new DBHelper(this);

        findViewById(R.id.btnCadastrarLivro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarLivro();
            }
        });
    }

    private void cadastrarLivro() {
        String titulo = edtTitulo.getText().toString();
        String autor = edtAutor.getText().toString();
        String categoria = edtCategoria.getText().toString();

        if (titulo.isEmpty() || autor.isEmpty() || categoria.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Inserir no banco de dados
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "INSERT INTO livros (titulo, autor, categoria) VALUES ('" +
                titulo + "', '" + autor + "', '" + categoria + "')";
        db.execSQL(query);

        Toast.makeText(this, "Livro cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

        edtTitulo.setText("");
        edtAutor.setText("");
        edtCategoria.setText("");
    }
}


