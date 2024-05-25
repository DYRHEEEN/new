package id.kasrt.model

data class DataItem(
    val tanggal : String,
    val nama_depan: String,
    val nama_belakang: String,
    val email: String,
    val alamat: String,
    val pemasukan: Int,
    val pemasukan_iuran: String,
    val pengeluaran: Int,
    val pengeluaran_iuran: String,
    val Gambar: String
)
