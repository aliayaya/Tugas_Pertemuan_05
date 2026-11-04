package com.example.katalogmatakuliah.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.katalogmatakuliah.data.CourseRepository
import com.example.katalogmatakuliah.ui.state.DetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun loadCourse(courseId: Int) {
        val course = CourseRepository.getCourseById(courseId)
        if (course != null) {
            _uiState.value = DetailUiState(course = course, isNotFound = false)
        } else {
            _uiState.value = DetailUiState(course = null, isNotFound = true)
        }
    }
}
