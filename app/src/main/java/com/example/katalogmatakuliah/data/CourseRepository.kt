package com.example.katalogmatakuliah.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

object CourseRepository {
    private val _courses: SnapshotStateList<Course> = mutableStateListOf(
        Course(
            id = 1,
            code = "IF-101",
            name = "Dasar-Dasar Pemrograman",
            sks = 3,
            semester = 1,
            description = "Mata kuliah ini mempelajari konsep dasar algoritma, struktur kontrol, fungsi, dan logika pemrograman dasar."
        ),
        Course(
            id = 2,
            code = "IF-102",
            name = "Matematika Diskrit",
            sks = 3,
            semester = 1,
            description = "Membahas tentang logika matematika, himpunan, relasi, fungsi, teori graf, dan kombinatorika untuk ilmu komputer."
        ),
        Course(
            id = 3,
            code = "IF-201",
            name = "Pemrograman Berorientasi Objek",
            sks = 3,
            semester = 2,
            description = "Mempelajari paradigma OOP seperti enkapsulasi, pewarisan, polimorfisme, dan abstraksi untuk membangun perangkat lunak terstruktur."
        ),
        Course(
            id = 4,
            code = "IF-202",
            name = "Struktur Data dan Algoritma",
            sks = 4,
            semester = 2,
            description = "Pembahasan struktur data seperti Array, Stack, Queue, Tree, dan Graph serta analisis kompleksitas algoritma pencarian dan pengurutan."
        ),
        Course(
            id = 5,
            code = "IF-301",
            name = "Basis Data",
            sks = 3,
            semester = 3,
            description = "Mempelajari pemodelan data relasional, ERD, normalisasi, dan pengoperasian database menggunakan SQL."
        ),
        Course(
            id = 6,
            code = "IF-302",
            name = "Pemrograman Web",
            sks = 3,
            semester = 3,
            description = "Pengembangan aplikasi berbasis web mencakup HTML, CSS, JavaScript, serta pembuatan RESTful API backend."
        ),
        Course(
            id = 7,
            code = "IF-401",
            name = "Pemrograman Bergerak",
            sks = 3,
            semester = 4,
            description = "Pengembangan aplikasi Android modern menggunakan bahasa Kotlin, Jetpack Compose, Material 3, dan Navigation Compose."
        ),
        Course(
            id = 8,
            code = "IF-402",
            name = "Rekayasa Perangkat Lunak",
            sks = 3,
            semester = 4,
            description = "Membahas tahapan SDLC, analisis kebutuhan sistem, desain arsitektur perangkat lunak, dan metode pengujian."
        )
    )

    private var nextId = 9

    fun getCourses(): List<Course> {
        return _courses
    }

    fun getCourseById(id: Int): Course? {
        return _courses.find { it.id == id }
    }

    fun addCourse(code: String, name: String, sks: Int, semester: Int, description: String): Course {
        val newCourse = Course(
            id = nextId++,
            code = code.trim(),
            name = name.trim(),
            sks = sks,
            semester = semester,
            description = description.trim()
        )
        _courses.add(newCourse)
        return newCourse
    }

    fun updateCourse(id: Int, code: String, name: String, sks: Int, semester: Int, description: String): Boolean {
        val index = _courses.indexOfFirst { it.id == id }
        if (index != -1) {
            _courses[index] = Course(
                id = id,
                code = code.trim(),
                name = name.trim(),
                sks = sks,
                semester = semester,
                description = description.trim()
            )
            return true
        }
        return false
    }
}
