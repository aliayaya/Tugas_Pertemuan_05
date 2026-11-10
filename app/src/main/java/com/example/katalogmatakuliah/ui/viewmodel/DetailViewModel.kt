package com.example.katalogmatakuliah.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.katalogmatakuliah.data.CourseRepository
import com.example.katalogmatakuliah.ui.state.DetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    init {
        CourseRepository.initialize(application)
    }

    fun loadCourse(courseId: Int) {
        viewModelScope.launch {
            val course = CourseRepository.getCourseById(courseId)
            if (course != null) {
                _uiState.value = DetailUiState(course = course, isNotFound = false)
            } else {
                _uiState.value = DetailUiState(course = null, isNotFound = true)
            }
        }
    }

    fun deleteCourse(courseId: Int, onDeleteSuccess: () -> Unit) {
        viewModelScope.launch {
            val deleted = CourseRepository.deleteCourse(courseId)
            if (deleted) {
                onDeleteSuccess()
            }
        }
    }
}
