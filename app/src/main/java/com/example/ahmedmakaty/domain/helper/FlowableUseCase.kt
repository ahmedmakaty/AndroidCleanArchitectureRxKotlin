package com.example.ahmedmakaty.domain.helper

import com.example.ahmedmakaty.domain.executor.PostExecutionThread
import com.example.ahmedmakaty.domain.executor.ThreadExecutor
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber

open abstract class FlowableUseCase<T, Params>(
    var threadExecutor: ThreadExecutor,
    var postExecutionThread: PostExecutionThread
) {
    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    /**
     * Builds a [Single] which will be used when the current [FlowableUseCase] is executed.
     */
    abstract fun buildUseCaseObservable(params: Params?): Flowable<T>

    /**
     * Executes the current use case.
     */
    fun execute(observer: DisposableSubscriber<T>, params: Params) {
        val observable = this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.from(this.threadExecutor))
            .observeOn(this.postExecutionThread.getScheduler())
        addDisposable(observable.subscribeWith(observer))
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    fun dispose() {
        if (!compositeDisposable.isDisposed()) compositeDisposable.dispose()
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}