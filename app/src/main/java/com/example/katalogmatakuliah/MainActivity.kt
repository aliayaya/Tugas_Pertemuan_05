package com.example.katalogmatakuliah

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.katalogmatakuliah.navigation.AppNavHost
import com.example.katalogmatakuliah.ui.theme.KatalogMataKuliahTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KatalogMataKuliahTheme {
                AppNavHost()
            }
        }
    }
}
