package com.example.lingoup.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lingoup.ui.screens.*

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            MaterialTheme {
                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {
                    composable("home") {
                        HomeScreen(onNavigateToIntro = { navController.navigate("introduction") })
                    }

                    composable("introduction") {
                        IntroductionScreen(onNavigateToRead = { navController.navigate("read") })
                    }

                    composable("read") {
                        ReadScreen(onNavigateToResponse = { resumo ->
                            navController.navigate("response/$resumo")
                        })
                    }

                    composable("response/{resumo}") { backStackEntry ->
                        val resumo = backStackEntry.arguments?.getString("resumo") ?: ""
                        ResponseScreen(
                            resumo = resumo,
                            onNavigateToAnalysis = { resumoInfo, perguntas, respostas ->
                                navController.currentBackStackEntry?.savedStateHandle?.set("resumo", resumoInfo)
                                navController.currentBackStackEntry?.savedStateHandle?.set("perguntas", perguntas)
                                navController.currentBackStackEntry?.savedStateHandle?.set("respostas", respostas)
                                navController.navigate("analysis")
                            }
                        )
                    }

                    composable("analysis") {
                        val resumo = navController.previousBackStackEntry?.savedStateHandle?.get<String>("resumo") ?: ""
                        val perguntas = navController.previousBackStackEntry?.savedStateHandle?.get<String>("perguntas") ?: ""
                        val respostas = navController.previousBackStackEntry?.savedStateHandle?.get<String>("respostas") ?: ""
                        
                        AnalizysScreen(
                            resumo = resumo,
                            questoes = perguntas,
                            respostas = respostas,
                            onBackToHome = {
                                navController.navigate("home") {
                                    popUpTo("home") { inclusive = true }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
