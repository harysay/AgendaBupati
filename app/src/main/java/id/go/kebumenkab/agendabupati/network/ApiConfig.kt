package id.go.kebumenkab.agendabupati.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiConfig {

    companion object{
        private const val REQUEST_TIMEOUT = 180

//        const val DOMAIN = "https://raw.githubusercontent.com/harysay/percobaanandoid/main/"
        const val DOMAIN = "https://api.npoint.io/"

        private var retrofitelet: Retrofit? = null

        fun getApiService(): ApiService {
            val loggingInterceptor2 =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            val gson = GsonBuilder()
                .setLenient()
                .create()

            val client = OkHttpClient.Builder()
                .connectTimeout(90, TimeUnit.SECONDS)
                .readTimeout(90, TimeUnit.SECONDS)
                .writeTimeout(90, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor2)
                .build()

            val retrofiteletter = Retrofit.Builder()
                .baseUrl(DOMAIN)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()

            return retrofiteletter.create(ApiService::class.java)
        }
    }
}