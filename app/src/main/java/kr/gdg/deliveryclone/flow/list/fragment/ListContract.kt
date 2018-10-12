package kr.gdg.deliveryclone.flow.list.fragment

import kr.gdg.deliveryclone.model.Store
import kr.gdg.deliveryclone.mvp.BaseMvpPresenter
import kr.gdg.deliveryclone.mvp.BaseMvpView

object ListContract {
    interface View: BaseMvpView {
        fun updateList(items: ArrayList<Store>)
    }

    interface Presenter: BaseMvpPresenter<View> {
        fun getStores(type: String?)
    }
}