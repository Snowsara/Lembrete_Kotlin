package com.example.lembrete

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lembrete.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val prefs = getSharedPreferences("cadastro", MODE_PRIVATE)

        val emailSalvo = prefs.getString("EMAIL_USUARIO", "")
        val senhaSalvo = prefs.getString("SENHA_USUARIO", "")

        val editEmail = binding.editEmail
        val editSenha = binding.editSenha

        val btnLogin = binding.btnLogin
        val btnCadastro =  binding.btnCadastro

        btnLogin.setOnClickListener{
            val email = editEmail.text.toString()
            val senha = editSenha.text.toString()

            if (email == emailSalvo && senha == senhaSalvo){
                val nomeSalvo = prefs.getString("NOME_USUARIO", "Usuário")

                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("NOME_USUARIO", nomeSalvo)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, "Email ou senha incorretos!", Toast.LENGTH_SHORT).show()
            }
        }

        btnCadastro.setOnClickListener{
            startActivity(Intent(this, CadastroActivity::class.java))
            finish()
        }
    }

}