# Aplicacion formulario con bases de datos local

Esta es una aplicación de Android desarrollada con Kotlin y Jetpack Compose para registrar y gestionar usuarios en una base de datos local utilizando Room.

## Descripción

La aplicación permite a los usuarios registrar, listar, actualizar y eliminar registros de usuarios en una base de datos local. La interfaz de usuario está construida con Jetpack Compose, y la base de datos se maneja con Room.

## Estructura del Proyecto

El proyecto está compuesto por los siguientes archivos:

- **UserDao.kt**: Define las operaciones de acceso a la base de datos.
- **UserDatabase.kt**: Configura la base de datos Room.
- **User.kt**: Define la entidad `User`.
- **UserRepository.kt**: Proporciona una capa de abstracción sobre `UserDao`.
- **Items.kt**: Contiene la interfaz de usuario construida con Jetpack Compose.
- **MainActivity.kt**: Configura la actividad principal y la inicialización de la base de datos y el repositorio.

## Uso

### Registrar un Usuario

1. Ingresa el nombre, apellido y edad del usuario en los campos correspondientes.
2. Haz clic en el botón "Registrar" para guardar el usuario en la base de datos.

### Listar Usuarios

1. Haz clic en el botón "Listar" para obtener y mostrar todos los usuarios registrados.

### Actualizar un Usuario

1. Ingresa el ID del usuario a actualizar en el campo correspondiente.
2. Ingresa los nuevos datos del usuario (nombre, apellido, edad).
3. Haz clic en el botón "Actualizar" para guardar los cambios.

### Eliminar un Usuario

1. Ingresa el ID del usuario a eliminar en el campo correspondiente.
2. Haz clic en el botón "Eliminar" para borrar el usuario de la base de datos.

## Tecnologías Utilizadas

- **Kotlin**: Lenguaje de programación.
- **Jetpack Compose**: Framework para la interfaz de usuario.
- **Room**: Biblioteca de persistencia de datos.
- **Coroutines**: Manejo de operaciones asíncronas.

## Elaborado por:

- Michael Alexander Corredor Castillo
