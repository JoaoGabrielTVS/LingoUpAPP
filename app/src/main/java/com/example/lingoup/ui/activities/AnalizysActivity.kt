package com.example.lingoup.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.lingoup.ui.screens.AnalizysScreen

class AnalizysActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        var resumo = intent.getStringExtra("resumo") ?: ""
        var questoes = intent.getStringExtra("perguntas") ?: ""
        var respostas = intent.getStringExtra("resposta") ?: ""
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   AnalizysScreen(resumo = resumo, questoes = questoes, respostas  = respostas)
                }
            }



        }
    }

}
