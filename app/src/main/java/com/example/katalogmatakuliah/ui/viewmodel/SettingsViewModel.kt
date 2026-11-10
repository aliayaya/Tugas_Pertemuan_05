package com.example.katalogmatakuliah.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.katalogmatakuliah.datastore.SortOrder
import com.example.katalogmatakuliah.datastore.UserPreferencesRepository
import com.example.katalogmatakuliah.ui.state.SettingsUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    private val userPreferencesRepository = UserPreferencesRepository.getInstance(application)

    val uiState: StateFlow<SettingsUiState> = combine(
        userPreferencesRepository.isDarkModeFlow,
        userPreferencesRepository.sortOrderFlow
    ) { isDarkMode, sortOrder ->
        SettingsUiState(isDarkMode = isDarkMode, sortOrder = sortOrder)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SettingsUiState()
    )

    fun setDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            userPreferencesRepository.setDarkMode(enabled)
        }
    }

    fun setSortOrder(sortOrder: SortOrder) {
        viewModelScope.launch {
            userPreferencesRepository.setSortOrder(sortOrder)
        }
    }
}
