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

    @GET("e11ce0ee71df303f3780") //personelagenda
    fun getPersonelAgenda(
//        @Header("Authorization") token: String,
    ): Call<ResponsePersonel>

    @GET("ce8b95acd992605bd00b")// daftaragenda
    fun getDaftarAgenda(
//        @Header("Authorization") token: String,
    ): Call<ResponseDaftarAgenda>

    // Untuk menampilkan surat konsep khusus detail
    @GET("fa4a6186628768d50e56")//detailagenda
    fun getDetailAgenda(
//        @Header("Authorization") token: String,
//        @Path("id") id: String
    ): Call<ResponseDetailAgenda>

    @GET("84edb94ca14196bc73f0")// loginagenda
    fun getLogin(
//        @Header("Authorization") authorization: String,
//        @Path("username") username: String,
//        @Path("password") password: String
    ): Call<ResponseUser>

    // Request detail surat
    @GET("302eb08f5bdbd5cb8c6e")//pendampingagenda
    fun getPendampingAgenda(
//        @Header("Authorization") authorization: String,
//        @Path("id_surat") id_surat: String,
//        @Path("id_histori") id_histori: String
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