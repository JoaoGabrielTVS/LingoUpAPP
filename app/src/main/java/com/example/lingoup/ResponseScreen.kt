package com.example.lingoup

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
fun ResponseScreen() {
    val q1 = "questao 1"
    val q2 = "questao 2"
    val q3 = "questao 3"
    val q4 = "questao 4"
    val q5 = "questao 5"

    val scrollState = rememberScrollState()

    // 1. Variável que guarda o texto digitado
    var textInputq1 by remember { mutableStateOf("") }
    var textInputq2 by remember { mutableStateOf("") }
    var textInputq3 by remember { mutableStateOf("") }
    var textInputq4 by remember { mutableStateOf("") }
    var textInputq5 by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(vertical = 32.dp),
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
        // 2. O campo de digitação contornado
        OutlinedTextField(
            value = textInputq1, // Mostra o valor da variável
            onValueChange = { novoTexto ->
                textInputq1 = novoTexto // Atualiza a variável quando o usuário digita
            },
            label = { Text("Digite seu nome") }, // Texto que fica em cima/dentro
            placeholder = { Text("Ex: João Silva") }, // Dica que some ao digitar
            modifier = Modifier.weight(1f)
        )

        Button(
            onClick = {
                //error
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.Blue)
            )
        ) {
            Text(
                text = "Entrar",
                color = Color.White
            )

            // Apenas para teste: mostra o que está sendo digitado abaixo
        }
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
            // 2. O campo de digitação contornado
            OutlinedTextField(
                value = textInputq2, // Mostra o valor da variável
                onValueChange = { novoTexto ->
                    textInputq2 = novoTexto // Atualiza a variável quando o usuário digita
                },
                label = { Text("Digite seu nome") }, // Texto que fica em cima/dentro
                placeholder = { Text("Ex: João Silva") }, // Dica que some ao digitar
                modifier = Modifier.weight(1f)
            )

            Button(
                onClick = {
                    //error
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.Blue)
                )
            ) {
                Text(
                    text = "Entrar",
                    color = Color.White
                )

                // Apenas para teste: mostra o que está sendo digitado abaixo
            }
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
            // 2. O campo de digitação contornado
            OutlinedTextField(
                value = textInputq3, // Mostra o valor da variável
                onValueChange = { novoTexto ->
                    textInputq3 = novoTexto // Atualiza a variável quando o usuário digita
                },
                label = { Text("Digite seu nome") }, // Texto que fica em cima/dentro
                placeholder = { Text("Ex: João Silva") }, // Dica que some ao digitar
                modifier = Modifier.weight(1f)
            )

            Button(
                onClick = {
                    //error
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.Blue)
                )
            ) {
                Text(
                    text = "Entrar",
                    color = Color.White
                )

                // Apenas para teste: mostra o que está sendo digitado abaixo
            }
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
            // 2. O campo de digitação contornado
            OutlinedTextField(
                value = textInputq4, // Mostra o valor da variável
                onValueChange = { novoTexto ->
                    textInputq4 = novoTexto // Atualiza a variável quando o usuário digita
                },
                label = { Text("Digite seu nome") }, // Texto que fica em cima/dentro
                placeholder = { Text("Ex: João Silva") }, // Dica que some ao digitar
                modifier = Modifier.weight(1f)
            )

            Button(
                onClick = {
                    //error
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.Blue)
                )
            ) {
                Text(
                    text = "Entrar",
                    color = Color.White
                )

                // Apenas para teste: mostra o que está sendo digitado abaixo
            }
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
            // 2. O campo de digitação contornado
            OutlinedTextField(
                value = textInputq5, // Mostra o valor da variável
                onValueChange = { novoTexto ->
                    textInputq5 = novoTexto // Atualiza a variável quando o usuário digita
                },
                label = { Text("Digite seu nome") }, // Texto que fica em cima/dentro
                placeholder = { Text("Ex: João Silva") }, // Dica que some ao digitar
                modifier = Modifier.weight(1f)
            )

            Button(
                onClick = {
                    //error
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.Blue)
                )
            ) {
                Text(
                    text = "Entrar",
                    color = Color.White
                )

                // Apenas para teste: mostra o que está sendo digitado abaixo
            }
        }




    }
}