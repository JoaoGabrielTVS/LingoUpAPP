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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lingoup.R
import com.example.lingoup.ui.activities.AnalizysActivity
import com.example.lingoup.ui.activities.IntroductionActivity
import com.example.lingoup.viewmodel.AnalizysViewModel

@Composable
fun AnalizysScreen(resumo:String, questoes:String, respostas:String , viewModel: AnalizysViewModel = viewModel()) {

   LaunchedEffect(Unit) {
        viewModel.iniciarComAnalise(resumo, questoes, respostas)
   }

    var texto = viewModel.analise
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Column(
        modifier = Modifier

            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 23.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Text(texto)

        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                val intent = Intent(context, IntroductionActivity::class.java)
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
    }
}
