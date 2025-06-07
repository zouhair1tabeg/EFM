package com.example.efmf

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.collections.emptyList as emptyList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        getWatch()
    }
    private fun getWatch(){
        val watchList = findViewById<ListView>(R.id.lv)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://apiyes.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        apiService.getWatch().enqueue(object : Callback<List<Smartwatch>> {
            override fun onResponse(call: Call<List<Smartwatch>>, response: Response<List<Smartwatch>>) {
                if (response.isSuccessful) {
                    val watches = response.body() ?: emptyList()
                    val adapterItem = adapter(this@MainActivity, watches)
                    watchList.adapter = adapterItem

                    watchList.setOnItemClickListener { parent, view, position, id ->
                        val selectedWatch = watches[position]

                        val intent = Intent(this@MainActivity, Details::class.java).apply {
                            putExtra("name", selectedWatch.name)
                            putExtra("price", selectedWatch.price)
                            putExtra("imageURL", selectedWatch.imageURL)
                            putExtra("water", selectedWatch.isWaterResistant)
                        }
                        startActivity(intent)
                    }

                }
            }

            override fun onFailure(call: Call<List<Smartwatch>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Erreur", Toast.LENGTH_SHORT).show()
            }
        })
    }
}