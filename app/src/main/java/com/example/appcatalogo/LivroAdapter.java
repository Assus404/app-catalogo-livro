package com.example.appcatalogo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class LivroAdapter extends RecyclerView.Adapter<LivroAdapter.LivroViewHolder> {

    private List<Livro> livros;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Livro livro);
    }

    public LivroAdapter(List<Livro> livros, OnItemClickListener listener) {
        this.livros = livros;
        this.listener = listener;
    }

    @Override
    public LivroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new LivroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LivroViewHolder holder, int position) {
        final Livro livro = livros.get(position);
        holder.titulo.setText(livro.getTitulo());
        holder.autor.setText(livro.getAutor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(livro);
            }
        });
    }

    @Override
    public int getItemCount() {
        return livros.size();
    }

    public class LivroViewHolder extends RecyclerView.ViewHolder {
        TextView titulo, autor;

        public LivroViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(android.R.id.text1);
            autor = itemView.findViewById(android.R.id.text2);
        }
    }
}


