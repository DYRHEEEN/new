package id.kasrt

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.kasrt.model.ResponseUser
import id.kasrt.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LaporanActivity : AppCompatActivity() {

    private lateinit var adapter: PemanfaatanAdapter
    private lateinit var rvlaporan: RecyclerView
    private lateinit var totalIuranTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laporan)

        rvlaporan = findViewById(R.id.rvlaporan)
        totalIuranTextView = findViewById(R.id.totalIuranTextView)

        adapter = PemanfaatanAdapter(mutableListOf())

        rvlaporan.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvlaporan.adapter = adapter

        getPemanfaatan()
    }

    private fun getPemanfaatan() {
        val apiService = ApiConfig.getApiService()
        val client = apiService.getPemanfaatan()

        client.enqueue(object : Callback<ResponseUser> {
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                if (response.isSuccessful) {
                    val dataArray = response.body()?.data
                    if (dataArray != null) {
                        var totalPemasukan = 0
                        var totalPengeluaran = 0

                        for (dataItem in dataArray) {
                            totalPemasukan += dataItem.pemasukan
                            totalPengeluaran += dataItem.pengeluaran
                        }
                        val rekapIuran = totalPemasukan - totalPengeluaran
                        totalIuranTextView.text = "Total Saldo\n Rp.$rekapIuran"

                        // Set data pemanfaatan pada adapter
                        adapter.setPemanfaatan(dataArray)
                    } else {
                        Toast.makeText(this@LaporanActivity, "Data not found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@LaporanActivity, "Failed to retrieve data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                Toast.makeText(this@LaporanActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })
    }
}