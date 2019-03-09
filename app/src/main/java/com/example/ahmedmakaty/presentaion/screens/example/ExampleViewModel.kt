package com.example.ahmedmakaty.presentaion.screens.example

import android.arch.lifecycle.ViewModel
import com.example.ahmedmakaty.domain.interactor.example.GetExampleUseCase
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.subscribers.DisposableSubscriber
import okhttp3.Response
import okhttp3.ResponseBody

class ExampleViewModel (var exampleUseCase: GetExampleUseCase) : ViewModel() {
    fun operate() {
        exampleUseCase.execute(object: DisposableSubscriber<ResponseBody>(){
            override fun onComplete() {

            }

            override fun onNext(t: ResponseBody?) {

            }

            override fun onError(t: Throwable?) {

            }
        }, "")
    }
}