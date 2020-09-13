package com.arb.rxjava

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arb.rxjava.observable.ObservableMain
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val compositeDisposable = CompositeDisposable()
    var x = 1

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ObservableTxt1 = ObservableMain().setText1(2)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(ObservableTxt1())

        val ObservableTxt2 = ObservableMain().setText2()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { tv_txt2.text = "Bye" }


        val exit = ObservableMain().exit()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { finishAffinity() }

        compositeDisposable.add(ObservableTxt1)
        compositeDisposable.add(ObservableTxt2)
        compositeDisposable.add(exit)
    }

    fun ObservableTxt1() : DisposableObserver<Long> = object : DisposableObserver<Long>(){
        override fun onComplete() {

        }

        @SuppressLint("SetTextI18n")
        override fun onNext(t: Long) {

            when(x){
                1 -> tv_txt1.text = "Hello !!"
                2 -> tv_txt1.text = "Welcome to RxJava"
                else -> x = 0
            }
            x+=1
        }

        override fun onError(e: Throwable) {

        }

    }

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable.dispose()

    }

}