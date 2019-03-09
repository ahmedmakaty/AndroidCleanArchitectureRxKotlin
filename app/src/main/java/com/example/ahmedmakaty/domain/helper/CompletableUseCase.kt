package com.example.ahmedmakaty.domain.helper

import com.example.ahmedmakaty.domain.executor.PostExecutionThread
import com.example.ahmedmakaty.domain.executor.ThreadExecutor
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers

abstract class CompletableUseCase<Params>(
    var threadExecutor: ThreadExecutor,
    var postExecutionThread: PostExecutionThread
) {
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    /**
     * Builds a [Completable] which will be used when the current [CompletableUseCase] is executed.
     */
    abstract fun buildUseCaseObservable(params: Params): Completable


    /**
     * Executes the current use case.
     */
    open fun execute(disposableCompletableObserver: DisposableCompletableObserver, params: Params) {
        var observable: Completable = buildUseCaseObservable(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.getScheduler())
        compositeDisposable.add(observable.subscribeWith(disposableCompletableObserver))
    }

    /**
     * Unsubscribes from current [Disposable].
     */
    fun dispose() {
        if (compositeDisposable.isDisposed) compositeDisposable.dispose()
    }
}