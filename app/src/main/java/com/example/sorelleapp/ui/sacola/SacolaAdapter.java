package com.example.sorelleapp.ui.sacola;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sorelleapp.R;
import com.example.sorelleapp.data.Sacola;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.example.sorelleapp.model.Produto;

public class SacolaAdapter extends RecyclerView.Adapter<SacolaAdapter.SacolaViewHolder> {

    public interface OnSacolaMudouListener {
        void onMudou();
    }

    private final List<Produto> produtos = new ArrayList<>();
    private final OnSacolaMudouListener listener;

    public SacolaAdapter(OnSacolaMudouListener listener) {
        this.listener = listener;
        atualizar();
    }

    public void atualizar() {
        produtos.clear();
        produtos.addAll(Sacola.getInstancia().getProdutos());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SacolaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sacola, parent, false);
        return new SacolaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SacolaViewHolder holder, int position) {
        Produto produto = produtos.get(position);
        int quantidade = Sacola.getInstancia().getQuantidade(produto);
        double subtotal = produto.getPreco() * quantidade;

        holder.tvNome.setText(produto.getNome());
        holder.tvQuantidade.setText(quantidade + (quantidade == 1 ? " unidade" : " unidades"));
        holder.tvPreco.setText(String.format(new Locale("pt", "BR"), "R$ %.2f", subtotal));

        holder.btnRemover.setOnClickListener(v -> {
            Sacola.getInstancia().remover(produto);
            atualizar();
            listener.onMudou();
        });
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    static class SacolaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNome, tvQuantidade, tvPreco;
        Button btnRemover;

        SacolaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNome = itemView.findViewById(R.id.tvNome);
            tvQuantidade = itemView.findViewById(R.id.tvQuantidade);
            tvPreco = itemView.findViewById(R.id.tvPreco);
            btnRemover = itemView.findViewById(R.id.btnRemover);
        }
    }
}