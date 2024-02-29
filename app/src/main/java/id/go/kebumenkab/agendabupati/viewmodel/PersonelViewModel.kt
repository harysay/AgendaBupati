package id.go.kebumenkab.agendabupati.viewmodel

import android.content.Context
import android.widget.ArrayAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.go.kebumenkab.agendabupati.model.Personel

class PersonelViewModel : ViewModel() {
    private val personelList = mutableListOf<Personel>()
    private val personelListAll = mutableListOf<Personel>()
    private lateinit var suratKonsepKhususList: ArrayList<Personel>

    private val _personelListLiveData = MutableLiveData<List<Personel>>()
    private val _personelListLiveDataAll = MutableLiveData<List<Personel>>()
    val personelListLiveData: LiveData<List<Personel>> get() = _personelListLiveData

    init {
        // Inisialisasi personelList pada saat ViewModel dibuat
        personelList.add(Personel("1", "Nama Contoh 1"))
        personelList.add(Personel("2", "Nama Contoh 2"))
        personelList.add(Personel("3", "Nama Contoh 3"))
        _personelListLiveData.value = personelList
    }

    fun addPersonel(personel: Personel) {
        personelList.add(personel)
        _personelListLiveData.value = personelList
    }

    fun removePersonel(personel: Personel) {
        personelList.remove(personel)
        _personelListLiveData.value = personelList
    }
}