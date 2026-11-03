package com.example.katalogmatakuliah.ui.state

data class FormUiState(
    val courseId: Int? = null,
    val isEditMode: Boolean = false,
    val code: String = "",
    val name: String = "",
    val sksText: String = "3",
    val semesterText: String = "1",
    val description: String = "",
    val codeError: String? = null,
    val nameError: String? = null,
    val sksError: String? = null,
    val semesterError: String? = null,
    val isSaveSuccess: Boolean = false,
)
