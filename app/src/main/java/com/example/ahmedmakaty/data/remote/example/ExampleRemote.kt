package com.example.ahmedmakaty.data.remote.example

import okhttp3.Response
import io.reactivex.Flowable
import okhttp3.ResponseBody

interface ExampleRemote {
    fun getExample(): Flowable<ResponseBody>
}