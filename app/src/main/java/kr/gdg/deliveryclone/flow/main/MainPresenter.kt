package kr.gdg.deliveryclone.flow.main

import kr.gdg.deliveryclone.mvp.BaseMvpPresenterImpl
import kr.gdg.deliveryclone.repository.Repository
import kr.gdg.deliveryclone.repository.RepositoryImpl

class MainPresenter : BaseMvpPresenterImpl<MainContract.View>(), MainContract.Presenter {

    private val repository  by lazy {
        RepositoryImpl()
    }

    override fun addCount(count: Int) {
//        repository.addCount(count)
//
//        val result = repository.getCount()

        mView?.updateView(count + 1)
    }

    override fun getAddr(lat: Double, lng: Double) {
        repository.convertAddr(129.075090, 35.179632)
    }
}