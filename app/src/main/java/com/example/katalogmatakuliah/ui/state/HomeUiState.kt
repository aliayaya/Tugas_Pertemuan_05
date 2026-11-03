package com.example.katalogmatakuliah.ui.state

import com.example.katalogmatakuliah.data.Course

data class HomeUiState(
    val courses: List<Course> = emptyList(),
    val searchQuery: String = "",
    val isEmpty: Boolean = false,
    val totalCount: Int = 0,
)
