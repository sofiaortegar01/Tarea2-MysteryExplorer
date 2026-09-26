package com.gmail.sofiaortegar01.misteryexplorer2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class CountryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)

        val categoryId = intent.getStringExtra("CATEGORY_ID") ?: "ovnis"

        val btnMexico = findViewById<Button>(R.id.btnMexico)
        val btnUsa = findViewById<Button>(R.id.btnUsa)

        btnMexico.setOnClickListener {
            val intent = Intent(this, CasesActivity::class.java)
            intent.putExtra("CATEGORY_ID", categoryId)
            intent.putExtra("COUNTRY_ID", "mx")
            startActivity(intent)
        }

        btnUsa.setOnClickListener {
            val intent = Intent(this, CasesActivity::class.java)
            intent.putExtra("CATEGORY_ID", categoryId)
            intent.putExtra("COUNTRY_ID", "us")
            startActivity(intent)
        }
    }
}