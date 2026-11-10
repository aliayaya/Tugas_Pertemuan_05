package com.example.katalogmatakuliah.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.katalogmatakuliah.data.CourseRepository
import com.example.katalogmatakuliah.datastore.SortOrder
import com.example.katalogmatakuliah.datastore.UserPreferencesRepository
import com.example.katalogmatakuliah.ui.state.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val userPreferencesRepository = UserPreferencesRepository.getInstance(application)
    private val searchQueryFlow = MutableStateFlow("")

    init {
        CourseRepository.initialize(application)
    }

    val uiState: StateFlow<HomeUiState> = combine(
        CourseRepository.getCoursesFlow(),
        searchQueryFlow,
        userPreferencesRepository.sortOrderFlow
    ) { courses, query, sortOrder ->
        val filtered = if (query.isBlank()) {
            courses
        } else {
            courses.filter { course ->
                course.name.contains(query, ignoreCase = true) ||
                        course.code.contains(query, ignoreCase = true)
            }
        }

        val sorted = when (sortOrder) {
            SortOrder.BY_NAME -> filtered.sortedBy { it.name }
            SortOrder.BY_CODE -> filtered.sortedBy { it.code }
            SortOrder.BY_SEMESTER -> filtered.sortedBy { it.semester }
        }

        HomeUiState(
            courses = sorted,
            searchQuery = query,
            sortOrder = sortOrder,
            isEmpty = sorted.isEmpty(),
            totalCount = courses.size
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeUiState()
    )

    fun onSearchQueryChanged(query: String) {
        searchQueryFlow.value = query
    }

    fun deleteCourse(courseId: Int) {
        viewModelScope.launch {
            CourseRepository.deleteCourse(courseId)
        }
    }
}
