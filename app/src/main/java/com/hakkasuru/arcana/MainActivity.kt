package com.hakkasuru.arcana

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hakkasuru.arcana.core.arcana.document.Login
import com.hakkasuru.arcana.debugprefs.PreferenceScreen
import com.hakkasuru.arcana.home.HomeScreen
import com.hakkasuru.arcana.login.LoginScreen
import com.hakkasuru.arcana.ui.theme.ArcanaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArcanaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("preferences") { PreferenceScreen(navController) }
        composable("preferences/login") { PreferenceScreen(navController, cls = Login::class.java) }
        composable("login") { LoginScreen() }
    }
}