from langchain_openai import ChatOpenAI
from langchain_core.prompts import ChatPromptTemplate
from dotenv import load_dotenv
import os

def checker(summary, questions, answers):
    # Carrega as variáveis de ambiente
    load_dotenv()
    api_key = os.getenv("OPENROUTER_API_KEY")
    if not api_key:
        raise ValueError("OPENROUTER_API_KEY não encontrada no arquivo .env")

    # Inicializa o modelo via OpenRouter (Claude 3.5 Sonnet)
    llm = ChatOpenAI(
        model="deepseek/deepseek-chat", # Nome correto no OpenRouter
        temperature=0.2,
        openai_api_key=api_key,
        base_url="https://openrouter.ai/api/v1"
    )

    # Cria o prompt
    prompt = ChatPromptTemplate.from_messages([
        ("system", (
            "You are an educational evaluator.\n"
            "Your task is to evaluate a student's answer.\n"
            "Use only the provided summary.\n"
            "Do not use external knowledge.\n"
            "Classify the answer as correct, partially correct, or incorrect.\n"
            "Give a score from 0 to 10.\n"
            "Explain the feedback briefly."
        )),
        ("user", "Summary:\n{summary}\n\nQuestion:\n{questions}\n\nStudent answer:\n{answers}")
    ])

    chain = prompt | llm
    result = chain.invoke({
        "summary": summary,
        "questions": questions,
        "answers": answers
    })
    return result.content
