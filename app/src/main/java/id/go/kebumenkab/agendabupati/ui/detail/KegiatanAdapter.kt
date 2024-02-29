package id.go.kebumenkab.agendabupati.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.go.kebumenkab.agendabupati.R
import id.go.kebumenkab.agendabupati.databinding.ItemDetailAgendaBinding
import id.go.kebumenkab.agendabupati.model.Kegiatan

class KegiatanAdapter(private val kegiatanList: List<Kegiatan>?) : RecyclerView.Adapter<KegiatanAdapter.ViewHolder>() {
    var listener: RecyclerViewClickListener? = null

    class ViewHolder(val binding: ItemDetailAgendaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDetailAgendaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val kegiatan = kegiatanList[position]
        with(holder){
            with(kegiatanList!![position]){
                binding.apply {
                    textNamaKegiatan.text = "Nama Kegiatan: ${nama}"
                    textTempat.text = "Tempat: ${tempat}"
                    textTanggal.text = "Tanggal: ${tanggal}"
                    textJam.text = "Jam: ${jam}"
                    textHadir.text = "Hadir: ${hadir}"
                    textKeterangan.text = "Keterangan: ${keterangan}"
                    btnEdit.setOnClickListener {
                        listener!!.onItemEditClick(it,kegiatanList[position])
                    }
                    btnHapus.setOnClickListener {

                    }
                }
            }
        }


    }

    interface RecyclerViewClickListener {
        fun onItemEditClick(view: View, surat: Kegiatan)
        // Tambahkan metode lain sesuai kebutuhan, misalnya onDeleteClick, dll.
    }
    override fun getItemCount(): Int {
        return kegiatanList!!.size
    }
}