package com.example.lembrete
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editNome = findViewById<EditText>(R.id.editNome)
        val editSobrenome = findViewById<EditText>(R.id.editSobrenome)
        val editIdade = findViewById<EditText>(R.id.editIdade)
        val btnAvancar = findViewById<Button>(R.id.btnAvancar)

        btnAvancar.setOnClickListener {
            val nome = editNome.text.toString()
            val sobrenome = editSobrenome.text.toString()
            val idade = editIdade.text.toString()

            btnAvancar.setOnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("NOME_USUARIO", nome)
                intent.putExtra("SOBRENOME_USUARIO", sobrenome)
                intent.putExtra("IDADE_USUARIO", idade)
                startActivity(intent)
            }
        }
    }
}
