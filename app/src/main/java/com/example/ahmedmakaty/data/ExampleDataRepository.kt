package com.example.ahmedmakaty.data

import com.example.ahmedmakaty.data.cache.example.ExacmpleCache
import com.example.ahmedmakaty.data.remote.example.ExampleRemote
import com.example.ahmedmakaty.domain.repository.ExampleRepository
import io.reactivex.Flowable
import okhttp3.Response
import okhttp3.ResponseBody
import javax.inject.Inject

class ExampleDataRepository @Inject constructor(var cache: ExacmpleCache, var remote: ExampleRemote): ExampleRepository {

    override fun getExample(): Flowable<ResponseBody> {
        return remote.getExample()
    }
}