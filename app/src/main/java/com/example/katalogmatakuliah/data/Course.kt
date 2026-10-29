package com.example.katalogmatakuliah.data

import kotlinx.serialization.Serializable

@Serializable
data class Course(
    val id: Int,
    val code: String,
    val name: String,
    val sks: Int,
    val semester: Int,
    val description: String,
)
