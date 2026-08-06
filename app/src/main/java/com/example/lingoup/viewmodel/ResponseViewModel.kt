package com.example.lingoup.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lingoup.data.RetrofitClient
import com.example.lingoup.model.PerguntasRequest
import com.example.lingoup.model.ResumoRequest
import kotlinx.coroutines.launch
import com.example.lingoup.viewmodel.varrer

class ResponseViewModel: ViewModel() {
    private val apiService = RetrofitClient.apiService
    // Estado que a tela vai observar.
    // Começa com "Carregando..." e muda quando a API responde.
   var perguntasstate by mutableStateOf("Carregando...")
    private set
    var pergunta1 by mutableStateOf("")
    var pergunta2 by mutableStateOf("")
    var pergunta3 by mutableStateOf("")
    var pergunta4 by mutableStateOf("")
    var pergunta5 by mutableStateOf("")

    private var resumosalvo = ""
    fun iniciarComResumo(resumo:String) {
        this.resumosalvo = resumo
        viewModelScope.launch {
            try {
                val perguntas  = apiService.obterPerguntas(PerguntasRequest(resumosalvo))
                perguntasstate = perguntas.perguntas
                var auxiliar = varrer(perguntasstate)
                pergunta1 = auxiliar[0]
                pergunta2 = auxiliar[1]
                pergunta3 = auxiliar[2]
                pergunta4 = auxiliar[3]
                pergunta5 = auxiliar[4]

            } catch (e: Exception) {
                perguntasstate = "Erro ao carregar: ${e.message}"
            }
        }
    }
}