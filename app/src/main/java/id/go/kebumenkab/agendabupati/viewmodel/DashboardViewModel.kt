package id.go.kebumenkab.agendabupati.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.go.kebumenkab.agendabupati.model.ResponseDaftarAgenda
import id.go.kebumenkab.agendabupati.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class DashboardViewModel : ViewModel() {

    private val _infodaftaragenda = MutableLiveData<ResponseDaftarAgenda>()
    val livedatadaftaragenda: LiveData<ResponseDaftarAgenda> = _infodaftaragenda
    lateinit var errorType: String
    lateinit var errorDesc: String

    // Live data instance
    val createPostLiveData get() = _infodaftaragenda

    private val _isRefresh = MutableLiveData<Boolean>()
    val isRefresh: LiveData<Boolean> = _isRefresh

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _infoserverdaftaragenda = MutableLiveData<String>()
    val isInfoserverkonsep: LiveData<String> = _infoserverdaftaragenda

    fun getDaftarAgenda(token: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDaftarAgenda()
        client.enqueue(object : Callback<ResponseDaftarAgenda> {
            override fun onResponse(call: Call<ResponseDaftarAgenda>, response: Response<ResponseDaftarAgenda>) {
                _isRefresh.value = false
                _isLoading.value = false
                if (response.isSuccessful) {
                    _infodaftaragenda.value = response.body() as ResponseDaftarAgenda
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseDaftarAgenda>, t: Throwable) {
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
                _infoserverdaftaragenda.value = errorType+":"+errorDesc
                Log.e(TAG, "onFailure: ${t.message.toString()}")

            }
        })
    }

    companion object {
        private const val TAG = "DaftarAgenda"

    }
}