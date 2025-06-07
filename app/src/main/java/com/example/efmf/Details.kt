// Details.kt
package com.example.efmf

import android.os.Bundle
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class Details : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imageView = findViewById<ImageView>(R.id.imageS)
        val nameView = findViewById<TextView>(R.id.name)
        val priceView = findViewById<TextView>(R.id.price)
        val checkBox = findViewById<CheckBox>(R.id.check)

        val name = intent.getStringExtra("name")
        val price = intent.getDoubleExtra("price", 0.0)
        val imageURL = intent.getStringExtra("imageURL")
        val isWaterResistant = intent.getBooleanExtra("water", false)

        nameView.text = name
        priceView.text = "$price Dh"
        checkBox.isChecked = isWaterResistant

        Glide.with(this)
            .load(imageURL)
            .into(imageView)
    }
}
