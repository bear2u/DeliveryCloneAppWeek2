package kr.gdg.deliveryclone.repository

import android.util.Log
import io.reactivex.rxkotlin.subscribeBy

class RepositoryImpl {

    val firebaseRepo : Repository by lazy {
        FirebaseRepository()
    }

//    val networkRepo : Repository by lazy {
//        NetworkRepsitory()
//    }

    var result = 0

    fun addCount(count: Int) {
        result = count + 1

        firebaseRepo.addCount(result);

    }

    fun getCount() : Int {
        return result
    }

    fun convertAddr(lat: Double, lng: Double) {
        NetworkRepsitory.getConvertedAddr(lat, lng)
                .subscribeBy(
                        onNext = {
                            Log.d("GDG", it.toString())
                        }
                )
    }
}