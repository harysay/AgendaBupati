package id.go.kebumenkab.agendabupati.viewmodel

import android.content.Context
import android.util.Log
import android.widget.ArrayAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.go.kebumenkab.agendabupati.model.ResponsePendamping
import id.go.kebumenkab.agendabupati.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class PendampingViewModel : ViewModel() {
    private val _infodetailagenda = MutableLiveData<ResponsePendamping>()
    val livedetailagenda: LiveData<ResponsePendamping> = _infodetailagenda
    lateinit var errorType: String
    lateinit var errorDesc: String
    private val _selectedPersonelList = MutableLiveData<List<ResponsePendamping>>()
    val selectedPersonelList: LiveData<List<ResponsePendamping>> = _selectedPersonelList
    private val _selectedPendampingList = MutableLiveData<List<ResponsePendamping>>()
    val selectedPendampingList: LiveData<List<ResponsePendamping>> = _selectedPendampingList

    // Live data instance
    val createPostLiveData get() = _infodetailagenda

    private val _isRefresh = MutableLiveData<Boolean>()
    val isRefresh: LiveData<Boolean> = _isRefresh

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _infoserverdetailagenda = MutableLiveData<String>()
    val isInfoserverkonsep: LiveData<String> = _infoserverdetailagenda

    fun getPendampingAgenda(token: String,idDaftar:String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getPendampingAgenda()
        client.enqueue(object : Callback<ResponsePendamping> {
            override fun onResponse(call: Call<ResponsePendamping>, response: Response<ResponsePendamping>) {
                _isRefresh.value = false
                _isLoading.value = false
                if (response.isSuccessful) {
                    _infodetailagenda.value = response.body() as ResponsePendamping
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponsePendamping>, t: Throwable) {
                _isRefresh.value = false
                _isLoading.value = false

                if (t is IOException) {
                    errorType = "Timeout"
                    errorDesc = t.cause.toString()

                } else if (t is IllegalStateException) {
                    errorType = "ConversionError"
                    errorDesc = t.cause.toString()
                } else {
                    errorType = "Other Error"
                    errorDesc = t.localizedMessage.toString()
                }
                _infoserverdetailagenda.value = errorType+":"+errorDesc
                Log.e(TAG, "onFailure: ${t.message.toString()}")

            }
        })
    }

    fun addSelectedPersonel(personel: ResponsePendamping) {
        val currentList = _selectedPersonelList.value?.toMutableList() ?: mutableListOf()
        currentList.add(personel)
        _selectedPersonelList.value = currentList
    }

    fun addSelectedPendamping(pendamping: ResponsePendamping) {
        val currentList = _selectedPendampingList.value?.toMutableList() ?: mutableListOf()
        currentList.add(pendamping)
        _selectedPendampingList.value = currentList
    }

    companion object {
        private const val TAG = "PendampingAgenda"

    }
}