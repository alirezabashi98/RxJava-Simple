package com.arb.rxjava.observable

import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class ObservableSplash {

    fun goToMain(): Observable<Long> = Observable.timer(3,TimeUnit.SECONDS)

}