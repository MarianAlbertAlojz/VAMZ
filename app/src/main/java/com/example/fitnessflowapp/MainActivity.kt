package com.example.fitnessflowapp

import AppDatabase
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.fitnessflowapp.navigation.AppNavGraph
import com.example.fitnessflowapp.navigation.Screen
import com.example.fitnessflowapp.ui.theme.FitnessFlowAppTheme


/**
 * hlavna aktivita aplikacie,inicializuje navigaciu a urcuje uvodnu obrazovku
 *
 * @sample startDestination sa urci podla toho, ci uz pouzivatel vytvoril profil
 *          - ak profil existuje presmeruje sa na domovsku obrazovku (Home)
 *          - inak sa zobrazi uvodna obrazovka (Welcome)
 *
 * pouziva sa LaunchedEffect na nacitanie dat mimo hlavneho vlakna (suspend funkcia)
 * navController sa pouziva na riadenie navigacie medzi obrazovkami
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //toto poriesit co je vrchna cast ako su notifikacie a hodiny a tak
            FitnessFlowAppTheme {
                val navController = rememberNavController()
                val context = LocalContext.current
                var startDestination by remember { mutableStateOf<String?>(null) }

                LaunchedEffect(Unit) {
                    val dao = AppDatabase.getInstance(context).userProfileDao()
                    val profileExists = dao.getProfile() != null
                    startDestination = if (profileExists) {
                        Screen.Home.route
                    } else {
                        Screen.Welcome.route
                    }
                }

                startDestination?.let {
                    AppNavGraph(navController = navController, startDestination = it)
                }
            }
        }
    }
}
