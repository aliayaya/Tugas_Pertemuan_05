# Katalog Mata Kuliah

Aplikasi Android sederhana berbasis Kotlin dan Jetpack Compose untuk mengelola katalog data mata kuliah program studi Informatika. Aplikasi ini dibuat untuk memenuhi Tugas Pertemuan 5 mata kuliah Pemrograman Bergerak.

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
   - Menampilkan daftar mata kuliah Informatika (minimal 8 data bawaan) menggunakan `LazyColumn`.
   - Menampilkan informasi ringkas seperti nama, kode mata kuliah, jumlah SKS, dan semester.

2. **Detail Mata Kuliah**
   - Menampilkan rincian lengkap mata kuliah berdasarkan ID yang dipilih.
   - Menyediakan tombol untuk mengedit data.
   - Penanganan kondisi jika ID mata kuliah tidak ditemukan.

3. **Form Tambah & Edit Mata Kuliah**
   - Form serbaguna untuk menambahkan mata kuliah baru maupun memperbarui mata kuliah yang sudah ada.
   - Fitur validasi input (kode dan nama wajib diisi, SKS dan semester harus berupa angka positif).
   - Menyediakan tombol Simpan dan Batal.

---

## Teknologi yang Digunakan

- **Bahasa**: Kotlin
- **UI Toolkit**: Jetpack Compose & Material 3 (Tema Dark Slate)
- **Navigasi**: Navigation Compose dengan Type-Safe Routes (`kotlinx.serialization`)
- **Penyimpanan Data**: In-Memory Storage (`SnapshotStateList` / `CourseRepository`)

---

## Route & Halaman

- `HomeRoute`: Halaman utama berisi identitas mahasiswa dan daftar mata kuliah.
- `DetailRoute(courseId: Int)`: Halaman detail mata kuliah berdasarkan ID.
- `FormRoute(courseId: Int? = null)`: Halaman form tambah (jika `courseId` null) atau edit (jika `courseId` terisi).

---

## Cara Menjalankan Project

1. Buka folder project **KatalogMataKuliah** di **Android Studio**.
2. Pastikan koneksi internet aktif untuk download dependensi Gradle awal.
3. Lakukan Sync Gradle dengan memilih menu **File > Sync Project with Gradle Files**.
4. Jalankan emulator Android atau hubungkan perangkat fisik via USB Debugging.
5. Klik tombol **Run ('app')** atau tekan `Shift + F10`.

---

## Deklarasi Penggunaan AI

Aplikasi ini dikembangkan dengan bantuan kecerdasan buatan (AI) sebagai alat bantu (assistant tool) untuk mempermudah pemahaman konsep Jetpack Compose, penyusunan arsitektur navigasi type-safe, serta perapihan antarmuka sesuai dengan panduan tugas.
