package id.go.kebumenkab.agendabupati.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.go.kebumenkab.agendabupati.model.Personel
import id.go.kebumenkab.agendabupati.databinding.ItemPersonelBinding
class PersonelAdapter(private var personelList: List<Personel>?) : RecyclerView.Adapter<PersonelAdapter.ViewHolder>() {
    var listener: RecyclerViewClickListener? = null

    class ViewHolder(val binding: ItemPersonelBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPersonelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(personelList!![position]){
                binding.apply {
                    textIdPersonel.text = id
                    textNamaPersonel.text = nama

                    btnDeletePersonel.setOnClickListener {

                    }
                }
            }
        }
    }

    fun submitList(newList: List<Personel>) {
        personelList = newList
        notifyDataSetChanged()
    }

    interface RecyclerViewClickListener {
        fun onItemPersonelClick(view: View, surat: Personel)
        // Tambahkan metode lain sesuai kebutuhan, misalnya onDeleteClick, dll.
    }
    override fun getItemCount() = personelList!!.size
}