package com.example.katalogmatakuliah.data

import android.content.Context
import com.example.katalogmatakuliah.data.local.CourseDao
import com.example.katalogmatakuliah.data.local.CourseDatabase
import com.example.katalogmatakuliah.data.local.CourseEntity
import com.example.katalogmatakuliah.data.local.toCourse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object CourseRepository {
    private var dao: CourseDao? = null

    fun initialize(context: Context) {
        if (dao == null) {
            val database = CourseDatabase.getInstance(context)
            dao = database.courseDao()
        }
    }

    private fun requireDao(): CourseDao {
        return dao ?: throw IllegalStateException("CourseRepository belum diinisialisasi dengan Context")
    }

    fun getCoursesFlow(): Flow<List<Course>> {
        return requireDao().getAllCoursesFlow().map { list ->
            list.map { it.toCourse() }
        }
    }

    suspend fun getCourseById(id: Int): Course? {
        return requireDao().getCourseById(id)?.toCourse()
    }

    suspend fun addCourse(code: String, name: String, sks: Int, semester: Int, description: String): Course {
        val entity = CourseEntity(
            id = 0,
            code = code.trim(),
            name = name.trim(),
            sks = sks,
            semester = semester,
            description = description.trim()
        )
        val generatedId = requireDao().insertCourse(entity)
        return entity.copy(id = generatedId.toInt()).toCourse()
    }

    suspend fun updateCourse(id: Int, code: String, name: String, sks: Int, semester: Int, description: String): Boolean {
        val entity = CourseEntity(
            id = id,
            code = code.trim(),
            name = name.trim(),
            sks = sks,
            semester = semester,
            description = description.trim()
        )
        val updatedRows = requireDao().updateCourse(entity)
        return updatedRows > 0
    }

    suspend fun deleteCourse(id: Int): Boolean {
        val existing = requireDao().getCourseById(id)
        return if (existing != null) {
            val deletedRows = requireDao().deleteCourse(existing)
            deletedRows > 0
        } else {
            false
        }
    }
}
