package com.example.lembrete
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lembrete.databinding.ActivityCadastroBinding
import com.google.firebase.auth.FirebaseAuth

class CadastroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()

        val editEmail1 = binding.editEmail1
        val editSenha1 = binding.editSenha1
        val btnCadastrar = binding.btnCadastrar

        btnCadastrar.setOnClickListener {
            val email = editEmail1.text.toString()
            val senha = editSenha1.text.toString()

            if (email.isNotEmpty() && senha.isNotEmpty()){
                auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "Cadastro feito com sucesso!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this,"Erro: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}
