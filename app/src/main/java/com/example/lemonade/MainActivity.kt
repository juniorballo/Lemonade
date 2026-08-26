package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Activité principale qui lance l'application Lemonade.
 */
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

/**
 * Composant principal de l'application Lemonade.
 * Gère les états de la préparation de la citronnade et l'interface utilisateur.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp() {
    // Variable pour suivre l'étape
    //
    // actuelle (de 1 à 4)
    var etapeActuelle by remember { mutableStateOf(1) }

    // Variable d'état pour compter le nombre de pressions nécessaires pour l'étape du citron (2 à 4)
    var nombreDePressions by remember { mutableStateOf(0) }

    // Détermination dynamique des ressources (images, textes et descriptions) selon l'étape actuelle
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

    // Scaffold permet d'avoir une structure propre avec une barre d'en-tête (TopAppBar)
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
                    containerColor = Color(0xFFF9E44C) // Couleur jaune citron de la barre d'en-tête
                )
            )
        }
    ) { espaceInterne ->
        // Disposition verticale au centre de l'écran
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(espaceInterne)
        ) {
            // Bouton contenant l'image avec des angles arrondis, une couleur de fond et une bordure
            Button(
                onClick = {
                    // Logique de changement d'étape lors d'un clic
                    when (etapeActuelle) {
                        1 -> {
                            // Génère un nombre aléatoire de pressions requis entre 2 et 4
                            nombreDePressions = (2..4).random()
                            etapeActuelle = 2
                        }
                        2 -> {
                            // Diminue le compteur de pressions à chaque clic
                            nombreDePressions--
                            // Passe à l'étape suivante uniquement lorsque le citron est totalement pressé
                            if (nombreDePressions == 0) {
                                etapeActuelle = 3
                            }
                        }
                        3 -> etapeActuelle = 4 // Passe au verre vide
                        4 -> etapeActuelle = 1 // Recommence le cycle
                    }
                },
                shape = RoundedCornerShape(24.dp), // Coins arrondis de 24dp
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFC3ECD2) // Couleur de fond du bouton (vert clair pastel)
                ),
                border = BorderStroke(2.dp, Color(0xFF104E43)) // Bordure soignée de 2dp autour du bouton
            ) {
                Image(
                    painter = painterResource(id = identifiantImage),
                    contentDescription = stringResource(id = identifiantDescription),
                    modifier = Modifier.padding(16.dp) // Marge interne autour de l'image
                )
            }

            // Espacement vertical de 16dp entre le bouton image et l'instruction texte
            Spacer(modifier = Modifier.height(16.dp))

            // Texte d'instruction avec une taille de police agrandie (18sp) pour une meilleure lisibilité
            Text(
                text = stringResource(id = identifiantTexte),
                fontSize = 18.sp
            )
        }
    }
}

/**
 * Fonction de prévisualisation pour afficher l'application directement dans Android Studio.
 */
@Preview(showBackground = true)
@Composable
fun LemonadeAppPreview() {
    MaterialTheme {
        LemonadeApp()
    }
}