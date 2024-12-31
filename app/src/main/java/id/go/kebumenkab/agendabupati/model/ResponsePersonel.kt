package id.go.kebumenkab.agendabupati.model

import com.google.gson.annotations.SerializedName

data class ResponsePersonel(

	@field:SerializedName("data")
	val data: List<DataItemPersonel?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataItemPersonel(

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("personel_id")
	val id: String? = null
)
