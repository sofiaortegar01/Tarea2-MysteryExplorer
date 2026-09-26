package com.gmail.sofiaortegar01.mysteryexplorer.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gmail.sofiaortegar01.mysteryexplorer.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorldMapScreen(
    onCountrySelected: (String) -> Unit,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Selecciona un país") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.77f) // Proporción panorámica del mapa
                    .padding(8.dp)
            ) {
                val mapWidth = maxWidth
                val mapHeight = maxHeight

                // 1. Imagen del mapa de neón
                Image(
                    painter = painterResource(id = R.drawable.mapa_neon),
                    contentDescription = "Mapa Mundi Neón",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )

                // 2. PIN DE ESTADOS UNIDOS (Norteamérica central)
                InteractivePin(
                    countryName = "Estados Unidos",
                    xPercent = 0.23f,
                    yPercent = 0.40f,
                    mapWidth = mapWidth,
                    mapHeight = mapHeight,
                    onClick = { onCountrySelected("us") } // Asegúrate de mandar el código que usa tu lista ("us" o "Estados Unidos")
                )

                // 3. PIN DE MÉXICO (Justo debajo de EE.UU.)
                InteractivePin(
                    countryName = "México",
                    xPercent = 0.22f,
                    yPercent = 0.52f,
                    mapWidth = mapWidth,
                    mapHeight = mapHeight,
                    onClick = { onCountrySelected("mx") } // O "México" según lo manejes en tus casesList
                )

                // 4. PIN DE REINO UNIDO (Europa Occidental / Islas británicas)
                InteractivePin(
                    countryName = "Reino Unido",
                    xPercent = 0.47f,
                    yPercent = 0.30f,
                    mapWidth = mapWidth,
                    mapHeight = mapHeight,
                    onClick = { onCountrySelected("uk") }
                )

                // 5. PIN DE JAPÓN (Islas al este del continente asiático)
                InteractivePin(
                    countryName = "Japón",
                    xPercent = 0.79f,
                    yPercent = 0.42f,
                    mapWidth = mapWidth,
                    mapHeight = mapHeight,
                    onClick = { onCountrySelected("jp") }
                )
            }
        }
    }
}

@Composable
fun BoxScope.InteractivePin(
    countryName: String,
    xPercent: Float,
    yPercent: Float,
    mapWidth: androidx.compose.ui.unit.Dp,
    mapHeight: androidx.compose.ui.unit.Dp,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .offset(
                x = mapWidth * xPercent - 18.dp,
                y = mapHeight * yPercent - 36.dp
            )
            .size(36.dp)
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = countryName,
                tint = Color(0xFF00E5FF), // Cian neón
                modifier = Modifier.size(32.dp)
            )
        }
    }
}