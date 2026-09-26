package com.gmail.sofiaortegar01.mysteryexplorer.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.gmail.sofiaortegar01.mysteryexplorer.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreen(
    onBackClick: () -> Unit,
    onReportSubmitted: (MysteryCaseUi) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var witnessesCount by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("2026") }

    // Selección por defecto
    var selectedCategory by remember { mutableStateOf("ovnis") }
    var selectedCountry by remember { mutableStateOf("mx") }

    var mysteryLevel by remember { mutableFloatStateOf(0.8f) }
    var credibilityLevel by remember { mutableFloatStateOf(0.6f) }

    var showError by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    // Las 5 categorías obligatorias
    val categories = mapOf(
        "ovnis" to "👽 OVNIs",
        "lugares" to "🗺 Lugares misteriosos",
        "naturales" to "🌋 Fenómenos naturales",
        "criaturas" to "🐾 Criaturas",
        "historicos" to "📜 Casos históricos"
    )

    // Países disponibles
    val countries = mapOf(
        "mx" to "🇲🇽 México",
        "us" to "🇺🇸 Estados Unidos",
        "uk" to "🇬🇧 Reino Unido",
        "jp" to "🇯🇵 Japón"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reportar Fenómeno") },
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
            Text(
                text = "Registrar nuevo avistamiento",
                style = MaterialTheme.typography.titleLarge
            )

            // Nombre del fenómeno
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre del fenómeno") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = showError && name.isBlank()
            )
            if (showError && name.isBlank()) {
                Text("El nombre es obligatorio", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
            }

            // SELECCIÓN DE CATEGORÍA (Radio Buttons - Sección 3)
            Text("Selecciona la Categoría:", style = MaterialTheme.typography.titleMedium)
            categories.forEach { (key, label) ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedCategory == key,
                        onClick = { selectedCategory = key }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = label)
                }
            }

            // SELECCIÓN DE PAÍS (Radio Buttons - Sección 3)
            Text("Selecciona el País:", style = MaterialTheme.typography.titleMedium)
            countries.forEach { (key, label) ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedCountry == key,
                        onClick = { selectedCountry = key }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = label)
                }
            }

            // Año del suceso
            OutlinedTextField(
                value = year,
                onValueChange = { year = it },
                label = { Text("Año del suceso") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Campos de entrada adicionales (Sección 1)
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo electrónico") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Teléfono") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = witnessesCount,
                onValueChange = { witnessesCount = it },
                label = { Text("Número de testigos") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña de seguridad") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descripción detallada") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )

            // Sliders para niveles (Sección 3)
            Text("Nivel estimado de misterio: ${(mysteryLevel * 100).toInt()}%")
            Slider(value = mysteryLevel, onValueChange = { mysteryLevel = it })

            Text("Nivel de credibilidad: ${(credibilityLevel * 100).toInt()}%")
            Slider(value = credibilityLevel, onValueChange = { credibilityLevel = it })

            Button(
                onClick = {
                    if (name.isBlank()) {
                        showError = true
                    } else {
                        val newCase = MysteryCaseUi(
                            id = System.currentTimeMillis().toString(),
                            title = name,
                            category = selectedCategory,
                            country = selectedCountry,
                            year = year.ifBlank { "2026" },
                            description = description.ifBlank { "Caso reportado por la comunidad." },
                            imageRes = R.drawable.ovnis // <--- ¡Añade esto! (puedes usar R.drawable.ovnis o la imagen por defecto que prefieras para los reportes)
                        )
                        onReportSubmitted(newCase)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar avistamiento")
            }
        }
    }
}