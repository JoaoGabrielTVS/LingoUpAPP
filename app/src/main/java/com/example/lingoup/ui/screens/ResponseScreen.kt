package com.example.lingoup.ui.screens
import  androidx.compose.foundation.Image
import android.content.Intent
import android.media.Image
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lingoup.R
import com.example.lingoup.ui.activities.AnalizysActivity
import com.example.lingoup.ui.activities.ReadActivity
import com.example.lingoup.viewmodel.ReadViewModel
import com.example.lingoup.viewmodel.ResponseViewModel
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun ResponseScreen(resumo:String , viewModel: ResponseViewModel = viewModel()) {
    LaunchedEffect(Unit) {
        viewModel.iniciarComResumo(resumo)
    }
        
    val context = LocalContext.current

    val q1 = viewModel.pergunta1
    val q2 = viewModel.pergunta2
    val q3 = viewModel.pergunta3
    val q4 = viewModel.pergunta4
    val q5 = viewModel.pergunta5

    val scrollState = rememberScrollState()

    var textInputq1 by remember { mutableStateOf("") }
    var textInputq2 by remember { mutableStateOf("") }
    var textInputq3 by remember { mutableStateOf("") }
    var textInputq4 by remember { mutableStateOf("") }
    var textInputq5 by remember { mutableStateOf("") }
    
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
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = "Suas Perguntas",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = colorResource(id = R.color.Blue),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            // Questão 1
            QuestionCard(q1, textInputq1) { textInputq1 = it }
            Spacer(modifier = Modifier.height(16.dp))

            // Questão 2
            QuestionCard(q2, textInputq2) { textInputq2 = it }
            Spacer(modifier = Modifier.height(16.dp))

            // Questão 3
            QuestionCard(q3, textInputq3) { textInputq3 = it }
            Spacer(modifier = Modifier.height(16.dp))

            // Questão 4
            QuestionCard(q4, textInputq4) { textInputq4 = it }
            Spacer(modifier = Modifier.height(16.dp))

            // Questão 5
            QuestionCard(q5, textInputq5) { textInputq5 = it }
            
            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    val intent = Intent(context, AnalizysActivity::class.java)
                    intent.putExtra("resumo", resumo)
                    intent.putExtra("perguntas", "1: $q1/ 2:$q2/ 3:$q3/ 4:$q4/ 5:$q5")
                    intent.putExtra("resposta","1: $textInputq1/ 2:$textInputq2/ 3:$textInputq3/ 4:$textInputq4/ 5:$textInputq5")
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.Blue)
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
            ) {
                Text(
                    text = "Enviar Respostas",
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

@Composable
fun QuestionCard(question: String, value: String, onValueChange: (String) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.9f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = question,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.Blue),
                lineHeight = 22.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                label = { Text("Sua resposta...") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(id = R.color.Blue),
                    unfocusedBorderColor = Color.LightGray
                )
            )
        }
    }
}
