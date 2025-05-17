package com.example.lembrete
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lembrete.databinding.ActivityCadastroBinding

class CadastroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val prefs = getSharedPreferences("cadastro", Context.MODE_PRIVATE)


        val editNome = binding.editNome
        val editEmail = binding.editEmail
        val editSenha = binding.editSenha
        val btnCadastrar = binding.btnCadastrar

        btnCadastrar.setOnClickListener {
            val nome = editNome.text.toString()
            val email = editEmail.text.toString()
            val senha = editSenha.text.toString()


            prefs.edit().apply {
                putString("EMAIL_USUARIO", email)
                putString("SENHA_USUARIO", senha)
                putString("NOME_USUARIO", nome)
                apply()
            }

            Toast.makeText(this, "Cadastro feito com sucesso!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()

        }
    }
}
