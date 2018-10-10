package kr.gdg.deliveryclone.repository.api

import android.util.Log
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kr.gdg.deliveryclone.model.ResponseAddress
import retrofit2.Retrofit

object ApiManager {
    private const val BASE_SERVER: String = "https://openapi.naver.com/v1/map/"
    var retrofit: Retrofit

    init {
        Log.d("gdg", "created retrofit")
        retrofit = BaseApiManager.initRetrofit(BASE_SERVER)
    }

    fun getAddressFromLatLng(lat : Double, lng : Double) : Observable<ResponseAddress> {
        val addressService = retrofit.create(AddressService::class.java)
        return addressService.getAddress(query = "$lat,$lng")
                .subscribeOn(Schedulers.io())
    }
}