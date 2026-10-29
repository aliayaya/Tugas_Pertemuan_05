package com.example.katalogmatakuliah.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.katalogmatakuliah.data.CourseRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(
    courseId: Int?,
    onBackClick: () -> Unit,
    onSaveSuccess: () -> Unit
) {
    val existingCourse = remember(courseId) {
        courseId?.let { CourseRepository.getCourseById(it) }
    }

    val isEditMode = courseId != null

    var code by remember { mutableStateOf(existingCourse?.code ?: "") }
    var name by remember { mutableStateOf(existingCourse?.name ?: "") }
    var sksText by remember { mutableStateOf(existingCourse?.sks?.toString() ?: "3") }
    var semesterText by remember { mutableStateOf(existingCourse?.semester?.toString() ?: "1") }
    var description by remember { mutableStateOf(existingCourse?.description ?: "") }

    var codeError by remember { mutableStateOf<String?>(null) }
    var nameError by remember { mutableStateOf<String?>(null) }
    var sksError by remember { mutableStateOf<String?>(null) }
    var semesterError by remember { mutableStateOf<String?>(null) }

    fun validateAndSave() {
        var isValid = true

        if (code.isBlank()) {
            codeError = "Kode mata kuliah tidak boleh kosong"
            isValid = false
        } else {
            codeError = null
        }

        if (name.isBlank()) {
            nameError = "Nama mata kuliah tidak boleh kosong"
            isValid = false
        } else {
            nameError = null
        }

        val sksVal = sksText.toIntOrNull()
        if (sksVal == null || sksVal <= 0) {
            sksError = "SKS harus angka positif (>0)"
            isValid = false
        } else {
            sksError = null
        }

        val semVal = semesterText.toIntOrNull()
        if (semVal == null || semVal <= 0) {
            semesterError = "Semester harus angka positif (>0)"
            isValid = false
        } else {
            semesterError = null
        }

        if (isValid && sksVal != null && semVal != null) {
            if (courseId != null) {
                CourseRepository.updateCourse(
                    id = courseId,
                    code = code,
                    name = name,
                    sks = sksVal,
                    semester = semVal,
                    description = description
                )
            } else {
                CourseRepository.addCourse(
                    code = code,
                    name = name,
                    sks = sksVal,
                    semester = semVal,
                    description = description
                )
            }
            onSaveSuccess()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (isEditMode) "Edit Mata Kuliah" else "Tambah Mata Kuliah",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Field Kode Mata Kuliah
            OutlinedTextField(
                value = code,
                onValueChange = {
                    code = it
                    if (codeError != null) codeError = null
                },
                label = { Text("Kode Mata Kuliah") },
                placeholder = { Text("Contoh: IF-401") },
                isError = codeError != null,
                supportingText = codeError?.let { { Text(it, color = MaterialTheme.colorScheme.error) } },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = textFieldColors()
            )

            // Field Nama Mata Kuliah
            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                    if (nameError != null) nameError = null
                },
                label = { Text("Nama Mata Kuliah") },
                placeholder = { Text("Contoh: Pemrograman Bergerak") },
                isError = nameError != null,
                supportingText = nameError?.let { { Text(it, color = MaterialTheme.colorScheme.error) } },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = textFieldColors()
            )

            // Field SKS & Semester
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = sksText,
                    onValueChange = {
                        sksText = it
                        if (sksError != null) sksError = null
                    },
                    label = { Text("Jumlah SKS") },
                    isError = sksError != null,
                    supportingText = sksError?.let { { Text(it, color = MaterialTheme.colorScheme.error) } },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp),
                    colors = textFieldColors()
                )

                OutlinedTextField(
                    value = semesterText,
                    onValueChange = {
                        semesterText = it
                        if (semesterError != null) semesterError = null
                    },
                    label = { Text("Semester") },
                    isError = semesterError != null,
                    supportingText = semesterError?.let { { Text(it, color = MaterialTheme.colorScheme.error) } },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp),
                    colors = textFieldColors()
                )
            }

            // Field Deskripsi
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Deskripsi Singkat") },
                placeholder = { Text("Penjelasan singkat materi mata kuliah...") },
                minLines = 3,
                maxLines = 5,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = textFieldColors()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Action Buttons (Simpan & Batal)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = onBackClick,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Batal")
                }

                Button(
                    onClick = { validateAndSave() },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("Simpan")
                }
            }
        }
    }
}

@Composable
private fun textFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = MaterialTheme.colorScheme.primary,
    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
    focusedLabelColor = MaterialTheme.colorScheme.primary,
    unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
    focusedContainerColor = MaterialTheme.colorScheme.surface,
    unfocusedContainerColor = MaterialTheme.colorScheme.surface
)
