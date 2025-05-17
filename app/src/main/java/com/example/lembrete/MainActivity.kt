package com.example.lembrete

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lembrete.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val prefsName = "arquivo"
    private val chaveLembrete = "lembrete_salvo"
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val nome = intent.getStringExtra("NOME_USUARIO")
        val email = intent.getStringExtra("EMAIL_USUARIO")
        val senha = intent.getStringExtra("SENHA_USUARIO")
        val textoBoasVindas = binding.txtBoasVindas
        textoBoasVindas.text = "Olá, $nome"

        val editLembrete = binding.editLembrete
        val btnSalvar = binding.buttonSalvar
        val btnApagar = binding.buttonDeletar
        val textLembreteSalvo = binding.textLembreteSalvo

        val prefs = getSharedPreferences(prefsName, Context.MODE_PRIVATE)


        // Mostrar lembrete salvo ao abrir o app
        textLembreteSalvo.text = prefs.getString(chaveLembrete, "Nenhum lembrete salvo")

        btnSalvar.setOnClickListener {
            val texto = editLembrete.text.toString()
            prefs.edit().putString(chaveLembrete, texto).apply()
            textLembreteSalvo.text = texto
        }

        btnApagar.setOnClickListener {
            prefs.edit().remove(chaveLembrete).apply()
            textLembreteSalvo.text = "Nenhum lembrete salvo"
            editLembrete.setText("")
        }
    }
}


