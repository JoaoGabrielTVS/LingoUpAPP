package com.example.lingoup.ui.screens
import android.content.Intent
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.lingoup.R
import com.example.lingoup.ui.activities.ReadActivity

@Composable
fun IntroductionScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 23.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        Text(   "1) O aplicativo vai gerar um texto em ingles para você ler\n" +
                "2) Será gerado 5 perguntas sobre o texto gerado\n" +
                "3) Você vai ter que responder a cada pergunta\n"+
                "4) Você vai receber uma nota de 0 a 10 sobre cada uma de suas respostas\n"

        )
        Spacer(modifier = Modifier.height(200.dp))
        Button(

            onClick = {
                val intent = Intent(context, ReadActivity::class.java)
                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.Blue)
            )

        ) {

            Text(text = "Avançar" , color = Color.White)

        }


        }

}
