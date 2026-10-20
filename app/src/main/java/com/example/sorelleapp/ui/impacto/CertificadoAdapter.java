package com.example.sorelleapp.ui.impacto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sorelleapp.R;

import java.util.List;

import com.example.sorelleapp.model.Certificado;

public class CertificadoAdapter extends RecyclerView.Adapter<CertificadoAdapter.CertificadoViewHolder> {

    private final List<Certificado> certificados;

    public CertificadoAdapter(List<Certificado> certificados) {
        this.certificados = certificados;
    }

    @NonNull
    @Override
    public CertificadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_certificado, parent, false);
        return new CertificadoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CertificadoViewHolder holder, int position) {
        Certificado certificado = certificados.get(position);

        holder.tvDetalhe.setText(certificado.getData() + " · " + certificado.getNome()
                + " · +" + certificado.getArvores()
                + (certificado.getArvores() == 1 ? " árvore" : " árvores"));

        holder.btnDiploma.setOnClickListener(v ->
                Toast.makeText(v.getContext(), "Download do diploma em breve",
                        Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return certificados.size();
    }

    static class CertificadoViewHolder extends RecyclerView.ViewHolder {
        TextView tvDetalhe;
        Button btnDiploma;

        CertificadoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDetalhe = itemView.findViewById(R.id.tvDetalhe);
            btnDiploma = itemView.findViewById(R.id.btnDiploma);
        }
    }
}