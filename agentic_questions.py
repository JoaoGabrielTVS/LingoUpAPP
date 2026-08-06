from langchain_openai import ChatOpenAI
from langchain_core.prompts import ChatPromptTemplate
from dotenv import load_dotenv
import os

def create_question(text):
    # Carrega as variáveis de ambiente
    load_dotenv()
    api_key = os.getenv("OPENROUTER_API_KEY")
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
            "You are a teacher.\n"
            "Your task is to create questions based only on the provided text.\n"
            "Create five questions.\n"
            "All questions must be answerable using information explicitly contained in the text.\n"
            "Do not use external knowledge or assumptions.\n"
            "The answers to all questions must be found within the text.\n"
            "Create questions that test understanding of the content, including facts, causes, and consequences.\n"
            "Avoid questions that require opinions or information not mentioned in the text.\n"
            "At the end of each question, add the character '/'.\n"
        )),
        ("user", "{input}")
    ])

    chain = prompt | llm
    result = chain.invoke({"input": text})
    return result.content
