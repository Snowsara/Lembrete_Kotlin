package com.example.lembrete

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val PREFS_NAME = "arquivo"
    private val CHAVE_LEMBRETE = "lembrete_salvo"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editLembrete = findViewById<EditText>(R.id.editLembrete)
        val btnSalvar = findViewById<Button>(R.id.buttonSalvar)
        val btnApagar = findViewById<Button>(R.id.buttonDeletar)
        val textLembreteSalvo = findViewById<TextView>(R.id.textLembreteSalvo)

        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        // Mostrar lembrete salvo ao abrir o app
        textLembreteSalvo.text = prefs.getString(CHAVE_LEMBRETE, "Nenhum lembrete salvo")

        btnSalvar.setOnClickListener {
            val texto = editLembrete.text.toString()
            prefs.edit().putString(CHAVE_LEMBRETE, texto).apply()
            textLembreteSalvo.text = texto
        }

        btnApagar.setOnClickListener {
            prefs.edit().remove(CHAVE_LEMBRETE).apply()
            textLembreteSalvo.text = "Nenhum lembrete salvo"
            editLembrete.setText("")
        }
    }
}
