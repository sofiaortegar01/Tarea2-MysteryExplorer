package com.gmail.sofiaortegar01.mysteryexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.gmail.sofiaortegar01.mysteryexplorer.ui.HomeScreen
import com.gmail.sofiaortegar01.mysteryexplorer.ui.theme.MysteryExplorerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MysteryExplorerTheme {
                // Surface aplica el color de fondo por defecto de nuestro tema (claro/oscuro)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Llamamos a nuestra pantalla de inicio que acabamos de crear
                    HomeScreen(
                        onCategoryClick = { categoryId ->
                            // TODO: Aquí programaremos la navegación hacia la pantalla de Países
                        },
                        onSearchQueryChanged = { query ->
                            // TODO: Aquí manejaremos la lógica de búsqueda en tiempo real
                        }
                    )
                }
            }
        }
    }
}