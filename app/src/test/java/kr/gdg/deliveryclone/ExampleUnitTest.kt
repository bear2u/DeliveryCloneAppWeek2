package kr.gdg.deliveryclone

import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testRxJava() {
        Observable.just(1,2,3)
                .map{
                    it * 2
                }
                .subscribeBy (
                        onNext = { println(it) },
                        onError = { it.printStackTrace() },
                        onComplete = { println("done") }
                )
        assert(true);
    }
}
