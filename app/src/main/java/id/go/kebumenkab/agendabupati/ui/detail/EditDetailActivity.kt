package id.go.kebumenkab.agendabupati.ui.detail

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.go.kebumenkab.agendabupati.R
import id.go.kebumenkab.agendabupati.databinding.ActivityEditDetailBinding
import id.go.kebumenkab.agendabupati.model.DataItemPersonel
import id.go.kebumenkab.agendabupati.model.DatadaftaragendaItem
import id.go.kebumenkab.agendabupati.model.DatadetailItem
import id.go.kebumenkab.agendabupati.model.HadirItem
import id.go.kebumenkab.agendabupati.model.ResponsePersonel
import id.go.kebumenkab.agendabupati.utils.NetworkUtil
import id.go.kebumenkab.agendabupati.viewmodel.PersonelViewModel
import io.github.muddz.styleabletoast.StyleableToast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class EditDetailActivity : AppCompatActivity(),PersonelAdapter.RecyclerViewClickListener {
    private var dataDetailItemAgenda: DatadetailItem? = null
    private lateinit var binding: ActivityEditDetailBinding
    private var personelList: ArrayList<DataItemPersonel>? = null
    private lateinit var viewModel: PersonelViewModel
    private lateinit var iddetailagenda: String
    private lateinit var tanggalagenda: String
    private lateinit var jamagenda: String
    private lateinit var agendaacara: String
    private lateinit var lokasiagenda: String
    private lateinit var disposisiagenda: String
    private lateinit var keteranganagenda: String
    private lateinit var lampiranagenda: String
    private lateinit var kontrakpersonagenda: String
    private lateinit var id_personalhadir: String
    private lateinit var nama_personalhadir: String
    private lateinit var status: String
    private lateinit var adapter: PersonelAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Inisialisasi ViewModel
        viewModel = ViewModelProvider(this).get(PersonelViewModel::class.java)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle("Edit Detail Agenda")
        init()
    }

    private fun init() {
        val isConnected: Boolean = NetworkUtil.cekInternet(applicationContext)

        /** Bila memiliki koneksi internet */
        if (isConnected) {
            getDetailEdit()
        } else {
            StyleableToast.makeText(this@EditDetailActivity, "Mohon maaf, kami tidak bisa mengambil data, silakan periksa internet Anda.", R.style.Toast_Error).show()
        }

    }

    private fun setPref() {
        dataDetailItemAgenda = intent.getParcelableExtra("agenda_item")
        iddetailagenda = dataDetailItemAgenda?.id.toString()
        tanggalagenda= dataDetailItemAgenda?.tanggal.toString()
        jamagenda= dataDetailItemAgenda?.waktujam.toString()
        agendaacara= dataDetailItemAgenda?.agenda.toString()
        lokasiagenda= dataDetailItemAgenda?.tempat.toString()
        disposisiagenda= dataDetailItemAgenda?.disposisi.toString()
        keteranganagenda= dataDetailItemAgenda?.keterangan.toString()
        lampiranagenda= dataDetailItemAgenda?.lampiran.toString()
        kontrakpersonagenda= dataDetailItemAgenda?.kontak.toString()
        // Inisialisasi personelList
        val personelList: ArrayList<DataItemPersonel> = ArrayList()

// Cek apakah dataDetailItemAgenda tidak null
        if (dataDetailItemAgenda != null) {
            // Dapatkan list HadirItem dari dataDetailItemAgenda
            val hadirItems: List<HadirItem?>? = dataDetailItemAgenda?.hadir
            // Cek apakah hadirItems tidak null dan tidak kosong
            if (!hadirItems.isNullOrEmpty()) {
                // Loop melalui setiap HadirItem
                for (hadirItem in hadirItems) {
                    // Cek apakah hadirItem tidak null
                    if (hadirItem != null) {
                        // Konversi HadirItem menjadi DataItemPersonel
                        val dataItemPersonel = DataItemPersonel(id = hadirItem.personelId, nama = hadirItem.nama)
                        // Tambahkan dataItemPersonel ke dalam personelList
                        personelList.add(dataItemPersonel)
                    }
                }
                setupAutoCompletePersonal(personelList)
                initRecyclerView(personelList)
            }
        }
//        nama_personalhadir=
//        status= dataDetailItemAgenda?..toString()
        binding.editTextAgenda.setText(agendaacara)
        binding.editTextLokasi.setText(lokasiagenda)
        binding.editTextDisposisi.setText(disposisiagenda)
        binding.editTextKeterangan.setText(keteranganagenda)
        binding.editTextKontakPerson.setText(kontrakpersonagenda)
        binding.editTextTanggal.setText(tanggalagenda)
        binding.editTextJam.setText(jamagenda)
        binding.editTextTanggal.setOnClickListener {
            showDatePicker()
        }
        binding.editTextJam.setOnClickListener {
            showTimePicker()
        }
    }

    private fun getDetailEdit() {
        setPref()


//        // Setup the RecyclerView with the adapter
//        val adapter = PersonelAdapter(personelList)
//        binding.recyclerViewPersonel.adapter = adapter
//        binding.recyclerViewPersonel.setHasFixedSize(true)
//        adapter.listener = this

    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                binding.editTextTanggal.setText(selectedDate)
            },
            year, month, day
        )
        datePickerDialog.show()
    }
    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
                val selectedTime = "$selectedHour:$selectedMinute"
                binding.editTextJam.setText(selectedTime)
            },
            hour, minute, true
        )
        timePickerDialog.show()
    }

    private fun setupAutoCompletePersonal(lispersonil: List<DataItemPersonel>?) {
        viewModel.getPersonelAgenda("token", "iddaftaragenda")
        viewModel.livedetailagenda.observe(this@EditDetailActivity) { response ->
            val status = response.status
            if (status == "success") {
                val masterPersonelList = response.data as ArrayList<DataItemPersonel>
                // Filter daftar master untuk mendapatkan data yang belum ada di personelList
                val filteredMasterPersonelList = masterPersonelList.filter { masterItem ->
                    // Cek apakah item masterItem tidak ada di personelList
                    !lispersonil!!.any { personelItem -> personelItem.id == masterItem?.id }
                } as ArrayList<DataItemPersonel>

                val autoCompleteTextView: AutoCompleteTextView = findViewById(R.id.autoCompletePersonel)

                // Buat adapter untuk AutoCompleteTextView
                val adapter = ArrayAdapter<DataItemPersonel>(this, android.R.layout.simple_dropdown_item_1line, filteredMasterPersonelList)
                autoCompleteTextView.setAdapter(adapter)

                // Atur aksi yang akan dijalankan saat item dipilih dari AutoCompleteTextView
                autoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
                    val selectedPersonel = adapter.getItem(position)
                    selectedPersonel?.let {
                        // Tambahkan personel yang dipilih ke dalam daftar yang dipilih di ViewModel
                        viewModel.addSelectedPersonel(selectedPersonel)
                    }
                    autoCompleteTextView.text.clear()
                }
            }
        }

        // Observasi daftar personel dari ViewModel
        viewModel.selectedPersonelList.observe(this) { personelList ->
            // Tampilkan daftar personel yang dipilih di RecyclerView
            updateRecyclerView(personelList)
        }
    }

    private fun initRecyclerView(lispersonil: List<DataItemPersonel>?) {
        // Inisialisasi RecyclerView dan Adapter
        adapter = PersonelAdapter(lispersonil)
        binding.recyclerViewPersonel.adapter = adapter
        binding.recyclerViewPersonel.layoutManager = LinearLayoutManager(this)
        adapter.listener = this
        // Setup the RecyclerView with the adapter
//        val adapter = PersonelAdapter(personelList)
//        binding.recyclerViewPersonel.adapter = adapter
    }

    private fun updateRecyclerView(personelList: List<DataItemPersonel>) {
        adapter.submitList(personelList)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    override fun onItemPersonelClick(view: View, surat: DataItemPersonel) {
        TODO("Not yet implemented")
    }

//    private fun setupAutoCompleteTextView() {
//        val autoCompleteTextView: AutoCompleteTextView = findViewById(R.id.autoCompletePersonel)
//
//        // Buat adapter untuk AutoCompleteTextView
//        val adapter = personelListAll?.let {
//            ArrayAdapter(
//                this,
//                android.R.layout.simple_dropdown_item_1line,
//                it.map { it.nama } // Ambil hanya nama personel
//            )
//        }
//
//        // Atur adapter ke AutoCompleteTextView
//        autoCompleteTextView.setAdapter(adapter)
//
//        // Atur aksi yang akan dijalankan saat item dipilih dari AutoCompleteTextView
//        autoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
//            // Lakukan sesuatu saat item dipilih, seperti menambahkan personel ke RecyclerView
//            val selectedPersonel = personelListAll?.get(position)
//            viewModel.addPersonel(selectedPersonel!!)
//            autoCompleteTextView.text.clear()
//        }
//    }
}