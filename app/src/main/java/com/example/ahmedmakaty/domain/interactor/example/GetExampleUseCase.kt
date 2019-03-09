package com.example.ahmedmakaty.domain.interactor.example

import com.example.ahmedmakaty.domain.exception.NoInternetException
import com.example.ahmedmakaty.domain.executor.PostExecutionThread
import com.example.ahmedmakaty.domain.executor.ThreadExecutor
import com.example.ahmedmakaty.domain.helper.FlowableUseCase
import com.example.ahmedmakaty.domain.interactor.InternetConnectionUseCase
import com.example.ahmedmakaty.domain.repository.ExampleRepository
import io.reactivex.Flowable
import okhttp3.Response
import okhttp3.ResponseBody
import javax.inject.Inject

class GetExampleUseCase @Inject constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    var internetConnectionUseCase: InternetConnectionUseCase,
    var exampleRepository: ExampleRepository
) : FlowableUseCase<ResponseBody, Any>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseObservable(params: Any?): Flowable<ResponseBody> {
        return internetConnectionUseCase.buildUseCaseObservable(null).switchMap { status ->
            if (status) {
                exampleRepository.getExample()
            } else {
                Flowable.error(NoInternetException())
            }
        }

    }
}