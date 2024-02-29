package id.go.kebumenkab.agendabupati.model

import com.google.gson.annotations.SerializedName

data class ResponsePendamping(

	@field:SerializedName("datapendamping")
	val datapendamping: List<DatapendampingItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DatapendampingItem(

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)
