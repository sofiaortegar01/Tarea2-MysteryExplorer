import 'package:flutter/material.dart';

void main() {
  runApp(const MysteryExplorerApp());
}

class MysteryExplorerApp extends StatelessWidget {
  const MysteryExplorerApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Mystery Explorer',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        brightness: Brightness.dark,
        scaffoldBackgroundColor: const Color(0xFF1A1225),
        primaryColor: const Color(0xFF5B3580),
        colorScheme: const ColorScheme.dark(
          primary: Color(0xFF5B3580),
          secondary: Color(0xFFFFC107),
          surface: Color(0xFF291B3B),
        ),
      ),
      home: const HomeScreen(),
    );
  }
}

// --- 1. PANTALLA DE INICIO ---
class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Mystery Explorer (Flutter)'),
        backgroundColor: const Color(0xFF3B2054),
      ),
      body: ListView(
        padding: const EdgeInsets.all(16.0),
        children: [
          const Text(
            'Explora los misterios',
            style: TextStyle(fontSize: 22, fontWeight: FontWeight.bold, color: Colors.white),
          ),
          const SizedBox(height: 16),
          CategoryCard(
            title: 'OVNIs',
            subtitle: 'Avistamientos y luces',
            icon: '👽',
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => const CountryScreen(categoryName: 'OVNIs')),
              );
            },
          ),
          CategoryCard(
            title: 'Lugares misteriosos',
            subtitle: 'Sitios con leyendas',
            icon: '🗺',
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => const CountryScreen(categoryName: 'Lugares misteriosos')),
              );
            },
          ),
          CategoryCard(
            title: 'Criaturas',
            subtitle: 'Seres y críptidos',
            icon: '🐾',
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => const CountryScreen(categoryName: 'Criaturas')),
              );
            },
          ),
        ],
      ),
      floatingActionButton: FloatingActionButton.extended(
        onPressed: () {
          Navigator.push(
            context,
            MaterialPageRoute(builder: (context) => const ReportScreen()),
          );
        },
        label: const Text('Reportar'),
        icon: const Text('📝'),
        backgroundColor: const Color(0xFFFFC107),
        foregroundColor: const Color(0xFF1A1225),
      ),
    );
  }
}

class CategoryCard extends StatelessWidget {
  final String title;
  final String subtitle;
  final String icon;
  final VoidCallback onTap;

  const CategoryCard({
    super.key,
    required this.title,
    required this.subtitle,
    required this.icon,
    required this.onTap,
  });

  @override
  Widget build(BuildContext context) {
    return Card(
      color: const Color(0xFF291B3B),
      margin: const EdgeInsets.only(bottom: 12),
      child: ListTile(
        leading: Text(icon, style: const TextStyle(fontSize: 32)),
        title: Text(title, style: const TextStyle(color: Colors.white, fontWeight: FontWeight.bold)),
        subtitle: Text(subtitle, style: const TextStyle(color: Colors.white70)),
        onTap: onTap,
      ),
    );
  }
}

// --- 2. PANTALLA DE PAÍSES ---
class CountryScreen extends StatelessWidget {
  final String categoryName;
  const CountryScreen({super.key, required this.categoryName});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Países: $categoryName'),
        backgroundColor: const Color(0xFF3B2054),
      ),
      body: ListView(
        padding: const EdgeInsets.all(16.0),
        children: [
          ListTile(
            title: const Text('🇲🇽 México', style: TextStyle(color: Colors.white, fontSize: 18)),
            onTap: () {
              // Navegar a casos
            },
          ),
          ListTile(
            title: const Text('🇺🇸 Estados Unidos', style: TextStyle(color: Colors.white, fontSize: 18)),
            onTap: () {
              // Navegar a casos
            },
          ),
        ],
      ),
    );
  }
}

// --- 3. PANTALLA DE REPORTE (Con elementos de selección y entrada) ---
class ReportScreen extends StatefulWidget {
  const ReportScreen({super.key});

  @override
  State<ReportScreen> createState() => _ReportScreenState();
}

class _ReportScreenState extends State<ReportScreen> {
  final _nameController = TextEditingController();
  bool _isConfirmed = false;
  double _mysteryLevel = 0.5;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Reportar Fenómeno'),
        backgroundColor: const Color(0xFF3B2054),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: ListView(
          children: [
            TextField(
              controller: _nameController,
              decoration: const InputDecoration(
                labelText: 'Nombre del fenómeno',
                labelStyle: TextStyle(color: Colors.white70),
                enabledBorder: UnderlineInputBorder(borderSide: BorderSide(color: Colors.white54)),
              ),
            ),
            const SizedBox(height: 20),
            SwitchListTile(
              title: const Text('¿Confirmar veracidad?'),
              value: _isConfirmed,
              onChanged: (bool value) {
                setState(() {
                  _isConfirmed = value;
                });
              },
            ),
            const SizedBox(height: 20),
            const Text('Nivel de misterio estimado', style: TextStyle(color: Colors.white70)),
            Slider(
              value: _mysteryLevel,
              onChanged: (double value) {
                setState(() {
                  _mysteryLevel = value;
                });
              },
            ),
            const SizedBox(height: 20),
            ElevatedButton(
              style: ElevatedButton.styleFrom(backgroundColor: const Color(0xFFFFC107)),
              onPressed: () {
                if (_nameController.text.isEmpty) {
                  ScaffoldMessenger.of(context).showSnackBar(
                    const SnackBar(content: Text('El nombre es obligatorio')),
                  );
                } else {
                  Navigator.pop(context);
                }
              },
              child: const Text('Guardar Reporte', style: TextStyle(color: Color(0xFF1A1225))),
            ),
          ],
        ),
      ),
    );
  }
}
