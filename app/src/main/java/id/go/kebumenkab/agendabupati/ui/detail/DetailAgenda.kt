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

class DetailAgenda : AppCompatActivity(),KegiatanAdapter.RecyclerViewClickListener {
    private lateinit var binding: ActivityDetailAgendaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAgendaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle("Detail Agenda")

        init()
    }

    private fun init() {
//        suratDetailViweModel = ViewModelProvider(this)[SuratDetailKhususViewModel::class.java]
//        dialog = DialogUtils()
//        /** Mengecek internet  */
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
//        val user = HawkStorageEletter.instance(this@DetailKonsepKhusus).getUser()
//        token = user.token.toString()
//        position = user.detail?.jabatan.toString()
    }

    private fun getDetail() {
//        val btnEdit = findViewById<Button>(R.id.btnEdit)
//        btnEdit.setOnClickListener {
//            val intent = Intent(this, EditDetailActivity::class.java)
//            startActivity(intent)
//        }

//        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewKegiatan)
//        recyclerView.layoutManager = LinearLayoutManager(this)

        // Contoh data dummy
        val kegiatanList = listOf(
            Kegiatan(
                "Exit Meeting Pemeriksaan BPK Perwakilan Provinsi Jawa Tengah Terkait Pemeriksaan Pendahuluan Kepatuhan Belanja Infrastruktur TA",
                "Ruang Rapat Arungbinang",
                "2023-10-06",
                "13:00:00",
                "Nama Orang",
                "Isi keterangan untuk kegiatan pertama"
            ),
            Kegiatan(
                "Kegiatan Kedua",
                "Tempat Kedua",
                "2023-10-07",
                "14:30:00",
                "Nama Lain",
                "Keterangan lain untuk kegiatan kedua"
            ),
            Kegiatan(
                "Kegiatan Ketiga",
                "Tempat Ketiga",
                "2023-10-07",
                "15:30:00",
                "Nama Lain",
                "Keterangan lain untuk kegiatan kedua"
            ),
            // Tambahkan kegiatan lainnya sesuai kebutuhan
        )


        val adapter = KegiatanAdapter(kegiatanList)
        binding.recyclerViewKegiatan.adapter = adapter
        binding.recyclerViewKegiatan.layoutManager = LinearLayoutManager(this)
        adapter.listener = this

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onItemEditClick(view: View, surat: Kegiatan) {
        val intent = Intent(this, EditDetailActivity::class.java)
        startActivity(intent)
    }
}