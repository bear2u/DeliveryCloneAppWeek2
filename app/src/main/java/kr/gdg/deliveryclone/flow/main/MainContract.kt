package kr.gdg.deliveryclone.flow.main

import kr.gdg.deliveryclone.mvp.BaseMvpPresenter
import kr.gdg.deliveryclone.mvp.BaseMvpView

object MainContract {
    interface View : BaseMvpView {
        fun updateAddress(dong: String?)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun getAddr(lat: Double, lng: Double)
    }
}