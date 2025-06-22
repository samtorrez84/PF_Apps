# Aplicación de Reciclaje

## Descripción
ReciclApp es una aplicación diseñada para fomentar el reciclaje. Permite a los usuarios explorar información sobre materiales reciclables, localizar puntos de acopio cercanos y utilizar funcionalidades como el escaneo de materiales mediante TensorFlow Lite.

## Funcionalidades Principales
- *Pantalla de Bienvenida*: Introducción a la aplicación.
- *Navegación Intuitiva*: Barra de navegación inferior para acceder rápidamente a las secciones principales.
- *Exploración de Materiales*: Información detallada sobre materiales reciclables como plástico, vidrio, composta, entre otros.
- *Mapa Interactivo*: Localización de puntos de acopio cercanos con marcadores personalizados.
- *Escaneo de Materiales*: Identificación de materiales reciclables utilizando TensorFlow Lite.
- *Opciones Personalizadas*: Configuración de preferencias del usuario.

## Instalación y Ejecución
1. Clona el repositorio:
   ```bash
   git clone https://github.com/samtorrez84/PF_Apps.git
2. Abre el proyecto en Android Studio.
3. Asegúrate de que las dependencias estén actualizadas en el archivo build.gradle.
5. Ejecuta la aplicación desde Android Studio.

## Nuevas Funcionalidades
- *Mapa Interactivo*: Navega a la pantalla de mapa para ver puntos de acopio cercanos, soporte para marcadores dinámicos basados en coordenadas.
- *Escaneo de Materiales*: Utiliza TensorFlow Lite para identificar materiales reciclables mediante la cámara del dispositivo. Accede a esta funcionalidad desde la pestaña "Cámara".
- *Navegación Animada*: Transiciones animadas entre pantallas para una experiencia más fluida.
- *Exploración de Materiales*: Accede a información detallada sobre diferentes tipos de materiales reciclables desde la pestaña "Explorar".

## Requisitos
- *Android Studio*: Versión Meerkat | 2024.3.1 o superior.
- *SDK de Android*: Compile SDK 35, Target SDK 35, Min SDK 24.
- *Kotlin*: JVM Target 11.
- *Gradle*: Configurado en el proyecto.

### Dependencias
- *TensorFlow Lite*: Para el escaneo de materiales.
- *MapLibre*: Para la funcionalidad de mapa interactivo.
- *Accompanist Navigation Animation*: Para transiciones animadas entre pantallas.

## Equipo:
- Ethan Abraham Sánchez Téllez,
- Samuel Alejandro Torrez Oropeza,
- Mariano Ugalde Díaz
