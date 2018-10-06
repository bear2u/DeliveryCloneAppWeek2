# Android 배달앱 클론 스터디

## 목표
Android로 배달 앱을 클론 하면서 개발하는 방법과 패턴, RxJava등 사용법을 배우게 된다.

## MVP flow
![mvp flow](mvp.png)
![mvp flow](mvp2.png)

## Base MVP 

### View
화면 단위를 뜻한다. 
Fragment, Adapter, Activity

### Model
데이터 연결 하는 부분을 담당한다. 
Sqlite, Retrofit등

### Presenter
View <-> Model 간의 연결을 도와주는 역할을 한다. 

### Base 작성

#### BaseMvpView

```kotlin
interface BaseMvpView {

    fun getContext(): Context

    fun showError(error: String?)

    fun showError(@StringRes stringResId: Int)

    fun showMessage(@StringRes strResId: Int)

    fun showMessage(message: String)
}
```

#### BaseMvpPresenter

```kotlin
interface BaseMvpPresenter<in V : BaseMvpView> {

    fun attachView(view: V)

    fun detachView()
}
```

#### BaseMvpActivity

```kotlin
abstract class BaseMvpActivity<in V : BaseMvpView, T : BaseMvpPresenter<V>>
    : AppCompatActivity(), BaseMvpView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.attachView(this as V)

        title = ""
    }

    override fun getContext(): Context = this

    protected abstract var mPresenter: T

    override fun showError(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showError(stringResId: Int) {
        Toast.makeText(this, stringResId, Toast.LENGTH_LONG).show()
    }

    override fun showMessage(srtResId: Int) {
        Toast.makeText(this, srtResId, Toast.LENGTH_LONG).show()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}
```