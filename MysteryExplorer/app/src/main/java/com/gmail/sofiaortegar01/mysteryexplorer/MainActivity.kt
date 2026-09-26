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
import com.gmail.sofiaortegar01.mysteryexplorer.ui.CountryScreen
import com.gmail.sofiaortegar01.mysteryexplorer.ui.DetailScreen
import com.gmail.sofiaortegar01.mysteryexplorer.ui.HomeScreen
import com.gmail.sofiaortegar01.mysteryexplorer.ui.MysteryCaseUi
import com.gmail.sofiaortegar01.mysteryexplorer.ui.ReportScreen
import com.gmail.sofiaortegar01.mysteryexplorer.ui.theme.MysteryExplorerTheme

sealed class Screen {
    object Home : Screen()
    data class Country(val categoryId: String) : Screen()
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
                            MysteryCaseUi("1", "Caso Roswell", "ovnis", "us", "1947", "Supuesto choque de una nave nodriza extraterrestre."),
                            MysteryCaseUi("2", "Luces de Ciudad Juárez", "ovnis", "mx", "2020", "Avistamientos masivos de objetos luminosos en el norte."),
                            MysteryCaseUi("3", "El Monstruo de la Laguna", "criaturas", "mx", "1995", "Extrañas criaturas avistadas cerca de cuerpos de agua.")
                        )
                    }

                    when (val screen = currentScreen) {
                        is Screen.Home -> {
                            HomeScreen(
                                onCategoryClick = { categoryId ->
                                    selectedCategory = categoryId
                                    currentScreen = Screen.Country(categoryId)
                                },
                                onSearchQueryChanged = { query -> },
                                onReportClick = {
                                    currentScreen = Screen.Report
                                }
                            )
                        }
                        is Screen.Country -> {
                            CountryScreen(
                                categoryId = screen.categoryId,
                                onBackClick = { currentScreen = Screen.Home },
                                onCountryClick = { countryId ->
                                    selectedCountry = countryId
                                    currentScreen = Screen.Cases(screen.categoryId, countryId)
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