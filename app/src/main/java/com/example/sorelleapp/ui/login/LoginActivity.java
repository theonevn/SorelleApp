package com.example.sorelleapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sorelleapp.ui.home.MainActivity;
import com.example.sorelleapp.R;
import com.example.sorelleapp.data.UsuarioStorage;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private boolean modoCadastro = false;

    private TextInputLayout tilEmail, tilSenha;
    private TextInputEditText etEmail, etSenha;
    private TextView tvTitulo, tvSubtitulo, tvAlternar;
    private Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (UsuarioStorage.isLogado(this)) {
            abrirHome();
            return;
        }

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        tilEmail = findViewById(R.id.tilEmail);
        tilSenha = findViewById(R.id.tilSenha);
        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        tvTitulo = findViewById(R.id.tvTitulo);
        tvSubtitulo = findViewById(R.id.tvSubtitulo);
        tvAlternar = findViewById(R.id.tvAlternar);
        btnEntrar = findViewById(R.id.btnEntrar);
        TextView tvEsqueci = findViewById(R.id.tvEsqueci);
        Button btnGoogle = findViewById(R.id.btnGoogle);

        atualizarModo();

        btnEntrar.setOnClickListener(v -> tentarEntrar());

        tvAlternar.setOnClickListener(v -> {
            modoCadastro = !modoCadastro;
            tilEmail.setError(null);
            tilSenha.setError(null);
            atualizarModo();
        });

        tvEsqueci.setOnClickListener(v ->
                Toast.makeText(this, "Recuperação de senha em breve", Toast.LENGTH_SHORT).show());

        btnGoogle.setOnClickListener(v ->
                Toast.makeText(this, "Login com Google em breve", Toast.LENGTH_SHORT).show());
    }

    private void atualizarModo() {
        if (modoCadastro) {
            tvTitulo.setText("Criar conta");
            tvSubtitulo.setText("Cadastre-se para acompanhar seu impacto na floresta.");
            btnEntrar.setText("Criar conta");
            tvAlternar.setText("Já tem conta? Entrar");
        } else {
            tvTitulo.setText("Bem-vindo de volta");
            tvSubtitulo.setText("Entre para acompanhar seu impacto na floresta.");
            btnEntrar.setText("Entrar");
            tvAlternar.setText("Novo por aqui? Criar conta");
        }
    }

    private void tentarEntrar() {
        String email = etEmail.getText() == null ? "" : etEmail.getText().toString().trim();
        String senha = etSenha.getText() == null ? "" : etSenha.getText().toString();

        tilEmail.setError(null);
        tilSenha.setError(null);

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.setError("E-mail inválido");
            return;
        }
        if (senha.length() < 6) {
            tilSenha.setError("Mínimo de 6 caracteres");
            return;
        }

        if (modoCadastro) {
            UsuarioStorage.salvarConta(this, email, senha);
            UsuarioStorage.setLogado(this, true);
            abrirHome();
        } else {
            if (!UsuarioStorage.existeConta(this)) {
                tilEmail.setError("Nenhuma conta criada ainda. Toque em Criar conta.");
                return;
            }
            if (UsuarioStorage.validar(this, email, senha)) {
                UsuarioStorage.setLogado(this, true);
                abrirHome();
            } else {
                tilSenha.setError("E-mail ou senha incorretos");
            }
        }
    }

    private void abrirHome() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}