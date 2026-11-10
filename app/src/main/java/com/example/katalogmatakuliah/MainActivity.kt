package com.example.katalogmatakuliah

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.katalogmatakuliah.data.CourseRepository
import com.example.katalogmatakuliah.datastore.UserPreferencesRepository
import com.example.katalogmatakuliah.navigation.AppNavHost
import com.example.katalogmatakuliah.ui.theme.KatalogMataKuliahTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        CourseRepository.initialize(applicationContext)
        val userPreferencesRepository = UserPreferencesRepository.getInstance(applicationContext)

        setContent {
            val isDarkMode: Boolean? by userPreferencesRepository.isDarkModeFlow.collectAsStateWithLifecycle(initialValue = null)

            if (isDarkMode != null) {
                KatalogMataKuliahTheme(darkTheme = isDarkMode!!) {
                    AppNavHost()
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF101720)),
                )
            }
        }
    }
}
