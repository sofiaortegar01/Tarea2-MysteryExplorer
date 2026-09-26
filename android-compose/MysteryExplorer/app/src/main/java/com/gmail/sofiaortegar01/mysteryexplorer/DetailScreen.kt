package com.gmail.sofiaortegar01.mysteryexplorer.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gmail.sofiaortegar01.mysteryexplorer.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    caseId: String,
    // Opcional: Puedes pasarle el objeto completo del caso o su imagen directamente.
    // Aquí usamos un recurso por defecto o puedes cambiarlo por tu modelo `MysteryCaseUi`.
    imageRes: Int = R.drawable.ovnis,
    onBackClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalles del Misterio") },
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
                .verticalScroll(scrollState)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // --- IMAGEN REAL DEL CASO ---
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = "Imagen del caso",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop // Asegura que la foto llene el espacio sin deformarse
                )
            }

            // Título del caso
            Text(
                text = "Caso Roswell (ID: $caseId)",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )

            // Metadatos (País, Año, Categoría)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                BadgeItem(text = "📍 Estados Unidos")
                BadgeItem(text = "📅 1947")
                BadgeItem(text = "👽 OVNI")
            }

            HorizontalDivider()

            // Indicadores de progreso
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Nivel de misterio (80%)", style = MaterialTheme.typography.bodyMedium)
                LinearProgressIndicator(
                    progress = { 0.8f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text("Credibilidad (60%)", style = MaterialTheme.typography.bodyMedium)
                LinearProgressIndicator(
                    progress = { 0.6f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                )
            }

            HorizontalDivider()

            // Descripción detallada
            Text(
                text = "Descripción del caso",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Este es uno de los incidentes más famosos de la historia moderna relacionado con el supuesto choque de un objeto volador no identificado (OVNI) en las cercanías de Roswell, Nuevo México, generando décadas de teorías e investigaciones gubernamentales.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Botones de acción real
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { /* Acción guardar */ },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Star, contentDescription = null)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Guardar")
                }

                OutlinedButton(
                    onClick = { /* Acción ubicación */ },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.LocationOn, contentDescription = null)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Ubicación")
                }
            }

            OutlinedButton(
                onClick = { /* Acción compartir */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Share, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Compartir caso")
            }
        }
    }
}

@Composable
fun BadgeItem(text: String) {
    Surface(
        color = MaterialTheme.colorScheme.secondaryContainer,
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}