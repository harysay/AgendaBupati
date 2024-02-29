package id.go.kebumenkab.agendabupati.ui.detail

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
import id.go.kebumenkab.agendabupati.model.Personel
import id.go.kebumenkab.agendabupati.utils.NetworkUtil
import id.go.kebumenkab.agendabupati.viewmodel.PersonelViewModel
import io.github.muddz.styleabletoast.StyleableToast

class EditDetailActivity : AppCompatActivity(),PersonelAdapter.RecyclerViewClickListener {
    private lateinit var binding: ActivityEditDetailBinding
    private var personelList: ArrayList<Personel>? = null
    private var personelListAll: ArrayList<Personel>? = null
    private lateinit var viewModel: PersonelViewModel
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
        // Menambahkan 3 data Personel ke dalam personelList
        personelList?.add(Personel("1", "Nama Contoh 1"))
        personelList?.add(Personel("2", "Nama Contoh 2"))
        personelList?.add(Personel("3", "Nama Contoh 3"))

        personelListAll?.add(Personel("1", "Nama Contoh 1"))
        personelListAll?.add(Personel("2", "Nama Contoh 2"))
        personelListAll?.add(Personel("3", "Nama Contoh 3"))
        personelListAll?.add(Personel("4", "Saya adalah 1"))
        personelListAll?.add(Personel("5", "Saya Adalah 2"))
        personelListAll?.add(Personel("6", "Saya Adalah 3"))

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

    override fun onItemPersonelClick(view: View, surat: Personel) {
        TODO("Not yet implemented")
    }

    private fun setupAutoCompleteTextView() {
        val autoCompleteTextView: AutoCompleteTextView = findViewById(R.id.autoCompleteTextView)

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