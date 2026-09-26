package com.gmail.sofiaortegar01.mysteryexplorer.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

// Modelo para representar nuestras 5 categorías principales
data class CategoryUi(
    val id: String,
    val name: String,
    val icon: String,
    val description: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onCategoryClick: (String) -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onReportClick: () -> Unit // <-- NUEVO: Acción para ir a reportar
) {
    var searchQuery by remember { mutableStateOf("") }

    val categories = listOf(
        CategoryUi("ovnis", "OVNIs", "👽", "Avistamientos y luces"),
        CategoryUi("lugares", "Lugares misteriosos", "🗺", "Sitios con leyendas"),
        CategoryUi("naturales", "Fenómenos naturales", "🌋", "Eventos poco comunes"),
        CategoryUi("criaturas", "Criaturas", "🐾", "Seres y críptidos"),
        CategoryUi("historicos", "Casos históricos", "📜", "Misterios del pasado")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mystery Explorer") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        // NUEVO: Agregamos el Botón de Acción Flotante (Cumpliendo Sección 2)
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onReportClick,
                icon = { Text("📝") },
                text = { Text("Reportar") }
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
            // ... (el resto de tu código de HomeScreen se queda igualito)
            // Descripción breve
            Text(
                text = "Explora lo desconocido 🌎",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary
            )

            // Barra de búsqueda
            OutlinedTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                    onSearchQueryChanged(it)
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Buscar fenómeno...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
                singleLine = true
            )

            // Título de Categorías
            Text(
                text = "Categorías",
                style = MaterialTheme.typography.titleLarge
            )

            // Cuadrícula de categorías con Tarjetas (Cards)
            // Cuadrícula de categorías adaptativa
            LazyVerticalGrid(
                // CAMBIO 1: Usamos Adaptive para que se adapte al tamaño de la pantalla
                columns = GridCells.Adaptive(minSize = 140.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(categories) { category ->
                    CategoryCard(
                        category = category,
                        onClick = { onCategoryClick(category.id) }
                    )
                }
            }
        }
    }
}

// Componente visual para cada tarjeta de categoría
// Componente visual para cada tarjeta de categoría
@Composable
fun CategoryCard(
    category: CategoryUi,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.3f) // CAMBIO 2: Mantiene una proporción visual perfecta según el ancho disponible
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = category.icon,
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = category.name,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}