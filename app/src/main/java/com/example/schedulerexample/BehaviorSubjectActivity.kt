package com.example.schedulerexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.subjects.BehaviorSubject

class BehaviorSubjectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val subject = BehaviorSubject.create<Int>()
        subject.onNext(1)
        subject.onNext(2)
        subject.onNext(3)
        subject.onNext(4)

        subject.subscribe({
            //onNext
            Log.d("S1 received", it.toString())
        }, {
            it.printStackTrace()
        }, {
            Log.d("S1", "complete")
        })

        subject.onNext(5)

        subject.subscribe({
            //onNext
            Log.d("S2 Received", it.toString())
        }, {
            it.printStackTrace()
        }, {
            Log.d("S2" ,"Complete")
        })

        subject.onComplete()
    }
}