from langchain_openai import ChatOpenAI
from langchain_core.prompts import ChatPromptTemplate
from dotenv import load_dotenv


import os


def resume_agentic(text):
     # Carrega as variáveis de ambiente
        load_dotenv()
        api_key = os.getenv("OPENROUTER_API_KEY_DOIS")
        if not api_key:
            raise ValueError("OPENROUTER_API_KEY não encontrada no arquivo .env")
    
        # Inicializa o modelo via OpenRouter (DeepSeek)
        llm = ChatOpenAI(
            model="deepseek/deepseek-chat", # Nome padrão no OpenRouter
            temperature=0,
            openai_api_key=api_key,
            base_url="https://openrouter.ai/api/v1"
        )
    
        # Cria o prompt
        prompt = ChatPromptTemplate.from_messages([
            ("system", (
                 "Your role is to summarize the text you receive.\n"
                    "Your summary will be used by another teacher to create questions.\n"
                    "[IMPORTANT] Your summary MUST be between 700 and 900 characters long.\n"
                    "[IMPORTANT] Your summary MUST be writing in Running text.\n"
            )),
            ("user", "{input}")
        ])
    
        chain = prompt | llm
        result = chain.invoke({"input": text})
        return result.content
    










