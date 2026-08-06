package com.example.lingoup.data


import com.example.lingoup.model.* // Importa os seus modelos que você acabou de criar
import retrofit2.http.*

interface LingoUpApi {
        @GET("noticia")
        suspend fun obterNoticia(): NoticiaResponse

        @POST("resumo") // Faltava isso
        suspend fun obterResumo(@Body request: ResumoRequest): ResumoResponse

        @POST("perguntas") // Faltava isso
        suspend fun obterPerguntas(@Body request: PerguntasRequest): PerguntasResponse

        @POST("avaliar") // Faltava isso (de acordo com seu api.py)
        suspend fun obterAvaliacao(@Body request: AvaliacaoRequest): AvaliacaoResponse





}