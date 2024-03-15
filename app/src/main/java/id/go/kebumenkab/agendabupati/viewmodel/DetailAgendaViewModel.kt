package id.go.kebumenkab.agendabupati.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.go.kebumenkab.agendabupati.model.ResponseDetailAgenda
import id.go.kebumenkab.agendabupati.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class DetailAgendaViewModel : ViewModel() {

    private val _infodetailagenda = MutableLiveData<ResponseDetailAgenda>()
    val livedetailagenda: LiveData<ResponseDetailAgenda> = _infodetailagenda
    lateinit var errorType: String
    lateinit var errorDesc: String

    // Live data instance
    val createPostLiveData get() = _infodetailagenda

    private val _isRefresh = MutableLiveData<Boolean>()
    val isRefresh: LiveData<Boolean> = _isRefresh

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _infoserverdetailagenda = MutableLiveData<String>()
    val isInfoserverkonsep: LiveData<String> = _infoserverdetailagenda

    fun getDetailAgenda(token: String,idDaftar:String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailAgenda()
        client.enqueue(object : Callback<ResponseDetailAgenda> {
            override fun onResponse(call: Call<ResponseDetailAgenda>, response: Response<ResponseDetailAgenda>) {
                _isRefresh.value = false
                _isLoading.value = false
                if (response.isSuccessful) {
                    _infodetailagenda.value = response.body() as ResponseDetailAgenda
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseDetailAgenda>, t: Throwable) {
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

    companion object {
        private const val TAG = "DaftarAgenda"

    }
}