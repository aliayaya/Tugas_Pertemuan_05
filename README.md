# Katalog Mata Kuliah

Aplikasi Android berbasis Kotlin dan Jetpack Compose untuk mengelola katalog data mata kuliah program studi Informatika. Aplikasi ini dirancang menggunakan arsitektur MVVM (Model-View-ViewModel) dengan Unidirectional Data Flow (UDF) dan StateFlow untuk Tugas Pertemuan 6 Pemrograman Bergerak.

## Identitas Mahasiswa

- **Nama**: Alia Fellanitami Achyar
- **NIM**: 0102523010
- **Program Studi**: Informatika
- **Universitas**: Universitas Al Azhar Indonesia
- **Mata Kuliah**: Pemrograman Bergerak

---

## Fitur Utama

1. **Informasi Mahasiswa & Daftar Mata Kuliah (Home)**
   - Menampilkan kartu identitas mahasiswa.
   - Fitur pencarian kueri real-time berdasarkan kode atau nama mata kuliah.
   - Menampilkan daftar mata kuliah Informatika menggunakan `LazyColumn`.
   - Penanganan *empty state* jika hasil pencarian tidak ditemukan.

2. **Detail Mata Kuliah**
   - Menampilkan rincian lengkap mata kuliah berdasarkan ID yang dipilih dari route.
   - Menyediakan tombol untuk mengedit data.
   - Penanganan *non-happy path* jika ID mata kuliah tidak ditemukan.

3. **Form Tambah & Edit Mata Kuliah**
   - Form serbaguna untuk menambahkan mata kuliah baru maupun memperbarui mata kuliah yang sudah ada.
   - State form terjaga utuh saat terjadi rotasi layar (*configuration change*).
   - Validasi input (kode dan nama wajib diisi, SKS dan semester harus berupa angka positif).
   - Menyediakan tombol Simpan dan Batal.

---

## Arsitektur & Teknologi

- **Bahasa**: Kotlin
- **UI Toolkit**: Jetpack Compose & Material 3 (Tema Dark Slate)
- **Arsitektur**: MVVM + Unidirectional Data Flow (UDF)
- **State Management**: `StateFlow` (`asStateFlow()`) & `collectAsStateWithLifecycle()`
- **State Holders**: `HomeViewModel`, `DetailViewModel`, `FormViewModel`
- **Navigasi**: Navigation Compose dengan Type-Safe Routes (`kotlinx.serialization`)
- **Penyimpanan Data**: In-Memory Storage (`CourseRepository`)

---

## Route & Halaman

- `HomeRoute`: Halaman utama berisi identitas mahasiswa, pencarian, dan daftar mata kuliah.
- `DetailRoute(courseId: Int)`: Halaman detail mata kuliah berdasarkan ID.
- `FormRoute(courseId: Int? = null)`: Halaman form tambah (jika `courseId` null) atau edit (jika `courseId` terisi).

---

## Cara Menjalankan Project

1. Buka folder project **KatalogMataKuliah** di **Android Studio**.
2. Pastikan koneksi internet aktif untuk download dependensi Gradle.
3. Lakukan Sync Gradle dengan memilih menu **File > Sync Project with Gradle Files**.
4. Jalankan emulator Android atau hubungkan perangkat fisik via USB Debugging.
5. Klik tombol **Run ('app')** atau tekan `Shift + F10`.

---

## Deklarasi Penggunaan AI

Aplikasi ini dikembangkan dengan bantuan kecerdasan buatan (AI) sebagai alat bantu (*assistant tool*) untuk mempermudah pemahaman konsep Jetpack Compose, ViewModel, StateFlow, penyusunan arsitektur navigasi type-safe, serta perapihan antarmuka sesuai dengan panduan tugas.
