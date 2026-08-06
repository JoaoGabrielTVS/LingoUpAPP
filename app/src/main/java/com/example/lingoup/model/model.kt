package com.example.lingoup.model



data class NoticiaResponse(
    val titulo: String,
    val url:String,
    val texto:String
)

data class ResumoRequest(val texto: String)
data class ResumoResponse(val resumo: String)

data class PerguntasRequest(val resumo: String)
data class PerguntasResponse(val perguntas: String)

data class AvaliacaoRequest(
    val resumo: String,
    val perguntas: String,
    val resposta: String
)
data class AvaliacaoResponse(val avaliacao: String)
