package kr.gdg.deliveryclone.repository

import io.reactivex.Observable
import kr.gdg.deliveryclone.model.Address
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

    override fun getConvertedAddr(lng: Double, lat: Double): Observable<Address> {
        //129.075090,35.179632
        return ApiManager.getAddressFromLatLng(lng, lat)
    }
}