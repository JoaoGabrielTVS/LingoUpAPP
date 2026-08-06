package com.example.lingoup.ui.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lingoup.R
import com.example.lingoup.ui.activities.AnalizysActivity
import com.example.lingoup.ui.activities.IntroductionActivity

@Composable
fun HomeScreen() {
   val context = LocalContext.current

   Box(
       modifier = Modifier.fillMaxSize()

   ){
       Image(
           painter = painterResource(id = R.drawable.background_white_initialscreen),
           contentDescription = null,
           modifier = Modifier.fillMaxSize(),
           contentScale = ContentScale.FillBounds // faz a imagem preencher toda a tela
       )
       Column(
           modifier = Modifier
               .fillMaxSize(),

           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center
       ){
           Image(
               painter = painterResource(id = R.drawable.logo),
               contentDescription = "LOgo Do LingoUp",
               modifier = Modifier.size(188.dp, 163.dp)

           )
           Spacer(modifier = Modifier.height(20.dp))

           Text(
               text = stringResource(R.string.app_name),
               fontSize = 30.sp,
               fontWeight = FontWeight.Bold,
               color = colorResource(R.color.Blue)

           )
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
}
