// Fungsi untuk menentukan apakah mahasiswa lulus atau tidak berdasarkan nilai akhir
fun lulus(nilai: Double): String {
    return if (nilai >= 60) {
        "Selamat! anda dinyatakan LULUS!"
    } else {
        "Mohon Maaf, Anda Tidak Lulus, coba lagi tahun depan!"
    }
}

// Fungsi untuk memberikan keterangan berdasarkan rentang nilai akhir
fun keterangan(nilai: Double): String {
    return when (nilai) {
        in 85.0..100.0 -> "Sangat Baik"
        in 70.0..84.9 -> "Baik"
        in 60.0..69.9 -> "Cukup"
        in 50.0..59.9 -> "Kurang"
        else -> "Sangat Kurang"
    }
}

// Fungsi untuk menentukan grade berdasarkan nilai akhir
fun grade(nilai: Double): String {
    return when (nilai) {
        in 85.0..100.0 -> "A"
        in 70.0..84.9 -> "B"
        in 60.0..69.9 -> "C"
        in 50.0..59.9 -> "D"
        else -> "E"
    }
}

// Fungsi untuk melakukan handle failure pada input nilai
fun validasiinput(input: Int): Int {
    return if (input in 0..100) {
        input
    } else {
        //jika input tidak valid, tampilkan pesan error dan minta input ulang
        println("Nilai tidak valid. Masukkan nilai antara 0 dan 100.")
        val ulang = readLine()?.toIntOrNull()
        if (ulang != null) {
            validasiinput(ulang)
        } else {
            //jika input tidak bisa diubah menjadi angka, tampilkan pesan error dan minta input ulang
            println("Input harus berupa angka.")
            validasiinput(-1)
        }
    }
}

/* 
AHMAD RAMADHANI R
F1D02310102
*/

fun main(){
    // Deklarasi variabel untuk menyimpan data mahasiswa dan nilai uts, uas serta tugas
    val nama: String
    val nilaiuts: Int
    val nilaiuas: Int
    val nilaitugas: Int
    val nilaiakhir: Double

    println("==== SISTEM PENILAIAN ====")

    print("Masukkan Nama Mahasiswa: ")
    nama = readLine()!!
    print("Masukkan Nilai UTS (0-100 ): ")
    nilaiuts = validasiinput(readLine()!!.toInt())//input nilai UTS dengan validasi menggunakan fungsi validasiinput
    print("Masukkan Nilai UAS (0-100 ): ")
    nilaiuas = validasiinput(readLine()!!.toInt())//input nilai UAS dengan validasi menggunakan fungsi validasiinput
    print("Masukkan Nilai Tugas (0-100 ): ")
    nilaitugas = validasiinput(readLine()!!.toInt())//input nilai Tugas dengan validasi menggunakan fungsi validasiinput

    //nilai akhir dihitung berdasarkan bobot masing-masing komponen penilaian, yaitu UTS 30%, UAS 40% dan Tugas 30%
    nilaiakhir = (nilaiuts * 0.3) + (nilaiuas * 0.4) + (nilaitugas * 0.3)

    println("")
    println("==== HASIL PENILAIAN ====")

    println("Nama Mahasiswa: $nama")
    println("Nilai UTS: $nilaiuts (Bobot 30%)" )
    println("Nilai UAS: $nilaiuas (Bobot 40%)" )
    println("Nilai Tugas: $nilaitugas (Bobot 30%)" )
    println("------------------------------" )
    println("Nilai Akhir: $nilaiakhir" )
    println("Grade: " + grade(nilaiakhir))
    println("Keterangan: " + keterangan(nilaiakhir))
    println("")
    
    println(lulus(nilaiakhir))

}