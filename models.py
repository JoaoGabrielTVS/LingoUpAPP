from pydantic import BaseModel

class NoticiaResponse(BaseModel):
    titulo: str
    url: str
    texto: str

class ResumoRequest(BaseModel):
    texto: str

class ResumoResponse(BaseModel):
    resumo: str

class PerguntasRequest(BaseModel):
    resumo: str

class PerguntasResponse(BaseModel):
    perguntas: str

class AvaliacaoRequest(BaseModel):
    resumo: str
    perguntas: str
    resposta: str

class AvaliacaoResponse(BaseModel):
    avaliacao: str