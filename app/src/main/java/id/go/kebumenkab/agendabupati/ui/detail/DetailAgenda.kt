package id.go.kebumenkab.agendabupati.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import id.go.kebumenkab.agendabupati.R
import id.go.kebumenkab.agendabupati.databinding.ActivityDetailAgendaBinding
import id.go.kebumenkab.agendabupati.model.Kegiatan
import id.go.kebumenkab.agendabupati.utils.NetworkUtil
import io.github.muddz.styleabletoast.StyleableToast
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import id.go.kebumenkab.agendabupati.model.DatadaftaragendaItem
import id.go.kebumenkab.agendabupati.model.DatadetailItem
import id.go.kebumenkab.agendabupati.utils.DialogUtils
import id.go.kebumenkab.agendabupati.utils.HawkStorage
import id.go.kebumenkab.agendabupati.viewmodel.DetailAgendaViewModel

class DetailAgenda : AppCompatActivity(),KegiatanAdapter.RecyclerViewClickListener {
    private var dataDaftarAgenda: DatadaftaragendaItem? = null
    private lateinit var iddaftaragenda: String
    private lateinit var binding: ActivityDetailAgendaBinding
    private lateinit var daftarAgendaViweModel: DetailAgendaViewModel
    private lateinit var dataDetailItemList: ArrayList<DatadetailItem>
    private lateinit var token: String
    private lateinit var dialog: DialogUtils
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAgendaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle("Detail Agenda")

        init()
    }

    private fun init() {
        daftarAgendaViweModel = ViewModelProvider(this)[DetailAgendaViewModel::class.java]
        dialog = DialogUtils()
        binding.swipeRefreshLayout.setOnRefreshListener {
            getDetail()
        }
       /** Mengecek internet  */
        val isConnected: Boolean = NetworkUtil.cekInternet(applicationContext)
        /** Bila memiliki koneksi internet maka panggil api detail  */
        if (isConnected) {
            setPref()
            getDetail()
        } else {
            StyleableToast.makeText(this@DetailAgenda, "Mohon maaf, kami tidak bisa mengambil data, silakan periksa internet Anda.", R.style.Toast_Error).show()
        }

    }

    private fun setPref() {
        val user = HawkStorage.instance(this@DetailAgenda).getUser()
        token = user.token.toString()
//        position = user.detail?.jabatan.toString()
    }

    private fun getDetail() {
        dataDaftarAgenda = intent.getParcelableExtra("daftaragendalist")
        iddaftaragenda = dataDaftarAgenda?.id.toString()
        setDetail()
    }

    private fun setDetail(){
        daftarAgendaViweModel.isRefresh.observe(this@DetailAgenda) {
            setRefresh(it)
        }
        daftarAgendaViweModel.isLoading.observe(this@DetailAgenda){
//            setLoading(it)
            binding.swipeRefreshLayout.isRefreshing = it
        }
        daftarAgendaViweModel.getDetailAgenda(token,iddaftaragenda)
        daftarAgendaViweModel.livedetailagenda.observe(this@DetailAgenda){
            val status = it.status
            if (status=="success"){
                dataDetailItemList = it.datadetail as ArrayList<DatadetailItem>
                val adapter = KegiatanAdapter(dataDetailItemList)
                binding.recyclerViewKegiatan.adapter = adapter
                binding.recyclerViewKegiatan.setHasFixedSize(true)
                adapter.listener = this

            }else{
                dialog.hideDialog()
            }
        }
    }

    private fun setRefresh(isRefresh: Boolean) {
        binding.swipeRefreshLayout.isRefreshing = isRefresh
    }

//    private fun setLoading(isLoading: Boolean) {
//        if (isLoading) {
//            dialog.showProgressDialog(this@DetailAgenda)
//        } else {
//            dialog.hideDialog()
//        }
//    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onItemEditClick(view: View, agenda: DatadetailItem) {
        val intent = Intent(this, EditDetailActivity::class.java)
        intent.putExtra("agenda_item",agenda)
        startActivity(intent)
    }

    override fun onItemDeleteClick(view: View, agenda: DatadetailItem) {
        StyleableToast.makeText(this, "Delete: ${agenda.id} berhasil diklik", R.style.Toast_Success).show()
    }

    override fun onItemLampiranClick(view: View, agenda: DatadetailItem) {
        StyleableToast.makeText(this, "Lampiran: ${agenda.lampiran} ditampilkan", R.style.Toast_Success).show()
    }
}