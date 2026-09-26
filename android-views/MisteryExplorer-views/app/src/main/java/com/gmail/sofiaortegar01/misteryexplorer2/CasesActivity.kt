package com.gmail.sofiaortegar01.misteryexplorer2

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class CasesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cases)

        val categoryId = intent.getStringExtra("CATEGORY_ID")
        val countryId = intent.getStringExtra("COUNTRY_ID")

        val btnDetail = findViewById<Button>(R.id.btnDetail)
        btnDetail.setOnClickListener {
            // Aquí puedes conectar el detalle posteriormente
        }
    }
}