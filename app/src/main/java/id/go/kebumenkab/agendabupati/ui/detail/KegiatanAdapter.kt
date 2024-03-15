package id.go.kebumenkab.agendabupati.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.go.kebumenkab.agendabupati.R
import id.go.kebumenkab.agendabupati.databinding.ItemDetailAgendaBinding
import id.go.kebumenkab.agendabupati.model.DatadetailItem
import id.go.kebumenkab.agendabupati.model.Kegiatan

class KegiatanAdapter(private val kegiatanList: List<DatadetailItem>?) : RecyclerView.Adapter<KegiatanAdapter.ViewHolder>() {
    var listener: RecyclerViewClickListener? = null

    class ViewHolder(val binding: ItemDetailAgendaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDetailAgendaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = kegiatanList?.getOrNull(position)
        currentItem?.let { item ->
            holder.binding.apply {
                // Membersihkan tampilan sebelum mengatur nilai
                textNamaKegiatan.text = ""
                textTempat.text = ""
                textTanggal.text = ""
                textJam.text = ""
                textHadir.text = ""
                textKeterangan.text = ""
                if (item.lampiran.equals("")){
                    lampiranlayout.visibility = View.GONE
                    textNamaKegiatan.text = "${item.agenda}"
                    textTempat.text = "Tempat: ${item.tempat}"
                    textTanggal.text = "${item.tanggal}"
                    textJam.text = "Jam: ${item.waktujam}"
                    textHadir.text = "Hadir: ${item.hadir}"
                    textKeterangan.text = "Keterangan: ${item.keterangan}"
                    btnEdit.setOnClickListener {
                        listener!!.onItemEditClick(it, kegiatanList!![position])
                    }
                    btnHapus.setOnClickListener {
                        listener!!.onItemDeleteClick(it,kegiatanList!![position])
                    }
                }else {
                    // Mengatur nilai tampilan sesuai dengan item saat ini
                    textNamaKegiatan.text = "${item.agenda}"
                    textTempat.text = "Tempat: ${item.tempat}"
                    textTanggal.text = "${item.tanggal}"
                    textJam.text = "Jam: ${item.waktujam}"
                    textHadir.text = "Hadir: ${item.hadir}"
                    textKeterangan.text = "Keterangan: ${item.keterangan}"
                    btnEdit.setOnClickListener {
                        listener!!.onItemEditClick(it, kegiatanList!![position])
                    }
                    btnHapus.setOnClickListener {
                        listener!!.onItemDeleteClick(it,kegiatanList!![position])
                    }
                    btnLampiran.setOnClickListener {
                        listener!!.onItemLampiranClick(it,kegiatanList!![position])
                    }
                }
            }
        }
    }

    interface RecyclerViewClickListener {
        fun onItemEditClick(view: View, agenda: DatadetailItem)
        // Tambahkan metode lain sesuai kebutuhan, misalnya onDeleteClick, dll.
        fun onItemDeleteClick(view: View,agenda:DatadetailItem)
        fun onItemLampiranClick(view: View,agenda:DatadetailItem)
    }
    override fun getItemCount(): Int {
        return kegiatanList!!.size
    }
}