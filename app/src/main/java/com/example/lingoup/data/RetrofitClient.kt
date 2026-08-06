package com.example.lingoup.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitClient{
    // 10.0.2.2 é o IP que o emulador usa para acessar o 'localhost' do seu computador.
    // Como você está usando um celular real, usamos o IP da sua rede: 192.168.1.80
    private const val BASE_URL = "http://192.168.1.80:8000/"

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    val apiService: LingoUpApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            // O GsonConverter converte o JSON do Python em objetos Kotlin automaticamente
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LingoUpApi::class.java)
    }
    
}