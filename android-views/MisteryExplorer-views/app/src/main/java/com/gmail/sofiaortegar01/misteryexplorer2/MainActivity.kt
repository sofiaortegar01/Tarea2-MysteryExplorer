package com.gmail.sofiaortegar01.misteryexplorer2



import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnOvnis = findViewById<Button>(R.id.btnOvnis)
        val btnLugares = findViewById<Button>(R.id.btnLugares)
        val btnReportar = findViewById<Button>(R.id.btnReportar)

        // Navegar a Países enviando la categoría seleccionada por Intent
        btnOvnis.setOnClickListener {
            val intent = Intent(this, CountryActivity::class.java)
            intent.putExtra("CATEGORY_ID", "ovnis")
            startActivity(intent)
        }

        btnLugares.setOnClickListener {
            val intent = Intent(this, CountryActivity::class.java)
            intent.putExtra("CATEGORY_ID", "lugares")
            startActivity(intent)
        }

        // Navegar al formulario de reporte
        btnReportar.setOnClickListener {
            val intent = Intent(this, ReportActivity::class.java)
            startActivity(intent)
        }
    }
}