package com.example.efmf

import retrofit2.Call
import retrofit2.http.GET

data class Smartwatch(
    val id : Int,
    val name : String,
    val price : Double,
    val isWaterResistant : Boolean,
    val imageURL : String
)

interface ApiService {
    @GET("WatchAPI/readAll.php")
    fun getWatch():Call<List<Smartwatch>>
}