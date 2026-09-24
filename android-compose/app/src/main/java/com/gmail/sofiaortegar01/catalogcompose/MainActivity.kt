package com.gmail.sofiaortegar01.catalogcompose
package com.example.catalogcompose // Asegúrate de que coincida con tu package

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MysteryAppNavigation()
                }
            }
        }
    }
}

@Composable
fun MysteryAppNavigation() {
    val navController = rememberNavController()

    // Estado compartido para cumplir la conexión entre Sección 1 y Sección 4
    val reportedSightings = remember {
        mutableStateListOf(
            "Objeto cilíndrico sobrevolando Popocatépetl",
            "Luz extraña en el Bosque de Chapultepec"
        )
    }

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController = navController) }
        composable("sec1") { Section1Screen(navController = navController, onAddSighting = { reportedSightings.add(it) }) }
        composable("sec2") { Section2Screen(navController = navController) }
        composable("sec3") { Section3Screen(navController = navController) }
        composable("sec4") { Section4Screen(navController = navController, sightings = reportedSightings) }
        composable("sec5") { Section5Screen(navController = navController) }
        composable("sec6") { Section6Screen(navController = navController) }
    }
}

// ---------------------------------------------------------
// HOME
// ---------------------------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Mystery Explorer - Compose") }) }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text("Selecciona una sección del catálogo:", style = MaterialTheme.typography.titleMedium)
            Button(onClick = { navController.navigate("sec1") }, modifier = Modifier.fillMaxWidth()) { Text("1. Entrada de Texto") }
            Button(onClick = { navController.navigate("sec2") }, modifier = Modifier.fillMaxWidth()) { Text("2. Botones y Acciones") }
            Button(onClick = { navController.navigate("sec3") }, modifier = Modifier.fillMaxWidth()) { Text("3. Elementos de Selección") }
            Button(onClick = { navController.navigate("sec4") }, modifier = Modifier.fillMaxWidth()) { Text("4. Listas y Colecciones") }
            Button(onClick = { navController.navigate("sec5") }, modifier = Modifier.fillMaxWidth()) { Text("5. Información y Retroalimentación") }
            Button(onClick = { navController.navigate("sec6") }, modifier = Modifier.fillMaxWidth()) { Text("6. Contenedores y Estructura") }
        }
    }
}

// ---------------------------------------------------------
// SECCIÓN 1: ENTRADA DE TEXTO (Conecta con Sección 4)
// ---------------------------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Section1Screen(navController: NavController, onAddSighting: (String) -> Unit) {
    var textSimple by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sección 1: Entrada de Texto") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Regresar")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Documentación: Los campos de texto permiten capturar información del usuario con validaciones y distintos tipos de teclado.", style = MaterialTheme.typography.bodySmall)

            OutlinedTextField(
                value = textSimple,
                onValueChange = { textSimple = it },
                label = { Text("Nombre del fenómeno / Avistamiento") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it; showError = !android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches() },
                label = { Text("Correo electrónico de contacto") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = showError,
                modifier = Modifier.fillMaxWidth()
            )
            if (showError) Text("Correo inválido", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña de acceso secreto") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff, contentDescription = null)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    if (textSimple.isNotBlank()) {
                        onAddSighting(textSimple)
                        navController.navigate("sec4") // Te lleva a la lista para ver el resultado agregado
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar y Enviar a la Lista (Sección 4)")
            }
        }
    }
}

// ---------------------------------------------------------
// SECCIÓN 2: BOTONES Y ACCIONES
// ---------------------------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Section2Screen(navController: NavController) {
    var clickedMessage by remember { mutableStateOf("Pulsa un botón") }
    var isLoading by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sección 2: Botones") },
                navigationIcon = { IconButton(onClick = { navController.popBackStack() }) { Icon(Icons.Default.ArrowBack, contentDescription = null) } }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(clickedMessage, style = MaterialTheme.typography.bodyLarge)
            Button(onClick = { clickedMessage = "¡Botón Relleno pulsado!" }) { Text("Botón Relleno") }
            OutlinedButton(onClick = { clickedMessage = "¡Botón con Contorno pulsado!" }) { Text("Botón con Contorno") }
            TextButton(onClick = { clickedMessage = "¡Botón de Texto pulsado!" }) { Text("Solo Texto") }
            Button(onClick = { isLoading = !isLoading }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) {
                Text(if (isLoading) "Cargando evidencia..." else "Simular Estado de Carga")
            }
        }
    }
}

// ---------------------------------------------------------
// SECCIÓN 3: ELEMENTOS DE SELECCIÓN
// ---------------------------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Section3Screen(navController: NavController) {
    var switchState by remember { mutableStateOf(false) }
    var sliderValue by remember { mutableStateOf(5f) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sección 3: Selección") },
                navigationIcon = { IconButton(onClick = { navController.popBackStack() }) { Icon(Icons.Default.ArrowBack, contentDescription = null) } }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text("Zona de Alto Riesgo (Switch)")
                Switch(checked = switchState, onCheckedChange = { switchState = it })
            }
            Text("Nivel de Credibilidad: ${sliderValue.toInt()} / 10")
            Slider(value = sliderValue, onValueChange = { sliderValue = it }, valueRange = 1f..10f)
        }
    }
}

// ---------------------------------------------------------
// SECCIÓN 4: LISTAS Y COLECCIONES (Recibe datos de Sec 1)
// ---------------------------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Section4Screen(navController: NavController, sightings: List<String>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sección 4: Lista de Avistamientos") },
                navigationIcon = { IconButton(onClick = { navController.popBackStack() }) { Icon(Icons.Default.ArrowBack, contentDescription = null) } }
            )
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            item {
                Text("Aquí se muestran los reportes (incluyendo el enviado desde la Sección 1):", style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(sightings) { sighting ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Text(text = sighting, modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

// ---------------------------------------------------------
// SECCIÓN 5: INFORMACIÓN Y RETROALIMENTACIÓN
// ---------------------------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Section5Screen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sección 5: Retroalimentación") },
                navigationIcon = { IconButton(onClick = { navController.popBackStack() }) { Icon(Icons.Default.ArrowBack, contentDescription = null) } }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("Indicador de Nivel de Radiación:", style = MaterialTheme.typography.titleMedium)
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}

// ---------------------------------------------------------
// SECCIÓN 6: CONTENEDORES Y ESTRUCTURA
// ---------------------------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Section6Screen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sección 6: Contenedores") },
                navigationIcon = { IconButton(onClick = { navController.popBackStack() }) { Icon(Icons.Default.ArrowBack, contentDescription = null) } }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Ejemplo de distribución en Columna y Filas:")
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(onClick = {}) { Text("Fila 1") }
                Button(onClick = {}) { Text("Fila 2") }
            }
        }
    }
}