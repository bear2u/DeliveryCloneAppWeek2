package kr.gdg.deliveryclone.model

data class Store(
        val no: Int,
        val name: String,
        val thumbnail : String,
        val star : Double,
        val review : Int,
        val priority: Int
)