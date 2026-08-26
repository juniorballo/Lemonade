 Lemonade App

Une application Android en **Kotlin** avec **Jetpack Compose**.

Aperçu de l'application

L'application repose sur un cycle de 4 étapes interactives :
1. **Sélectionner le citron** : Cliquez sur le citronnier pour cueillir un citron.
2. **Presser le citron** : Cliquez plusieurs fois (nombre déterminé aléatoirement entre 2 et 4 fois) pour obtenir le jus.
3. **Boire la citronnade** : Cliquez sur le verre plein pour déguster.
4. **Recommencer** : Cliquez sur le verre vide pour réinitialiser le cycle.

 Technologies utilisées

* **Langage** : Kotlin
* **UI Toolkit** : Jetpack Compose
* **Composants Material Design 3** : `Scaffold`, `CenterAlignedTopAppBar`, `Button`, `Text`, `Image`
* **Gestion d'état** : `remember` et `mutableStateOf`
* **IDE** : Android Studio

Fonctionnalités & Design

* **Barre d'en-tête (TopAppBar)** : Titre centré avec fond jaune citron (`#F9E44C`).
* **Bouton d'action personnalisé** : Angles arrondis (`24.dp`), fond vert pastel et bordure verte structurée.
* **Accessibilité** : Textes dynamiques et descriptions d'images (`contentDescription`) adaptées pour chaque étape.

