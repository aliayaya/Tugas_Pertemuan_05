package com.example.katalogmatakuliah.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.katalogmatakuliah.data.Course

@Entity(tableName = "courses")
data class CourseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val code: String,
    val name: String,
    val sks: Int,
    val semester: Int,
    val description: String,
)

fun CourseEntity.toCourse(): Course = Course(
    id = id,
    code = code,
    name = name,
    sks = sks,
    semester = semester,
    description = description
)

fun Course.toEntity(): CourseEntity = CourseEntity(
    id = id,
    code = code,
    name = name,
    sks = sks,
    semester = semester,
    description = description
)
