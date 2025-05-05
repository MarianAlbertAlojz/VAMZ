package com.example.fitnessflowapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.fitnessflowapp.navigation.AppNavGraph
import com.example.fitnessflowapp.ui.theme.FitnessFlowAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FitnessFlowAppTheme {
                val navController = rememberNavController()
                AppNavGraph(navController = navController)
                //toto poriesit co je vrchna cast ako su notifikacie a hodiny a tak
                //WelcomeScreen()
                /*Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier.padding(innerPadding)
                    ) {

                        //OnboardingScreen()
                    }
                }*/
            }
        }
    }
}
