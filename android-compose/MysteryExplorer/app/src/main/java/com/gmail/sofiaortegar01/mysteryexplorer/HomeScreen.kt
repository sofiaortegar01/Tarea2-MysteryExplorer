package com.gmail.sofiaortegar01.mysteryexplorer.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    // Lista de categorías con sus respectivas imágenes locales
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

            // Cuadrícula que pinta las tarjetas cuadradas automáticamente usando tu lista
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // Dos columnas
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(categories) { category ->
                    CategorySquareCard(
                        category = category,
                        onClick = { onCategoryClick(category.id) }
                    )
                }
            }
        }
    }
}

// Componente para la tarjeta cuadrada con imagen y texto adaptados
@Composable
fun CategorySquareCard(
    category: CategoryUi,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f) // Forza a que sea un cuadrado perfecto (ancho == alto)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Imagen cuadrada o adaptada en la parte superior
            Image(
                painter = painterResource(id = category.imageRes),
                contentDescription = category.name,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Nombre de la categoría
            Text(
                text = category.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}