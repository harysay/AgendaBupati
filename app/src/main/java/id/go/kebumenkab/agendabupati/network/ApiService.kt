package id.go.kebumenkab.agendabupati.network

import id.go.kebumenkab.agendabupati.model.ResponseDaftarAgenda
import id.go.kebumenkab.agendabupati.model.ResponseDetailAgenda
import id.go.kebumenkab.agendabupati.model.ResponseUser
import id.go.kebumenkab.agendabupati.model.ResponsePendamping
import id.go.kebumenkab.agendabupati.model.ResponsePersonel
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
//    @FormUrlEncoded
//    @POST("index.php/authandroid/login")
//    fun loginEletter(
//        @Field("username") username: String,
//        @Field("password") password: String
//    ): Call<UserResponseEletter>

    @GET("personelagenda.json")
    fun getPersonelAgenda(
        @Header("Authorization") token: String,
    ): Call<ResponsePersonel>

    // Untuk menampilkan surat konsep khusus
    @GET("daftaragenda.json")
    fun getDaftarAgenda(
//        @Header("Authorization") token: String,
    ): Call<ResponseDaftarAgenda>

    // Untuk menampilkan surat konsep khusus detail
    @GET("detailagenda.json")
    fun getDetailAgenda(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Call<ResponseDetailAgenda>

    // Untuk menampilkan surat yang belum diberi aksi
    @GET("loginagenda.json")
    fun getLogin(
//        @Header("Authorization") authorization: String,
//        @Path("username") username: String,
//        @Path("password") password: String
    ): Call<ResponseUser>

    // Request detail surat
    @GET("pendamping.json")
    fun getDetailKonsep(
        @Header("Authorization") authorization: String,
        @Path("id_surat") id_surat: String,
        @Path("id_histori") id_histori: String
    ): Call<ResponsePendamping>


//    // Untuk disposisi surat
//    @FormUrlEncoded
//    @POST("index.php/suratinternalprocess/disposisi/{id_surat}/{id_history}")
//    fun sendDisposisi(
//        @Header("Authorization") authorization: String,
//        @Field("pesan") pesan: String,
//        @Field("bawahan[]") bawahan: ArrayList<String>,
//        @Field("tindakan[]") tindakan: ArrayList<String>,
//        @Path("id_surat") id_surat: String,
//        @Path("id_history") id_history: String
//    ): Call<ResponStandar>


}