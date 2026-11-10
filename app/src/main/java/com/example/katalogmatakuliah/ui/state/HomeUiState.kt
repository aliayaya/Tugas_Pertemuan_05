package com.example.katalogmatakuliah.ui.state

import com.example.katalogmatakuliah.data.Course
import com.example.katalogmatakuliah.datastore.SortOrder

data class HomeUiState(
    val courses: List<Course> = emptyList(),
    val searchQuery: String = "",
    val sortOrder: SortOrder = SortOrder.BY_NAME,
    val isEmpty: Boolean = false,
    val totalCount: Int = 0,
)
