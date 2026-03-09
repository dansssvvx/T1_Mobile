/* 
AHMAD RAMADHANI R
F1D02310102
*/

// Fungsi untuk menentukan apakah mahasiswa lulus atau tidak berdasarkan nilai akhir
fun getStatus(nilai: Double): String {
    return if (nilai >= 60) "LULUS" else "TIDAK LULUS"
}

// Fungsi untuk memberikan pesan selamat atau semangat
fun getPesan(nilai: Double): String {
    return if (nilai >= 60) {
        "Selamat! Anda dinyatakan LULUS."
    } else {
        "Mohon Maaf, Anda Tidak Lulus, coba lagi tahun depan!"
    }
}

// Fungsi untuk memberikan keterangan berdasarkan rentang nilai akhir
fun getKeterangan(nilai: Double): String {
    return when (nilai) {
        in 85.0..100.0 -> "Sangat Baik"
        in 70.0..84.9 -> "Baik"
        in 60.0..69.9 -> "Cukup"
        in 50.0..59.9 -> "Kurang"
        else -> "Sangat Kurang"
    }
}

// Fungsi untuk menentukan grade berdasarkan nilai akhir
fun getGrade(nilai: Double): String {
    return when (nilai) {
        in 85.0..100.0 -> "A"
        in 70.0..84.9 -> "B"
        in 60.0..69.9 -> "C"
        in 50.0..59.9 -> "D"
        else -> "E"
    }
}

// Fungsi untuk melakukan handle failure pada input nilai
fun validasiInput(label: String): Int {
    while (true) {
        print("Masukkan Nilai $label (0-100): ")
        val input = readLine()?.toIntOrNull()
        if (input != null && input in 0..100) {
            return input
        } else {
            println("Nilai tidak valid. Masukkan nilai antara 0 dan 100.")
        }
    }
}

fun main() {
    println("===== SISTEM PENILAIAN =====")
    println("")

    print("Masukkan Nama Mahasiswa: ")
    val nama = readLine() ?: "Mahasiswa"
    
    val nilaiUTS = validasiInput("UTS")
    val nilaiUAS = validasiInput("UAS")
    val nilaiTugas = validasiInput("Tugas")

    // Nilai akhir dihitung berdasarkan bobot: UTS 30%, UAS 40% dan Tugas 30%
    val nilaiAkhir = (nilaiUTS * 0.3) + (nilaiUAS * 0.4) + (nilaiTugas * 0.3)

    println("")
    println("===== HASIL PENILAIAN =====")
    println("Nama        : $nama")
    println("Nilai UTS   : $nilaiUTS (Bobot 30%)")
    println("Nilai UAS   : $nilaiUAS (Bobot 40%)")
    println("Nilai Tugas : $nilaiTugas (Bobot 30%)")
    println("------------------------------")
    println("Nilai Akhir : $nilaiAkhir")
    println("Grade       : ${getGrade(nilaiAkhir)}")
    println("Keterangan  : ${getKeterangan(nilaiAkhir)}")
    println("Status      : ${getStatus(nilaiAkhir)}")
    println("")
    
    println(getPesan(nilaiAkhir))
}
