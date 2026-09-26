#  Mystery Explorer

Aplicación móvil desarrollada para la exploración de fenómenos extraños en el mundo (OVNs, lugares misteriosos, fenómenos naturales, criaturas y casos históricos), diseñada cumpliendo con los elementos avanzados de interfaces de usuario en múltiples tecnologías.

---

##  1. Datos de Identificación
* **Nombre completo:** Sofía Ortega García
* **Número de boleta:** 2024630517
* **Grupo:** 7CV4
* **Escuela:** ESCOM - IPN (Ingeniería en Sistemas Computacionales)

---

##  2. Tecnologías Utilizadas
La aplicación fue implementada de manera equivalente en tres tecnologías distintas:
1. **Android Jetpack Compose + Kotlin** (Interfaz declarativa moderna).
2. **Android Views + XML + Kotlin** (Interfaz imperativa tradicional).
3. **Flutter + Dart** (Framework multiplataforma).

### Tabla de Equivalencias Tecnológicas

| Componente UI / Tarea | Jetpack Compose | Android Views (XML) | Flutter (Dart) |
| :--- | :--- | :--- | :--- |
| **Contenedor Principal** | `Scaffold` / `Column` | `ConstraintLayout` / `LinearLayout` | `Scaffold` / `Column` |
| **Listas y Colecciones** | `LazyColumn` / `LazyVerticalGrid` | `RecyclerView` / `GridView` | `ListView.builder` / `GridView.builder` |
| **Navegación** | Estado mutable (`sealed class`) | Fragments / Intents | Navigator 2.0 / `Navigator.push` |
| **Entrada de Texto** | `OutlinedTextField` | `EditText` | `TextField` |
| **Selección y Acciones** | `Switch`, `Slider`, `Tabs` | `Switch`, `SeekBar`, `TabLayout` | `Switch`, `Slider`, `TabBar` |

---

##  3. Instrucciones de Compilación y Ejecución

### Versión 1: Jetpack Compose (Kotlin)
1. Abrir la carpeta `android-compose/` en **Android Studio**.
2. Esperar a que Gradle sincronice las dependencias (`build.gradle.kts`).
3. Seleccionar un emulador o dispositivo físico con Android (SDK 24 o superior).
4. Hacer clic en el botón **Run **.

### Versión 2: Android Views + XML (Kotlin)
1. Abrir la carpeta `android-views/` en **Android Studio**.
2. Sincronizar el proyecto con los archivos Gradle.
3. Ejecutar sobre el emulador o dispositivo de prueba mediante el botón **Run**.

### Versión 3: Flutter (Dart)
1. Abrir la carpeta `flutter-app/` en **Android Studio** o **VS Code**.
2. Ejecutar el comando `flutter pub get` en la terminal para descargar paquetes.
3. Conectar un dispositivo o iniciar un emulador.
4. Ejecutar el comando `flutter run` o presionar F5.

---

##  4. Capturas de Pantalla de las Secciones
*(Las imágenes se encuentran almacenadas en la carpeta `docs/` del repositorio)*

* **Sección 1 (Entrada de Texto):** ![Entrada de texto](docs/seccion1_textinput.png)
* **Sección 2 (Botones y Acciones):** ![Botones](docs/seccion2_buttons.png)
* **Sección 3 (Elementos de Selección):** ![Selección](docs/seccion3_selection.png)
* **Sección 4 (Listas y Colecciones):** ![Listas](docs/seccion4_lists.png)
* **Sección 5 (Información y Retroalimentación):** ![Feedback](docs/seccion5_feedback.png)
* **Sección 6 (Contenedores y Estructura):** ![Contenedores](docs/seccion6_containers.png)

---

##  5. Reflexión Final

* **¿En cuál tecnología resultó más rápido construir la interfaz?**
  * *Jetpack Compose* permitió desarrollar las pantallas con mucha mayor agilidad al no requerir la sincronización constante entre archivos de diseño XML y clases de código lógico.
* **¿Cuál generó código más legible?**
  * *Flutter* y *Jetpack Compose* comparten un paradigma declarativo muy limpio, aunque Jetpack Compose destaca por su integración nativa y fluida con Kotlin.
* **Dificultades encontradas:**
  * En *Android Views*, la gestión de adaptadores para los `RecyclerViews` requirió mayor cantidad de código repetitivo (boilerplate). En *Flutter*, configurar la adaptabilidad exacta del grid adaptativo demandó ajustes adicionales de propiedades de diseño.
* **Tecnología preferida:**
  * *Jetpack Compose*, debido a su potencia, versatilidad y la ventaja directa de trabajar con un ecosistema moderno en Kotlin.

---

## 6. Referencias Consultadas
* Android Developers. (2026). *Jetpack Compose documentation*. Recuperado de https://developer.android.com/compose
* The Flutter Team. (2026). *Flutter UI documentation and widget catalog*. Recuperado de https://docs.flutter.dev/
* Google. (2026). *Material Design 3 Guidelines and Components*. Recuperado de https://m3.material.io/