package id.go.kebumenkab.agendabupati.model

import com.google.gson.annotations.SerializedName

data class ResponseUser(

	@field:SerializedName("detail")
	val detail: Detail? = null,

	@field:SerializedName("type")
	val type: Int? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("token")
	val token: String? = null
)

data class Detail(

	@field:SerializedName("id_jabatan")
	val idJabatan: String? = null,

	@field:SerializedName("nik")
	val nik: String? = null,

	@field:SerializedName("unit_kerja")
	val unitKerja: String? = null,

	@field:SerializedName("status_jabatan")
	val statusJabatan: String? = null,

	@field:SerializedName("pns")
	val pns: Int? = null,

	@field:SerializedName("nip")
	val nip: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("jabatan")
	val jabatan: String? = null,

	@field:SerializedName("nip_lama")
	val nipLama: String? = null,

	@field:SerializedName("id_pns")
	val idPns: String? = null,

	@field:SerializedName("id_unit_kerja")
	val idUnitKerja: String? = null
)
