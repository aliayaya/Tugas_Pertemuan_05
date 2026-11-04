package com.example.katalogmatakuliah.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.katalogmatakuliah.data.Course
import com.example.katalogmatakuliah.data.CourseRepository
import com.example.katalogmatakuliah.ui.state.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadCourses()
    }

    fun loadCourses() {
        val allCourses = CourseRepository.getCourses()
        val query = _uiState.value.searchQuery
        filterCourses(allCourses, query)
    }

    fun onSearchQueryChanged(query: String) {
        val allCourses = CourseRepository.getCourses()
        filterCourses(allCourses, query)
    }

    private fun filterCourses(allCourses: List<Course>, query: String) {
        val filtered = if (query.isBlank()) {
            allCourses
        } else {
            allCourses.filter { course ->
                course.name.contains(query, ignoreCase = true) ||
                        course.code.contains(query, ignoreCase = true)
            }
        }
        _uiState.value = HomeUiState(
            courses = filtered,
            searchQuery = query,
            isEmpty = filtered.isEmpty(),
            totalCount = allCourses.size,
        )
    }
}
