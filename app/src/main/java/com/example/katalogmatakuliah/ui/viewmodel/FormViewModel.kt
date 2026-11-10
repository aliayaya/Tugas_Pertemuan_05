package com.example.katalogmatakuliah.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.katalogmatakuliah.data.CourseRepository
import com.example.katalogmatakuliah.ui.state.FormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FormViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(FormUiState())
    val uiState: StateFlow<FormUiState> = _uiState.asStateFlow()

    private var isInitialized = false
    private var initializedForId: Int? = null

    init {
        CourseRepository.initialize(application)
    }

    fun initForm(courseId: Int?) {
        if (isInitialized && (initializedForId == courseId)) return

        isInitialized = true
        initializedForId = courseId

        if (courseId != null) {
            viewModelScope.launch {
                val existing = CourseRepository.getCourseById(courseId)
                if (existing != null) {
                    _uiState.value = FormUiState(
                        courseId = courseId,
                        isEditMode = true,
                        code = existing.code,
                        name = existing.name,
                        sksText = existing.sks.toString(),
                        semesterText = existing.semester.toString(),
                        description = existing.description,
                    )
                } else {
                    _uiState.value = FormUiState(
                        courseId = null,
                        isEditMode = false,
                    )
                }
            }
            return
        }

        _uiState.value = FormUiState(
            courseId = null,
            isEditMode = false,
        )
    }

    fun onCodeChanged(code: String) {
        _uiState.value = _uiState.value.copy(
            code = code,
            codeError = if (code.isBlank()) _uiState.value.codeError else null
        )
    }

    fun onNameChanged(name: String) {
        _uiState.value = _uiState.value.copy(
            name = name,
            nameError = if (name.isBlank()) _uiState.value.nameError else null
        )
    }

    fun onSksChanged(sksText: String) {
        _uiState.value = _uiState.value.copy(
            sksText = sksText,
            sksError = null
        )
    }

    fun onSemesterChanged(semesterText: String) {
        _uiState.value = _uiState.value.copy(
            semesterText = semesterText,
            semesterError = null
        )
    }

    fun onDescriptionChanged(description: String) {
        _uiState.value = _uiState.value.copy(description = description)
    }

    fun saveCourse(): Boolean {
        val state = _uiState.value
        var isValid = true

        var codeErr: String? = null
        if (state.code.isBlank()) {
            codeErr = "Kode mata kuliah tidak boleh kosong"
            isValid = false
        }

        var nameErr: String? = null
        if (state.name.isBlank()) {
            nameErr = "Nama mata kuliah tidak boleh kosong"
            isValid = false
        }

        val sksVal = state.sksText.toIntOrNull()
        var sksErr: String? = null
        if ((sksVal == null) || (sksVal <= 0)) {
            sksErr = "SKS harus angka positif (>0)"
            isValid = false
        }

        val semVal = state.semesterText.toIntOrNull()
        var semErr: String? = null
        if ((semVal == null) || (semVal <= 0)) {
            semErr = "Semester harus angka positif (>0)"
            isValid = false
        }

        if (!isValid) {
            _uiState.value = state.copy(
                codeError = codeErr,
                nameError = nameErr,
                sksError = sksErr,
                semesterError = semErr
            )
            return false
        }

        if ((sksVal != null) && (semVal != null)) {
            viewModelScope.launch {
                if (state.isEditMode && state.courseId != null) {
                    CourseRepository.updateCourse(
                        id = state.courseId,
                        code = state.code,
                        name = state.name,
                        sks = sksVal,
                        semester = semVal,
                        description = state.description
                    )
                } else {
                    CourseRepository.addCourse(
                        code = state.code,
                        name = state.name,
                        sks = sksVal,
                        semester = semVal,
                        description = state.description
                    )
                }
                isInitialized = false
                _uiState.value = state.copy(isSaveSuccess = true)
            }
            return true
        }

        return false
    }

    fun resetSaveSuccess() {
        _uiState.value = _uiState.value.copy(isSaveSuccess = false)
    }
}
