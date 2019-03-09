package com.example.ahmedmakaty.injection.modules.data

import com.example.ahmedmakaty.data.ExampleDataRepository
import com.example.ahmedmakaty.data.cache.example.ExacmpleCache
import com.example.ahmedmakaty.data.cache.example.ExampleCacheImp
import com.example.ahmedmakaty.data.cache.helper.SharedPreferencesHelper
import com.example.ahmedmakaty.data.remote.ApiServiceInterface
import com.example.ahmedmakaty.data.remote.example.ExampleRemote
import com.example.ahmedmakaty.data.remote.example.ExampleRemoteImp
import com.example.ahmedmakaty.domain.repository.ExampleRepository
import dagger.Module
import dagger.Provides

@Module
class ExampleDataModule {

    @Provides
    fun providesExampleRepository(exacmpleCache: ExacmpleCache, exampleRemote: ExampleRemote) : ExampleRepository {
        return ExampleDataRepository(exacmpleCache, exampleRemote)
    }

    @Provides
    fun providesExampleCache(sharedPreferencesHelper: SharedPreferencesHelper) : ExacmpleCache {
        return ExampleCacheImp(sharedPreferencesHelper)
    }

    @Provides
    fun providesExampleRemote(apiServiceInterface: ApiServiceInterface):ExampleRemote {
        return ExampleRemoteImp(apiServiceInterface)
    }
}