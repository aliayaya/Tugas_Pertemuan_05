package com.example.katalogmatakuliah.ui.state

import com.example.katalogmatakuliah.datastore.SortOrder

data class SettingsUiState(
    val isDarkMode: Boolean = true,
    val sortOrder: SortOrder = SortOrder.BY_NAME,
)
