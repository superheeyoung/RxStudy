package com.example.schedulerexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

//disposable
class DisposableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runBlocking {
            val observable: Observable<Long> =
                Observable.interval(100, TimeUnit.MILLISECONDS) //순서 1
            val observer: Observer<Long> = object : Observer<Long> {
                lateinit var disposable: Disposable //순서 2

                override fun onSubscribe(d: Disposable) {
                    disposable = d //순서 3
                }

                override fun onNext(t: Long) {
                    Log.d("observer received :", t.toString())
                    if (t >= 10 && !disposable.isDisposed) { //순서 4
                        disposable.dispose() //순서 5
                        Log.d("observer", "disposed")
                    }
                }

                override fun onError(e: Throwable?) {
                }

                override fun onComplete() {
                }

            }
            observable.subscribe(observer)
            delay(1500) //순서 6
        }
    }

}
