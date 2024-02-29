package id.go.kebumenkab.agendabupati.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ResponseDaftarAgenda(

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("data")
	val datadaftaragenda: List<DatadaftaragendaItem?>? = null
)

@Parcelize
data class DatadaftaragendaItem(

	@field:SerializedName("no")
	val no: String? = null,

	@field:SerializedName("jmlkegiatan")
	val jmlkegiatan: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("tglformat")
	val tglformat: String? = null,

	@field:SerializedName("tanggal")
	val tanggal: String? = null
): Parcelable
