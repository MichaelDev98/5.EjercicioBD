package com.example.ejerciciobd.Screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejerciciobd.Model.User
import com.example.ejerciciobd.Repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun UserApp(userRepository: UserRepository) {
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var userId by remember { mutableStateOf("") }
    var idDelete by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFF0F0F0))
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "APP FORMULARIO CON BASE DE DATOS",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6200EE),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = apellido,
            onValueChange = { apellido = it },
            label = { Text("Apellido") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = edad,
            onValueChange = { edad = it },
            label = { Text("Edad") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val user = User(
                    nombre = nombre,
                    apellido = apellido,
                    edad = edad.toIntOrNull() ?: 0
                )
                scope.launch {
                    withContext(Dispatchers.IO) {
                        userRepository.insert(user)
                    }
                    Toast.makeText(context, "Usuario registrado", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        var users by remember { mutableStateOf(listOf<User>()) }
        Button(
            onClick = {
                scope.launch {
                    users = withContext(Dispatchers.IO) {
                        userRepository.getAllUsers()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Listar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = idDelete,
            onValueChange = { idDelete = it },
            label = { Text("ID del Usuario a Eliminar") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val id = idDelete.toIntOrNull()
                if (id != null) {
                    scope.launch {
                        val deletedCount: Int
                        withContext(Dispatchers.IO) {
                            deletedCount = userRepository.deleteById(id)
                        }
                        if (deletedCount > 0) {
                            Toast.makeText(context, "Usuario eliminado", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Usuario no existe", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(context, "ID no válido", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Eliminar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = userId,
            onValueChange = { userId = it },
            label = { Text("ID del Usuario a Actualizar") },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val id = userId.toIntOrNull()
                if (id != null) {
                    scope.launch {
                        val updatedCount: Int
                        withContext(Dispatchers.IO) {
                            updatedCount = userRepository.updateUser(id, nombre, apellido, edad.toIntOrNull() ?: 0)
                        }
                        if (updatedCount > 0) {
                            Toast.makeText(context, "Usuario actualizado", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Usuario no existe", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(context, "ID no válido", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Actualizar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column {
            users.forEach { user ->
                Text("ID: ${user.id}, Nombre: ${user.nombre} ${user.apellido}, Edad: ${user.edad}")
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}