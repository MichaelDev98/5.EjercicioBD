package com.example.ejerciciobd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.ejerciciobd.DAO.UserDao
import com.example.ejerciciobd.Database.UserDatabase
import com.example.ejerciciobd.Repository.UserRepository
import com.example.ejerciciobd.Screen.UserApp

class MainActivity : ComponentActivity() {
    private lateinit var userDao: UserDao
    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = UserDatabase.getDatabase(applicationContext)
        userDao = db.userDao()
        userRepository = UserRepository(userDao)
        enableEdgeToEdge()
        setContent {
            UserApp(userRepository)
        }
    }
}

