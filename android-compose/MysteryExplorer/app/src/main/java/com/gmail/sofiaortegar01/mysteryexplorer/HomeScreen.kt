package com.gmail.sofiaortegar01.mysteryexplorer.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gmail.sofiaortegar01.mysteryexplorer.R

// Modelo de datos con su respectivo recurso de imagen (Int)
data class CategoryUi(
    val id: String,
    val name: String,
    val imageRes: Int,
    val description: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onCategoryClick: (String) -> Unit,
    onSearchQueryChanged: (String) -> Unit,
    onReportClick: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    // Lista de categorías con sus respectivas imágenes locales
    // Nota: Asegúrate de tener estas imágenes en tu carpeta res/drawable/
    val categories = listOf(
        CategoryUi("ovnis", "OVNIs", R.drawable.ovnis, "Avistamientos y luces"),
        CategoryUi("lugares", "Lugares misteriosos", R.drawable.lugaresm, "Sitios con leyendas"),
        CategoryUi("naturales", "Fenómenos naturales", R.drawable.fenaturales, "Eventos poco comunes"),
        CategoryUi("criaturas", "Criaturas", R.drawable.criaturas, "Seres y críptidos"),
        CategoryUi("historicos", "Casos históricos", R.drawable.casosh, "Misterios del pasado")
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
            Text(
                text = "Explora los misterios",
                style = MaterialTheme.typography.titleLarge
            )

            // Lista vertical de categorías
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
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

@Composable
fun CategoryCard(
    category: CategoryUi,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
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
            // Imagen de la categoría cargada desde drawable
            Image(
                painter = painterResource(id = category.imageRes),
                contentDescription = category.name,
                modifier = Modifier
                    .size(56.dp)
                    .padding(4.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = category.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = category.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}