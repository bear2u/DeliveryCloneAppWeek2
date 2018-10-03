package kr.gdg.deliveryclone.flow.main

import kr.gdg.deliveryclone.mvp.BaseMvpPresenterImpl
import kr.gdg.deliveryclone.repository.Repository
import kr.gdg.deliveryclone.repository.RepositoryImpl

class MainPresenter : BaseMvpPresenterImpl<MainContract.View>(), MainContract.Presenter {

    private val repository : Repository by lazy {
        RepositoryImpl()
    }

    override fun addCount(count: Int) {
        repository.addCount(count)

        val result = repository.getCount()

        mView?.updateView(result)
    }
}