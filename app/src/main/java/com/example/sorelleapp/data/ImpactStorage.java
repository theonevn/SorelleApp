package com.example.sorelleapp.data;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.example.sorelleapp.model.Certificado;

public class ImpactStorage {

    private static final String PREFS = "sorelle_impacto";
    private static final String CHAVE_ARVORES = "arvores";
    private static final String CHAVE_CERTIFICADOS = "certificados";
    private static final int CO2_POR_ARVORE = 15;

    private static SharedPreferences prefs(Context context) {
        return context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public static int getArvores(Context context) {
        return prefs(context).getInt(CHAVE_ARVORES, 0);
    }

    public static int getCo2(Context context) {
        return getArvores(context) * CO2_POR_ARVORE;
    }

    public static String getNivel(Context context) {
        int arvores = getArvores(context);
        if (arvores >= 100) return "Guardião da Mata";
        if (arvores >= 50) return "Protetor da Floresta";
        if (arvores >= 10) return "Semeador";
        return "Semente";
    }

    public static void registrarCompra(Context context, String nome, int arvores) {
        String data = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR")).format(new Date());

        List<Certificado> lista = getCertificados(context);
        lista.add(0, new Certificado(nome, data, arvores));

        JSONArray array = new JSONArray();
        try {
            for (Certificado c : lista) {
                JSONObject obj = new JSONObject();
                obj.put("nome", c.getNome());
                obj.put("data", c.getData());
                obj.put("arvores", c.getArvores());
                array.put(obj);
            }
        } catch (JSONException e) {
            return;
        }

        prefs(context).edit()
                .putInt(CHAVE_ARVORES, getArvores(context) + arvores)
                .putString(CHAVE_CERTIFICADOS, array.toString())
                .apply();
    }

    public static List<Certificado> getCertificados(Context context) {
        List<Certificado> lista = new ArrayList<>();
        String json = prefs(context).getString(CHAVE_CERTIFICADOS, "[]");
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                lista.add(new Certificado(
                        obj.getString("nome"),
                        obj.getString("data"),
                        obj.getInt("arvores")));
            }
        } catch (JSONException e) {

        }
        return lista;
    }
}