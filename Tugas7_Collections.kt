/* 
AHMAD RAMADHANI R
F1D02310102
*/

data class NilaiMahasiswa(
    val nim: String,
    val nama: String,
    val matakuliah: String,
    val nilai: Int
)

fun getGrade(nilai: Int): String {
    return when (nilai) {
        in 85..100 -> "A"
        in 70..84 -> "B"
        in 60..69 -> "C"
        in 50..59 -> "D"
        else -> "E"
    }
}

fun main() {
    //Buat list berisi minimal 10 data mahasiswa
    val listMahasiswa = mutableListOf(
        NilaiMahasiswa("F1D02310102", "Ahmad Ramadhani R", "Pemrograman", 75),
        NilaiMahasiswa("F1D02310103", "Ahram Damarhani R", "Pemrograman", 92),
        NilaiMahasiswa("F1D02310104", "Amrad Hamarnhai R", "Pemrograman", 68),
        NilaiMahasiswa("F1D02310105", "Armar Naramhadi R", "Pemrograman", 45),
        NilaiMahasiswa("F1D02310106", "Ahram Ramdahani R", "Pemrograman", 60),
        NilaiMahasiswa("F1D02310107", "Arham Ramadhani R", "Pemrograman", 85),
        NilaiMahasiswa("F1D02310108", "Amrah Ramdahani R", "Pemrograman", 72),
        NilaiMahasiswa("F1D02310109", "Amdar Rahmani R", "Pemrograman", 58),
        NilaiMahasiswa("F1D02310110", "Armad Rahmani R", "Pemrograman", 80),
        NilaiMahasiswa("F1D02310111", "Ahramad Ramhani R", "Pemrograman", 50)
    )

    // Implementasikan berbagai operasi collection

    // Tampilkan semua data mahasiswa
    println("===== DATA NILAI MAHASISWA =====")
    println("%-4s %-10s %-15s %-15s %-5s".format("No", "NIM", "Nama", "MataKuliah", "Nilai"))
    listMahasiswa.forEachIndexed { index, m ->
        println("%-4d %-10s %-15s %-15s %-5d".format(index + 1, m.nim, m.nama, m.matakuliah, m.nilai))
    }

    // Hitung statistik: Total, Rata-rata, Tertinggi, Terendah
    println("\n===== STATISTIK =====")
    val totalMahasiswa = listMahasiswa.size
    val rataRata = listMahasiswa.map { it.nilai }.average()
    val nilaiTertinggi = listMahasiswa.maxByOrNull { it.nilai }
    val nilaiTerendah = listMahasiswa.minByOrNull { it.nilai }

    println("Total Mahasiswa : $totalMahasiswa")
    println("Rata-rata Nilai : %.1f".format(rataRata))
    println("Nilai Tertinggi : ${nilaiTertinggi?.nilai} (${nilaiTertinggi?.nama})")
    println("Nilai Terendah  : ${nilaiTerendah?.nilai} (${nilaiTerendah?.nama})")

    // Filter mahasiswa yang lulus (nilai >= 70)
    println("\n===== MAHASISWA LULUS =====")
    val lulus = listMahasiswa.filter { it.nilai >= 70 }
    lulus.forEachIndexed { index, m ->
        println("${index + 1}. ${m.nama} - ${m.nilai} (${getGrade(m.nilai)})")
    }

    //  Filter mahasiswa yang tidak lulus (nilai < 70)
    println("\n===== MAHASISWA TIDAK LULUS =====")
    val tidakLulus = listMahasiswa.filter { it.nilai < 70 }
    tidakLulus.forEachIndexed { index, m ->
        println("${index + 1}. ${m.nama} - ${m.nilai} (${getGrade(m.nilai)})")
    }

    // Urutkan berdasarkan nilai (descending)
    println("\n===== URUTAN NILAI (DESCENDING) =====")
    val urutanDesc = listMahasiswa.sortedByDescending { it.nilai }
    urutanDesc.forEach { m ->
        println("${m.nama}: ${m.nilai}")
    }

    // Kelompokkan dan hitung jumlah per grade
    println("\n===== JUMLAH PER GRADE =====")
    val groupedByGrade = listMahasiswa.groupBy { getGrade(it.nilai) }
    listOf("A", "B", "C", "D", "E").forEach { grade ->
        val count = groupedByGrade[grade]?.size ?: 0
        println("Grade $grade: $count mahasiswa")
    }

    //Cari mahasiswa berdasarkan nama
    println("\n===== PENCARIAN MAHASISWA =====")
    val searchName = "Ani"
    val found = listMahasiswa.filter { it.nama.contains(searchName, ignoreCase = true) }
    if (found.isNotEmpty()) {
        println("Hasil pencarian untuk '$searchName':")
        found.forEach { println("- ${it.nama} (NIM: ${it.nim})") }
    } else {
        println("Mahasiswa dengan nama '$searchName' tidak ditemukan.")
    }
}
