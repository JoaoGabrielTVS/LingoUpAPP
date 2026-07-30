package com.example.lingoup
import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

@Composable
fun ReadScreen(){
    var texto  = "A amizade consegue ser tão complexa.\n" +
            "Deixa uns desanimados, outros bem felizes.\n" +
            "É a alimentação dos fracos\n" +
            "É o reino dos fortes.\n" +
            "\n" +
            "Faz-nos cometer erros\n" +
            "Os fracos deixam se ir abaixo\n" +
            "Os fortes erguem sempre a cabeça\n" +
            "Os assim assumem-nos.\n" +
            "\n" +
            "Sem pensar conquistamos\n" +
            "o mundo geral\n" +
            "e construímos o nosso pequeno lugar,\n" +
            "deixando brilhar cada estrelinha.\n" +
            "\n" +
            "Estrelinhas.\n" +
            "Doces, sensíveis, frias, ternurentas.\n" +
            "Mas sempre presentes em qualquer parte.\n" +
            "Os donos da amizade."
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){


        Text(texto)

        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                //error
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.Blue)
            )
        ) {

            Text("Avançar")

        }


    }
}
