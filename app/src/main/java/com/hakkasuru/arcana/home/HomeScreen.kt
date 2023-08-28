package com.hakkasuru.arcana.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hakkasuru.arcana.core.arcana.Arcana
import com.hakkasuru.arcana.core.arcana.ArcanaFactory
import com.hakkasuru.arcana.core.arcana.document.Login

@Composable
fun HomeScreen(navController: NavController) {
    val arcana = ArcanaFactory.create<Arcana>(LocalContext.current)
    val greetingFlag = arcana.isGreetingEnabled().collectAsState(false).value

    val loginArcana = ArcanaFactory.create<Login>(LocalContext.current)
    val loginFlag = loginArcana.isLoginEnabled().collectAsState(false).value

    Column(modifier = Modifier.fillMaxSize(1f).padding(16.dp)) {
        if (greetingFlag) Greeting("John") else Text(text = "Greeting disabled")

        Spacer(modifier = Modifier.padding(10.dp))

        Button(onClick = { navController.navigate("preferences") }) {
            Text(text = "Preferences")
        }

        Spacer(modifier = Modifier.padding(10.dp))

        if (loginFlag) Button(onClick = { navController.navigate("login") }) {
            Text(text = "Login")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}