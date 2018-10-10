package kr.gdg.deliveryclone.repository

import io.reactivex.Observable
import kr.gdg.deliveryclone.model.Address
import kr.gdg.deliveryclone.model.ResponseAddress
import kr.gdg.deliveryclone.repository.api.ApiManager

object NetworkRepsitory : Repository {

//    private var apiManager : ApiManager by lazy {
//        ApiManager()
//    }

    override fun addCount(count: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getConvertedAddr(lat: Double, lng: Double): Observable<ResponseAddress> {
        //129.075090,35.179632
        return ApiManager.getAddressFromLatLng(lat, lng)
    }
}