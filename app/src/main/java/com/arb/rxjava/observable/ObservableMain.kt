package com.arb.rxjava.observable

import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class ObservableMain {

    fun setText1(x : Long) : Observable<Long> = Observable.interval(1,x, TimeUnit.SECONDS)

    fun setText2() : Observable<Long> = Observable.timer(7 , TimeUnit.SECONDS )

    fun exit() : Observable<Long> = Observable.timer(10,TimeUnit.SECONDS)

}