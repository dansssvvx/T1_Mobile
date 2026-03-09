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
    // 3. Buat list berisi minimal 10 data mahasiswa
    val listMahasiswa = mutableListOf(
        NilaiMahasiswa("2024001", "Budi Santoso", "Pemrograman", 75),
        NilaiMahasiswa("2024002", "Ani Wijaya", "Pemrograman", 92),
        NilaiMahasiswa("2024003", "Citra Dewi", "Pemrograman", 68),
        NilaiMahasiswa("2024004", "Dani Pratama", "Pemrograman", 45),
        NilaiMahasiswa("2024005", "Eko Saputra", "Pemrograman", 60),
        NilaiMahasiswa("2024006", "Fajar Nugroho", "Pemrograman", 85),
        NilaiMahasiswa("2024007", "Gita Lestari", "Pemrograman", 72),
        NilaiMahasiswa("2024008", "Hendi Kurniawan", "Pemrograman", 58),
        NilaiMahasiswa("2024009", "Indah Permata", "Pemrograman", 80),
        NilaiMahasiswa("2024010", "Joko Susilo", "Pemrograman", 50)
    )

    // 4. Implementasikan berbagai operasi collection

    // 1. Tampilkan semua data mahasiswa
    println("===== DATA NILAI MAHASISWA =====")
    println("%-4s %-10s %-15s %-15s %-5s".format("No", "NIM", "Nama", "MataKuliah", "Nilai"))
    listMahasiswa.forEachIndexed { index, m ->
        println("%-4d %-10s %-15s %-15s %-5d".format(index + 1, m.nim, m.nama, m.matakuliah, m.nilai))
    }

    // 4. Hitung statistik: Total, Rata-rata, Tertinggi, Terendah
    println("\n===== STATISTIK =====")
    val totalMahasiswa = listMahasiswa.size
    val rataRata = listMahasiswa.map { it.nilai }.average()
    val nilaiTertinggi = listMahasiswa.maxByOrNull { it.nilai }
    val nilaiTerendah = listMahasiswa.minByOrNull { it.nilai }

    println("Total Mahasiswa : $totalMahasiswa")
    println("Rata-rata Nilai : %.1f".format(rataRata))
    println("Nilai Tertinggi : ${nilaiTertinggi?.nilai} (${nilaiTertinggi?.nama})")
    println("Nilai Terendah  : ${nilaiTerendah?.nilai} (${nilaiTerendah?.nama})")

    // 2. Filter mahasiswa yang lulus (nilai >= 70)
    println("\n===== MAHASISWA LULUS =====")
    val lulus = listMahasiswa.filter { it.nilai >= 70 }
    lulus.forEachIndexed { index, m ->
        println("${index + 1}. ${m.nama} - ${m.nilai} (${getGrade(m.nilai)})")
    }

    // 3. Filter mahasiswa yang tidak lulus (nilai < 70)
    println("\n===== MAHASISWA TIDAK LULUS =====")
    val tidakLulus = listMahasiswa.filter { it.nilai < 70 }
    tidakLulus.forEachIndexed { index, m ->
        println("${index + 1}. ${m.nama} - ${m.nilai} (${getGrade(m.nilai)})")
    }

    // 7. Urutkan berdasarkan nilai (descending)
    println("\n===== URUTAN NILAI (DESCENDING) =====")
    val urutanDesc = listMahasiswa.sortedByDescending { it.nilai }
    urutanDesc.forEach { m ->
        println("${m.nama}: ${m.nilai}")
    }

    // 8 & 9. Kelompokkan dan hitung jumlah per grade
    println("\n===== JUMLAH PER GRADE =====")
    val groupedByGrade = listMahasiswa.groupBy { getGrade(it.nilai) }
    listOf("A", "B", "C", "D", "E").forEach { grade ->
        val count = groupedByGrade[grade]?.size ?: 0
        println("Grade $grade: $count mahasiswa")
    }

    // 10. Cari mahasiswa berdasarkan nama
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
