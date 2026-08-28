package com.example.lingoup.ui.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
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

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.style.TextAlign

@Composable
fun HomeScreen(onNavigateToIntro: () -> Unit) {
   Box(
       modifier = Modifier.fillMaxSize()
   ){
       Image(
           painter = painterResource(id = R.drawable.background_white_initialscreen),
           contentDescription = null,
           modifier = Modifier.fillMaxSize(),
           contentScale = ContentScale.FillBounds
       )
       
       Column(
           modifier = Modifier
               .fillMaxSize()
               .systemBarsPadding()
               .padding(24.dp),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.SpaceBetween
       ){
           Column(
               horizontalAlignment = Alignment.CenterHorizontally,
               modifier = Modifier.padding(top = 80.dp)
           ) {
               Card(
                   shape = RoundedCornerShape(32.dp),
                   colors = CardDefaults.cardColors(containerColor = Color.White),
                   elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
                   modifier = Modifier.size(200.dp)
               ) {
                   Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                       Image(
                           painter = painterResource(id = R.drawable.logo),
                           contentDescription = "Logo LingoUP",
                           modifier = Modifier.size(150.dp)
                       )
                   }
               }
               
               Spacer(modifier = Modifier.height(32.dp))

               Text(
                   text = "LingoNews",
                   fontSize = 42.sp,
                   fontWeight = FontWeight.Black,
                   color = colorResource(id = R.color.Blue),
                   textAlign = TextAlign.Center
               )
               
               Text(
                   text = "Aprenda inglês com as notícias do mundo",
                   fontSize = 18.sp,
                   fontWeight = FontWeight.Medium,
                   color = Color.Gray,
                   textAlign = TextAlign.Center,
                   modifier = Modifier.padding(top = 8.dp)
               )
           }

           Button(
               onClick = onNavigateToIntro,
               modifier = Modifier
                   .fillMaxWidth()
                   .height(64.dp)
                   .padding(bottom = 8.dp),
               shape = RoundedCornerShape(20.dp),
               colors = ButtonDefaults.buttonColors(
                   containerColor = colorResource(id = R.color.Blue)
               ),
               elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
           ) {
               Text(
                   text = "Começar Agora",
                   fontSize = 20.sp,
                   fontWeight = FontWeight.Bold,
                   color = Color.White
               )
           }
       }
   }
}
