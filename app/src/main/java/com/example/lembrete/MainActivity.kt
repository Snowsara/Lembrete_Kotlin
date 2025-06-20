package com.example.lembrete

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lembrete.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private val prefsName = "arquivo"
    private val chaveLembrete = "lembrete_salvo"
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = FirebaseAuth.getInstance().currentUser
        if (user == null){
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

       val auth = FirebaseAuth.getInstance()


        val textoBoasVindas = binding.txtBoasVindas
        val email = user?.email
        textoBoasVindas.text = "Olá, $email"

        val editLembrete = binding.editLembrete
        val btnSalvar = binding.buttonSalvar
        val btnDeletar = binding.buttonDeletar
        val textLembreteSalvo = binding.textLembreteSalvo
        val btnSair = binding.buttonSair

        val prefs = getSharedPreferences(prefsName, Context.MODE_PRIVATE)


        // Mostrar lembrete salvo ao abrir o app
        textLembreteSalvo.text = prefs.getString(chaveLembrete, "Nenhum lembrete salvo")

        btnSalvar.setOnClickListener {
            val texto = editLembrete.text.toString()
            prefs.edit().putString(chaveLembrete, texto).apply()
            textLembreteSalvo.text = texto
        }

        btnDeletar.setOnClickListener {
            prefs.edit().remove(chaveLembrete).apply()
            textLembreteSalvo.text = "Nenhum lembrete salvo"
            editLembrete.setText("")
        }

        btnSair.setOnClickListener{
            auth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }


    }
}


