package com.example.lingoup.data


import com.example.lingoup.model.*
import retrofit2.http.*

interface LingoUpApi {
        @GET("noticia")
        suspend fun obterNoticia(): NoticiaResponse

        @POST("resumo")
        suspend fun obterResumo(@Body request: ResumoRequest): ResumoResponse

        @POST("perguntas")
        suspend fun obterPerguntas(@Body request: PerguntasRequest): PerguntasResponse

        @POST("avaliar")
        suspend fun obterAvaliacao(@Body request: AvaliacaoRequest): AvaliacaoResponse





}