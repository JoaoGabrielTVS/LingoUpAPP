package com.example.lingoup.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lingoup.data.RetrofitClient
import com.example.lingoup.model.ResumoRequest
import kotlinx.coroutines.launch

class ReadViewModel : ViewModel() {
    private val apiService = RetrofitClient.apiService


    var noticiaTexto by mutableStateOf("")
        private set
    var isLoading by mutableStateOf(true)

    fun carregarNoticia() {
        viewModelScope.launch {
            isLoading = true
            try {
                val noticia = apiService.obterNoticia()
                val resposta = apiService.obterResumo(ResumoRequest(noticia.texto))
                noticiaTexto = resposta.resumo
            } catch (e: Exception) {
                noticiaTexto = "Erro ao carregar: ${e.message}"
            }finally{
                isLoading = false
            }
        }
    }
}