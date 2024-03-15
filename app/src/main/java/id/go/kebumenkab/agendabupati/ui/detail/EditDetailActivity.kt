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
import id.go.kebumenkab.agendabupati.R
import id.go.kebumenkab.agendabupati.databinding.ActivityEditDetailBinding
import id.go.kebumenkab.agendabupati.model.DatadaftaragendaItem
import id.go.kebumenkab.agendabupati.model.DatadetailItem
import id.go.kebumenkab.agendabupati.model.Personel
import id.go.kebumenkab.agendabupati.utils.NetworkUtil
import id.go.kebumenkab.agendabupati.viewmodel.PersonelViewModel
import io.github.muddz.styleabletoast.StyleableToast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class EditDetailActivity : AppCompatActivity(),PersonelAdapter.RecyclerViewClickListener {
    private var dataDetailItemAgenda: DatadetailItem? = null
    private lateinit var binding: ActivityEditDetailBinding
    private var personelList: ArrayList<Personel>? = null
    private var personelListAll: ArrayList<Personel>? = null
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
    private lateinit var status: String

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
//        // Menambahkan 3 data Personel ke dalam personelList
//        personelList?.add(Personel("1", "Nama Contoh 1"))
//        personelList?.add(Personel("2", "Nama Contoh 2"))
//        personelList?.add(Personel("3", "Nama Contoh 3"))
//
//        personelListAll?.add(Personel("1", "Nama Contoh 1"))
//        personelListAll?.add(Personel("2", "Nama Contoh 2"))
//        personelListAll?.add(Personel("3", "Nama Contoh 3"))
//        personelListAll?.add(Personel("4", "Saya adalah 1"))
//        personelListAll?.add(Personel("5", "Saya Adalah 2"))
//        personelListAll?.add(Personel("6", "Saya Adalah 3"))

//        val user = HawkStorageEletter.instance(this@DetailKonsepKhusus).getUser()
//        token = user.token.toString()
//        position = user.detail?.jabatan.toString()
    }

    private fun getDetailEdit() {
        setPref()
        // Setup the RecyclerView with the adapter
        val adapter = PersonelAdapter(personelList)
        binding.recyclerViewPersonel.adapter = adapter
        binding.recyclerViewPersonel.setHasFixedSize(true)
        adapter.listener = this

        // Update RecyclerView when personelList changes
        viewModel.personelListLiveData.observe(this, { newList ->
            adapter.submitList(newList)
        })

        // Setup AutoCompleteTextView
        setupAutoCompleteTextView()
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onItemPersonelClick(view: View, surat: Personel) {
        TODO("Not yet implemented")
    }

    private fun setupAutoCompleteTextView() {
        val autoCompleteTextView: AutoCompleteTextView = findViewById(R.id.autoCompletePersonel)

        // Buat adapter untuk AutoCompleteTextView
        val adapter = personelListAll?.let {
            ArrayAdapter(
                this,
                android.R.layout.simple_dropdown_item_1line,
                it.map { it.nama } // Ambil hanya nama personel
            )
        }

        // Atur adapter ke AutoCompleteTextView
        autoCompleteTextView.setAdapter(adapter)

        // Atur aksi yang akan dijalankan saat item dipilih dari AutoCompleteTextView
        autoCompleteTextView.setOnItemClickListener { _, _, position, _ ->
            // Lakukan sesuatu saat item dipilih, seperti menambahkan personel ke RecyclerView
            val selectedPersonel = personelListAll?.get(position)
            viewModel.addPersonel(selectedPersonel!!)
            autoCompleteTextView.text.clear()
        }
    }
}