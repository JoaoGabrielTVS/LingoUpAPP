from fastapi import FastAPI, HTTPException
from fastapi.middleware.cors import CORSMiddleware

import news_extrator
import Agentic_Resume
import agentic_questions
import agentic_checker

from models import (
    NoticiaResponse, ResumoRequest, ResumoResponse,
    PerguntasRequest, PerguntasResponse,
    AvaliacaoRequest, AvaliacaoResponse
)

app = FastAPI(title="Agentic News Quiz API")

# Permite que seu app (mobile/web) acesse a API de outro domínio
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # em produção, troque por domínios específicos
    allow_methods=["*"],
    allow_headers=["*"],
)

#quando o usuario ele so retorna nao recebe nada
@app.get("/noticia", response_model=NoticiaResponse)
def obter_noticia():
    site = "https://www.bbc.com/news"
    noticia = news_extrator.buscar_noticia_aleatoria(site)
    if not noticia:
        raise HTTPException(status_code=404, detail="Nenhuma notícia encontrada.")
    return NoticiaResponse(
        titulo=noticia["titulo"],
        url=noticia["url"],
        texto=noticia["texto"]
    )


@app.post("/resumo", response_model=ResumoResponse)
def gerar_resumo(req: ResumoRequest):
    try:
        resumo = Agentic_Resume.resume_agentic(req.texto)
        return ResumoResponse(resumo=resumo)
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))


@app.post("/perguntas", response_model=PerguntasResponse)#post usado quando a função vai receber um input do usuário, nesse caso o resumo da notícia
def gerar_perguntas(req: PerguntasRequest):
    try:
        perguntas = agentic_questions.create_question(req.resumo)
        return PerguntasResponse(perguntas=perguntas)
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))


@app.post("/avaliar", response_model=AvaliacaoResponse)
def avaliar_resposta(req: AvaliacaoRequest):
    try:
        avaliacao = agentic_checker.checker(req.resumo, req.perguntas, req.resposta)
        return AvaliacaoResponse(avaliacao=avaliacao)
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))