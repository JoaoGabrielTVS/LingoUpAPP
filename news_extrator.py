import random
import json
import re
import requests
import trafilatura
from bs4 import BeautifulSoup
from urllib.parse import urljoin, urlparse

HEADERS = {
    "User-Agent": (
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
        "AppleWebKit/537.36 (KHTML, like Gecko) "
        "Chrome/137.0 Safari/537.36"
    ),
    "Accept-Language": "pt-BR,pt;q=0.9,en;q=0.8",
    "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
    "Referer": "https://www.google.com/",
}

# Padrão que identifica URLs de artigo de notícia da BBC.
# Ex: /news/articles/c1234567890o  ou  /news/uk-politics-12345678
PADRAO_ARTIGO_BBC = re.compile(r"/news/(articles/[a-z0-9]+|[a-z-]+-\d{6,})", re.I)


def obter_links_noticias(site):
    response = requests.get(site, headers=HEADERS, timeout=20)
    response.raise_for_status()

    soup = BeautifulSoup(response.text, "html.parser")

    dominio_site = urlparse(site).netloc  # ex: www.bbc.com

    links = set()

    for a in soup.find_all("a", href=True):
        href = a["href"]

        if href.startswith("#"):
            continue
        if href.startswith("javascript"):
            continue

        url = urljoin(site, href)
        parsed = urlparse(url)

        # Compara domínio de forma robusta (não string solta)
        if parsed.netloc != dominio_site:
            continue

        # Só aceita links que parecem ser artigos de notícia de verdade
        if not PADRAO_ARTIGO_BBC.search(parsed.path):
            continue

        ignorar = [
            "login", "about", "privacy", "contact", "video",
            "live", "podcast", "newsletter", "facebook", "twitter", "instagram",
        ]

        if any(p in url.lower() for p in ignorar):
            continue

        links.add(url)

    return list(links)


def extrair_noticia(url):
    response = requests.get(url, headers=HEADERS, timeout=20)
    response.raise_for_status()

    resultado = trafilatura.extract(
        response.text,
        output_format="json",
        favor_precision=True,
        with_metadata=True,
    )

    if resultado is None:
        return None

    dados = json.loads(resultado)

    return {
        "titulo": dados.get("title"),
        "texto": dados.get("text"),
        "url": url
    }


def buscar_noticia_aleatoria(site):
    print("Buscando links...")
    links = obter_links_noticias(site)
    print(f"Foram encontrados {len(links)} links.\n")
    print("Exemplos de links encontrados:")
    for l in links[:10]:
        print(" -", l)

    random.shuffle(links)

    for link in links:
        try:
            noticia = extrair_noticia(link)
            if noticia and noticia["texto"]:
                return noticia
            else:
                print(f"[sem texto] {link}")
        except requests.exceptions.HTTPError as e:
            print(f"[erro http] {link} -> {e}")
        except Exception as e:
            print(f"[erro] {link} -> {e}")

    return None