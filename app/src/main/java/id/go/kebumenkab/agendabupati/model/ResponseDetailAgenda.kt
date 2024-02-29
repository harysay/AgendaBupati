package id.go.kebumenkab.agendabupati.model

import com.google.gson.annotations.SerializedName

data class ResponseDetailAgenda(

	@field:SerializedName("datadetail")
	val datadetail: List<DatadetailItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DatadetailItem(

	@field:SerializedName("no")
	val no: String? = null,

	@field:SerializedName("keterangan")
	val keterangan: String? = null,

	@field:SerializedName("kontak")
	val kontak: String? = null,

	@field:SerializedName("tempat")
	val tempat: String? = null,

	@field:SerializedName("hadir")
	val hadir: String? = null,

	@field:SerializedName("pendamping")
	val pendamping: String? = null,

	@field:SerializedName("lampiran")
	val lampiran: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("tanggal")
	val tanggal: String? = null,

	@field:SerializedName("waktujam")
	val waktujam: String? = null,

	@field:SerializedName("agenda")
	val agenda: String? = null,

	@field:SerializedName("disposisi")
	val disposisi: String? = null
)
