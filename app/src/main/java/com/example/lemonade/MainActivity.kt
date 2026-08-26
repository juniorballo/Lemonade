package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LemonadeApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp() {
    var etapeActuelle by remember { mutableStateOf(1) }
    var nombreDePressions by remember { mutableStateOf(0) }

    val identifiantImage: Int
    val identifiantTexte: Int
    val identifiantDescription: Int

    when (etapeActuelle) {
        1 -> {
            identifiantImage = R.drawable.lemon_tree
            identifiantTexte = R.string.select_lemon
            identifiantDescription = R.string.lemon_tree_desc
        }
        2 -> {
            identifiantImage = R.drawable.lemon_squeeze
            identifiantTexte = R.string.squeeze_lemon
            identifiantDescription = R.string.lemon_desc
        }
        3 -> {
            identifiantImage = R.drawable.lemon_drink
            identifiantTexte = R.string.drink_lemonade
            identifiantDescription = R.string.lemonade_desc
        }
        else -> {
            identifiantImage = R.drawable.lemon_restart
            identifiantTexte = R.string.empty_glass
            identifiantDescription = R.string.empty_glass_desc
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color(0xFFF9E44C) // Jaune citron de l'en-tête
                )
            )
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Button(
                onClick = {
                    when (etapeActuelle) {
                        1 -> {
                            nombreDePressions = (2..4).random()
                            etapeActuelle = 2
                        }
                        2 -> {
                            nombreDePressions--
                            if (nombreDePressions == 0) {
                                etapeActuelle = 3
                            }
                        }
                        3 -> etapeActuelle = 4
                        4 -> etapeActuelle = 1
                    }
                },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC3ECD2))
            ) {
                Image(
                    painter = painterResource(id = identifiantImage),
                    contentDescription = stringResource(id = identifiantDescription)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = identifiantTexte),
                fontSize = 18.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeAppPreview() {
    MaterialTheme {
        LemonadeApp()
    }
}