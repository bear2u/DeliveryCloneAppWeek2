package kr.gdg.deliveryclone.repository


interface Repository {

    fun addCount(count: Int)
    fun getCount() : Int

}