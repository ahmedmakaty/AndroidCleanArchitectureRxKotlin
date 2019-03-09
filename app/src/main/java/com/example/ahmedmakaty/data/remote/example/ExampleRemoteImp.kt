package com.example.ahmedmakaty.data.remote.example

import com.example.ahmedmakaty.data.remote.ApiServiceInterface
import io.reactivex.Flowable
import okhttp3.Response
import okhttp3.ResponseBody
import javax.inject.Inject

class ExampleRemoteImp @Inject constructor(var apiServiceInterface: ApiServiceInterface) : ExampleRemote {
    override fun getExample(): Flowable<ResponseBody> {
        return apiServiceInterface.getLikes("android", "a772b84f5ac743dd803da86ef2b573cf", 10, 1)
    }
}