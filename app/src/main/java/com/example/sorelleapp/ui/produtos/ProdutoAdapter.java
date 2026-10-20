package com.example.sorelleapp.ui.produtos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sorelleapp.R;

import java.util.List;
import java.util.Locale;

import com.example.sorelleapp.model.Produto;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> {

    public interface OnAdicionarClickListener {
        void onAdicionar(Produto produto);
    }

    private final List<Produto> produtos;
    private final OnAdicionarClickListener listener;

    public ProdutoAdapter(List<Produto> produtos, OnAdicionarClickListener listener) {
        this.produtos = produtos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_produto, parent, false);
        return new ProdutoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {
        Produto produto = produtos.get(position);
        holder.ivProduto.setImageResource(produto.getImagem());

        holder.tvTag.setText(produto.getTag());
        holder.tvNome.setText(produto.getNome());
        holder.tvDescricao.setText(produto.getDescricao());
        holder.tvPreco.setText(String.format(new Locale("pt", "BR"), "R$ %.2f", produto.getPreco()));

        int arvores = produto.getArvores();
        holder.tvArvores.setText("+" + arvores + (arvores == 1 ? " árvore" : " árvores"));

        holder.btnAdicionar.setOnClickListener(v -> listener.onAdicionar(produto));
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    static class ProdutoViewHolder extends RecyclerView.ViewHolder {
        TextView tvTag, tvNome, tvDescricao, tvPreco, tvArvores;
        Button btnAdicionar;
        ImageView ivProduto;
        ProdutoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProduto = itemView.findViewById(R.id.ivProduto);
            tvTag = itemView.findViewById(R.id.tvTag);tvTag = itemView.findViewById(R.id.tvTag);
            tvNome = itemView.findViewById(R.id.tvNome);
            tvDescricao = itemView.findViewById(R.id.tvDescricao);
            tvPreco = itemView.findViewById(R.id.tvPreco);
            tvArvores = itemView.findViewById(R.id.tvArvores);
            btnAdicionar = itemView.findViewById(R.id.btnAdicionar);
        }
    }
}