package com.example.lembrete

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lembrete.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val editEmail = binding.editEmail
        val editSenha = binding.editSenha

        val btnLogin = binding.btnLogin
        val btnCadastro =  binding.btnCadastro

        btnLogin.setOnClickListener{
            val email = editEmail.text.toString()
            val senha = editSenha.text.toString()

            if (email.isNotEmpty() && senha.isNotEmpty()){
                auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "Login feito com sucesso!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this,"Erro no login: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }else{
                Toast.makeText(this,"Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }

        btnCadastro.setOnClickListener{
            startActivity(Intent(this, CadastroActivity::class.java))
            finish()
        }
    }

}