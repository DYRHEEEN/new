package id.kasrt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.kasrt.model.DataItem

class PemanfaatanAdapter(private val pemanfaatan: MutableList<DataItem>) :
    RecyclerView.Adapter<PemanfaatanAdapter.ListViewHolder>() {

    constructor() : this(mutableListOf())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_laporan, parent, false)
        return ListViewHolder(view)
    }

    fun setPemanfaatan(dataItems: List<DataItem>) {
        pemanfaatan.clear()
        pemanfaatan.addAll(dataItems)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = pemanfaatan.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = pemanfaatan[position]

        holder.tvTanggal.text = item.tanggal
        holder.tvPemanfaatan.text = if (item.pemasukan > 0) item.pemasukan_iuran + ":" else item.pengeluaran_iuran + ":"
        holder.tvTotalIuranRekap.text = if (item.pemasukan > 0) "+Rp. ${item.pemasukan}" else "-Rp. ${item.pengeluaran}"
    }

    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var tvTanggal: TextView = itemView.findViewById(R.id.itemTanggal)
        var tvPemanfaatan: TextView = itemView.findViewById(R.id.itemPemanfaatan)
        var tvTotalIuranRekap: TextView = itemView.findViewById(R.id.itemDanaPemanfaatan)
    }
}
