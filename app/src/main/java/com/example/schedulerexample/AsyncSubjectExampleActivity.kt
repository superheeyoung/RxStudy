package com.example.schedulerexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.AsyncSubject

class AsyncSubjectExampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val observable = Observable.just(1, 2, 3, 4) //순서 1
        val subject = AsyncSubject.create<Int>() // 순서 2
        observable.subscribe(subject) //순서 3

        subject.subscribe({
            //onNext
            //순서 4
            Log.d("Received", it.toString())
        }, {
           //onError
           it.printStackTrace()
        }, {
            //onComplete
            Log.d("onCompleted","true")
        })

        subject.onComplete() // 순서 5
    }
}