package kr.gdg.deliveryclone.repository

import io.reactivex.Observable
import kr.gdg.deliveryclone.model.ResponseAddress


interface Repository {

    fun addCount(count: Int)
    fun getCount() : Int

    fun getConvertedAddr(lat : Double, lng : Double ) : Observable<ResponseAddress>

}