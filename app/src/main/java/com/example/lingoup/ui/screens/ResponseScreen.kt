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
            painter = painterResource(id = R.drawable.background_screens)
            ,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 23.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
            Text(q1)

            Spacer(modifier = Modifier.height(20.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = textInputq1,
                onValueChange = { novoTexto ->
                    textInputq1 = novoTexto
                },
                label = { Text("Digite sua respostas") },
                modifier = Modifier.weight(1f)
            )


        }
            Spacer(modifier = Modifier.height(20.dp))
            Text(q2)

            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                OutlinedTextField(
                    value = textInputq2,
                    onValueChange = { novoTexto ->
                        textInputq2 = novoTexto
                    },
                    label = { Text("Digite Sua Resposta") },
                    modifier = Modifier.weight(1f)
                )


            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(q3)
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = textInputq3,
                    onValueChange = { novoTexto ->
                        textInputq3 = novoTexto
                    },
                    label = { Text("Digite Sua Resposta") },
                    modifier = Modifier.weight(1f)
                )


            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(q4)
            Spacer(modifier = Modifier.height(20.dp))


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = textInputq4,
                    onValueChange = { novoTexto ->
                        textInputq4 = novoTexto
                    },
                    label = { Text("Digite Sua Resposta") },
                    modifier = Modifier.weight(1f)
                )


            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(q5)
            Spacer(modifier = Modifier.height(20.dp))


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = textInputq5,
                    onValueChange = { novoTexto ->
                        textInputq5 = novoTexto
                    },
                    label = { Text("Digite Sua Resposta") },
                    modifier = Modifier.weight(1f)
                )


            }
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    val intent = Intent(context, AnalizysActivity::class.java)
                    intent.putExtra("resumo", resumo)
                    intent.putExtra("perguntas", "questao1: $q1/ questao2:$q2/ questao3:$q3/ questao4:$q4/ questao5:$q5")
                    intent.putExtra("resposta","respostaquestao1: $textInputq1/ respostaquestao2:$textInputq2/ respostaquestao3:$textInputq3/ respostaquestao4:$textInputq4/ respostaquestao5:$textInputq5")
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.Blue)
                )

            ) {
                Text(
                    text = "Entrar",
                    color = Color.White
                )

            }

            Box(
                modifier = Modifier
                .width(200.dp)
                .height(100.dp)

            ){

            }
        }
        
        if (viewModel.isLoading) {
            LoadingScreen()
        }
    }
}
