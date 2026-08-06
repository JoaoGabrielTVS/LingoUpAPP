package com.example.lingoup.ui.screens
import android.content.Intent
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lingoup.R
import com.example.lingoup.ui.activities.ReadActivity
import com.example.lingoup.ui.activities.ResponseActivity
import com.example.lingoup.viewmodel.ReadViewModel



@Composable
fun ReadScreen(
    viewModel: ReadViewModel = viewModel()
){
    val context = LocalContext.current
    val scrollState = rememberScrollState()
LaunchedEffect(
    Unit
) { viewModel.carregarNoticia()}
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 23.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){


        Text(text = viewModel.noticiaTexto)

        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                val intent = Intent(context, ResponseActivity::class.java)
                intent.putExtra("resumo", viewModel.noticiaTexto)

                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.Blue)
            )

        ) {

            Text("Avançar")

        }


    }
}
