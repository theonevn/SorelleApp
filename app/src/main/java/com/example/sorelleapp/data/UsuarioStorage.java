package com.example.sorelleapp.data;

import android.content.Context;
import android.content.SharedPreferences;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UsuarioStorage {

    private static final String PREFS = "sorelle_usuario";
    private static final String CHAVE_EMAIL = "email";
    private static final String CHAVE_SENHA = "senha_hash";
    private static final String CHAVE_LOGADO = "logado";

    private static SharedPreferences prefs(Context context) {
        return context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public static void salvarConta(Context context, String email, String senha) {
        prefs(context).edit()
                .putString(CHAVE_EMAIL, email.toLowerCase())
                .putString(CHAVE_SENHA, hash(senha))
                .apply();
    }

    public static boolean existeConta(Context context) {
        return prefs(context).contains(CHAVE_EMAIL);
    }

    public static boolean validar(Context context, String email, String senha) {
        String emailSalvo = prefs(context).getString(CHAVE_EMAIL, "");
        String senhaSalva = prefs(context).getString(CHAVE_SENHA, "");
        return emailSalvo.equals(email.toLowerCase()) && senhaSalva.equals(hash(senha));
    }

    public static void setLogado(Context context, boolean logado) {
        prefs(context).edit().putBoolean(CHAVE_LOGADO, logado).apply();
    }

    public static boolean isLogado(Context context) {
        return prefs(context).getBoolean(CHAVE_LOGADO, false);
    }

    private static String hash(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(senha.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }
}