package com.gmail.sofiaortegar01.mysteryexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.gmail.sofiaortegar01.mysteryexplorer.ui.CasesScreen
import com.gmail.sofiaortegar01.mysteryexplorer.ui.DetailScreen
import com.gmail.sofiaortegar01.mysteryexplorer.ui.HomeScreen
import com.gmail.sofiaortegar01.mysteryexplorer.ui.MysteryCaseUi
import com.gmail.sofiaortegar01.mysteryexplorer.ui.ReportScreen
import com.gmail.sofiaortegar01.mysteryexplorer.ui.WorldMapScreen // <--- Importamos tu nuevo mapa
import com.gmail.sofiaortegar01.mysteryexplorer.ui.theme.MysteryExplorerTheme

sealed class Screen {
    object Home : Screen()
    data class Country(val categoryId: String) : Screen()
    // Actualizamos Cases para que reciba el país seleccionado desde el mapa
    data class Cases(val categoryId: String, val countryId: String) : Screen()
    data class Detail(val caseId: String) : Screen()
    object Report : Screen()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MysteryExplorerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }

                    var selectedCategory by remember { mutableStateOf("") }
                    var selectedCountry by remember { mutableStateOf("") }
                    var selectedCaseId by remember { mutableStateOf("1") }
                    val casesList = remember {
                        mutableStateListOf(
                            MysteryCaseUi(
                                id = "1",
                                title = "Caso Roswell",
                                category = "ovnis",
                                country = "us",
                                year = "1947",
                                description = "Supuesto choque de una nave nodriza extraterrestre.",
                                imageRes = R.drawable.ovni1 // Asegúrate de tener tus drawables listos
                            ),
                            MysteryCaseUi(
                                id = "2",
                                title = "Luces de Ciudad Juárez",
                                category = "ovnis",
                                country = "mx",
                                year = "2020",
                                description = "Avistamientos masivos de objetos luminosos en el norte.",
                                imageRes = R.drawable.ovni1 // Cambia por tu recurso correspondiente
                            ),
                            MysteryCaseUi(
                                id = "3",
                                title = "El Monstruo de la Laguna",
                                category = "criaturas",
                                country = "mx",
                                year = "1995",
                                description = "Extrañas criaturas avistadas cerca de cuerpos de agua.",
                                imageRes = R.drawable.criaturas2 // Cambia por tu recurso correspondiente
                            )
                        )
                    }

                    when (val screen = currentScreen) {
                        is Screen.Home -> {
                            HomeScreen(
                                onCategoryClick = { categoryId ->
                                    selectedCategory = categoryId
                                    // Al hacer clic en una categoría (ej. OVNIs), ahora nos lleva al mapa
                                    currentScreen = Screen.Country(categoryId)
                                },
                                onSearchQueryChanged = { query -> },
                                onReportClick = {
                                    currentScreen = Screen.Report
                                }
                            )
                        }
                        is Screen.Country -> {
                            // Aquí reemplazamos CountryScreen por tu WorldMapScreen con pines
                            WorldMapScreen(
                                onCountrySelected = { countryId ->
                                    selectedCountry = countryId
                                    // Cuando el usuario toca un pin en el mapa, avanza a los casos de ese país
                                    currentScreen = Screen.Cases(screen.categoryId, countryId)
                                },
                                onBackClick = {
                                    currentScreen = Screen.Home
                                }
                            )
                        }
                        is Screen.Cases -> {
                            val filteredCases = casesList.filter {
                                it.category == screen.categoryId && it.country == screen.countryId
                            }

                            CasesScreen(
                                categoryId = screen.categoryId,
                                countryId = screen.countryId,
                                customCases = filteredCases,
                                // Al regresar desde los casos, lo mandamos de vuelta al mapa
                                onBackClick = { currentScreen = Screen.Country(screen.categoryId) },
                                onCaseClick = { caseId ->
                                    selectedCaseId = caseId
                                    currentScreen = Screen.Detail(caseId)
                                }
                            )
                        }
                        is Screen.Detail -> {
                            DetailScreen(
                                caseId = screen.caseId,
                                onBackClick = { currentScreen = Screen.Cases(selectedCategory, selectedCountry) }
                            )
                        }
                        is Screen.Report -> {
                            ReportScreen(
                                onBackClick = { currentScreen = Screen.Home },
                                onReportSubmitted = { newCase ->
                                    casesList.add(newCase)
                                    currentScreen = Screen.Home
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}