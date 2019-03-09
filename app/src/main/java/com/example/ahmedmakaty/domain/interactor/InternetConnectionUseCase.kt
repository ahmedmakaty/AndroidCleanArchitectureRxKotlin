package com.example.ahmedmakaty.domain.interactor

import com.example.ahmedmakaty.domain.executor.PostExecutionThread
import com.example.ahmedmakaty.domain.executor.ThreadExecutor
import com.example.ahmedmakaty.domain.helper.FlowableUseCase
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import io.reactivex.Flowable
import javax.inject.Inject

class InternetConnectionUseCase @Inject
constructor(threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread) :
    FlowableUseCase<Boolean, Void>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(v: Void?): Flowable<Boolean> {
        return ReactiveNetwork
            .checkInternetConnectivity()
            .toFlowable()
    }
}