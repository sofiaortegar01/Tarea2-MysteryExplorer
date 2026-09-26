package com.gmail.sofiaortegar01.mysteryexplorer.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Modelo temporal para representar un país
data class CountryUi(
    val id: String,
    val name: String,
    val flagEmoji: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryScreen(
    categoryId: String,
    onBackClick: () -> Unit,
    onCountryClick: (String) -> Unit
) {
    // Lista de países de ejemplo para explorar los misterios
    val countries = listOf(
        CountryUi("mx", "México", "🇲🇽"),
        CountryUi("us", "Estados Unidos", "🇺🇸"),
        CountryUi("uk", "Reino Unido", "🇬🇧"),
        CountryUi("jp", "Japón", "🇯🇵")
    )

    val categoryTitle = when (categoryId) {
        "ovnis" -> "OVNIs"
        "lugares" -> "Lugares misteriosos"
        "naturales" -> "Fenómenos naturales"
        "criaturas" -> "Criaturas"
        "historicos" -> "Casos históricos"
        else -> "Categoría"
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(categoryTitle) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Regresar")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Selecciona un país",
                style = MaterialTheme.typography.titleLarge
            )

            // Lista vertical de países (Cumpliendo elementos de lista)
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(countries) { country ->
                    CountryItem(
                        country = country,
                        onClick = { onCountryClick(country.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun CountryItem(
    country: CountryUi,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = country.flagEmoji,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = country.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}