package com.example.ahmedmakaty.domain.repository

import okhttp3.Response
import io.reactivex.Flowable
import okhttp3.ResponseBody

interface ExampleRepository {
    fun getExample(): Flowable<ResponseBody>
}