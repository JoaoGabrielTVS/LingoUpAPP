package com.example.lingoup.ui.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lingoup.R
import com.example.lingoup.ui.activities.IntroductionActivity
import com.example.lingoup.viewmodel.AnalizysViewModel

@Composable
fun AnalizysScreen(
    resumo: String,
    questoes: String,
    respostas: String,
    viewModel: AnalizysViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.iniciarComAnalise(resumo, questoes, respostas)
    }

    val textoRaw = viewModel.analise
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    // Divide o texto de forma precisa usando o token //final//
    val blocos = remember(textoRaw) {
        val rawBlocos = textoRaw.split("//final//")
            .map { it.trim() }
            .filter { it.isNotBlank() && it.length > 5 }
        
        // Separa o card de resultado final (Overall) para garantir que fique por último
        val overall = rawBlocos.filter { it.contains("Overall", ignoreCase = true) || it.contains("###") }
        val questoesList = rawBlocos.filter { !it.contains("Overall", ignoreCase = true) && !it.contains("###") }
        
        questoesList + overall
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background_screens),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Análise da Resposta",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = colorResource(id = R.color.Blue),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Renderiza cada bloco de análise em um Card individual
            blocos.forEach { bloco ->
                val isOverall = bloco.contains("Overall", ignoreCase = true) || bloco.contains("###")
                
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isOverall) colorResource(id = R.color.white).copy(alpha = 0.95f)
                        else Color.White.copy(alpha = 0.95f)
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = if (isOverall) 12.dp else 4.dp)
                ) {
                    Box(modifier = Modifier.padding(24.dp)) {
                        Text(
                            text = parseAnalysisText(bloco),
                            fontSize = if (isOverall) 19.sp else 18.sp,
                            lineHeight = 28.sp,
                            color = Color(0xFF2C3E50),
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val intent = Intent(context, IntroductionActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.Blue)
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
            ) {
                Text(
                    text = "Tentar de Novo",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
        }

        if (viewModel.isLoading) {
            LoadingScreen()
        }
    }
}

/**
 * Função avançada para formatar o texto de análise.
 * Pinta linhas de Score e palavras-chave (Correct/Incorrect) em Azul.
 */
fun parseAnalysisText(text: String): AnnotatedString {
    val lines = text.replace("### ", "").replace("---", "").trim().lines()
    val blueColor = Color(0xFF0347B3)

    return buildAnnotatedString {
        lines.forEachIndexed { index, line ->
            val cleanLine = line.trim()
            
            // 1. Se a linha for de SCORE ou CLASSIFICAÇÃO, pinta ela inteira de Azul Negrito
            if (cleanLine.startsWith("Score:", ignoreCase = true) || 
                cleanLine.startsWith("Overall Score:", ignoreCase = true) ||
                cleanLine.startsWith("Classification:", ignoreCase = true)) {
                
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = blueColor)) {
                    append(cleanLine)
                }
            } else {
                // 2. Se for texto normal, processa negritos (**) e palavras-chave isoladas
                val parts = cleanLine.split("**")
                parts.forEachIndexed { pIndex, part ->
                    if (pIndex % 2 != 0) {
                        // Conteúdo entre ** (Markdown)
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = blueColor)) {
                            append(part)
                        }
                    } else {
                        // Texto comum: busca palavras "Correct" ou "Incorrect" para destacar
                        val words = part.split(" ")
                        words.forEachIndexed { wIndex, word ->
                            val lower = word.lowercase()
                            val isResultKey = lower.contains("correct") || lower.contains("incorrect")
                            
                            if (isResultKey) {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = blueColor)) {
                                    append(word)
                                }
                            } else {
                                append(word)
                            }
                            if (wIndex < words.size - 1) append(" ")
                        }
                    }
                }
            }
            if (index < lines.size - 1) append("\n")
        }
    }
}
