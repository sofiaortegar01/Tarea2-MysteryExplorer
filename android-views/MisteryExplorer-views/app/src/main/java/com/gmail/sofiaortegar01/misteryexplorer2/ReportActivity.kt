package com.gmail.sofiaortegar01.misteryexplorer2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ReportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        val etName = findViewById<EditText>(R.id.etName)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        btnSubmit.setOnClickListener {
            val name = etName.text.toString()
            if (name.isBlank()) {
                etName.error = "Campo obligatorio"
            } else {
                Toast.makeText(this, "Reporte guardado con éxito", Toast.LENGTH_SHORT).show()
                finish() // Regresa a la pantalla anterior
            }
        }
    }
}