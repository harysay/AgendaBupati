package id.go.kebumenkab.agendabupati.ui.dashboard

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.go.kebumenkab.agendabupati.databinding.ItemDashboardBinding
import id.go.kebumenkab.agendabupati.model.DatadaftaragendaItem

class DashboardListAdapter(private val items: List<DatadaftaragendaItem>?) : RecyclerView.Adapter<DashboardListAdapter.ViewHolder>() {
    var listener: RecyclerViewClickListener? = null
    class ViewHolder (val binding: ItemDashboardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDashboardBinding.inflate(LayoutInflater.from(parent.context), parent, false)//LayoutInflater.from(parent.context).inflate(R.layout.item_dashboard, parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = items?.getOrNull(position)
        currentItem?.let { item ->
            holder.binding.apply {
                dateTextView.text = item.tanggal
                txtJumlahKegiatan.text = item.jmlkegiatan
                iconTexttgl.text = item.tglformat?.substring(8, 10) ?: "" // Pastikan untuk menangani null
                messageContainer?.setOnClickListener {
                    listener?.onItemClickedListItemDashboard(it, item)
                }
            }
        }
    }

    interface RecyclerViewClickListener {

        // method yang akan dipanggil di MainActivity
        fun onItemClickedListItemDashboard(view: View, surat: DatadaftaragendaItem)

    }


    override fun getItemCount(): Int {
        return items!!.size
    }

}