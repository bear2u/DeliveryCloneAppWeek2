package kr.gdg.deliveryclone.model

import com.squareup.moshi.Json

data class ResponseAddress(
        @field:Json(name = "result") val result : Address
)

data class Address (
        @field:Json(name = "total") val total : Int,
        @field:Json(name = "items") val items : List<AddressItem>
)

data class AddressItem (
        @field:Json(name = "address") val address : String
)
