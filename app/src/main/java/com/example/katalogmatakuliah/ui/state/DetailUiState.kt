package com.example.katalogmatakuliah.ui.state

import com.example.katalogmatakuliah.data.Course

data class DetailUiState(
    val course: Course? = null,
    val isNotFound: Boolean = false
)
