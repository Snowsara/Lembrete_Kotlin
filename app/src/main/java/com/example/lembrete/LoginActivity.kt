package com.example.lembrete

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private val prefsName = "arquivo"
    private val chaveLembrete = "lembrete_salvo"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nome = intent.getStringExtra("NOME_USUARIO")
        val sobrenome = intent.getStringExtra("SOBRENOME_USUARIO")
        val idade = intent.getStringExtra("IDADE_USUARIO")
        val textoBoasVindas = findViewById<TextView>(R.id.txtBoasVindas)
        textoBoasVindas.text = "Olá, $nome $sobrenome ($idade anos) "

        val editLembrete = findViewById<EditText>(R.id.editLembrete)
        val btnSalvar = findViewById<Button>(R.id.buttonSalvar)
        val btnApagar = findViewById<Button>(R.id.buttonDeletar)
        val textLembreteSalvo = findViewById<TextView>(R.id.textLembreteSalvo)

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


