package com.example.lingoup.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.lingoup.data.RetrofitClient
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lingoup.model.AvaliacaoRequest
import kotlinx.coroutines.launch

class AnalizysViewModel: ViewModel(){
    private val apiservice = RetrofitClient.apiService

    var analise by mutableStateOf("Carregando")
    private set

    fun iniciarComAnalise(resumo:String ,  questoes:String , respostas:String){
        viewModelScope.launch{
            try{
                val analises = apiservice.obterAvaliacao(AvaliacaoRequest(resumo, questoes,respostas))
                analise = analises.avaliacao
            }catch(e: Exception){

            }
        }

    }






}