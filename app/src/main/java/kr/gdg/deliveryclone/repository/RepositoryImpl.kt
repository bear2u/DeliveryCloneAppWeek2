package kr.gdg.deliveryclone.repository

import io.reactivex.Observable
import kr.gdg.deliveryclone.model.Address

class RepositoryImpl {

    val firebaseRepo : Repository by lazy {
        FirebaseRepository()
    }

    fun convertAddr(lng: Double, lat: Double): Observable<Address> = NetworkRepsitory.getConvertedAddr(lng, lat)
}