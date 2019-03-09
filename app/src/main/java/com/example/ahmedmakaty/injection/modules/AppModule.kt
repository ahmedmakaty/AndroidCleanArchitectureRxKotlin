package com.example.ahmedmakaty.injection.modules

import android.app.Application
import android.content.Context
import com.example.ahmedmakaty.data.executor.JobExecutor
import com.example.ahmedmakaty.data.remote.ApiServiceInterface
import com.example.ahmedmakaty.domain.executor.PostExecutionThread
import com.example.ahmedmakaty.domain.executor.ThreadExecutor
import com.example.ahmedmakaty.presentaion.UiThread
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AppModule {

    @Provides
    fun providesContext(application: Application): Context {
        return application
    }

    @Provides
    fun providesApiService(retrofit: Retrofit): ApiServiceInterface {
        return retrofit.create(ApiServiceInterface::class.java)
    }

    @Provides
    fun providesJobExecutor(): JobExecutor {
        return JobExecutor.getInstance()
    }

    @Provides
    fun providesThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    fun providesPostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }
}