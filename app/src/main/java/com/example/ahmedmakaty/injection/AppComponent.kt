package com.example.ahmedmakaty.injection

import android.app.Application
import com.example.ahmedmakaty.App
import com.example.ahmedmakaty.injection.modules.AppModule
import com.example.ahmedmakaty.injection.modules.FragmentBindingModule
import com.example.ahmedmakaty.injection.modules.NetworkModule
import com.example.ahmedmakaty.injection.modules.data.ExampleDataModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        AppModule::class,
        NetworkModule::class,
        ExampleDataModule::class,
        AndroidSupportInjectionModule::class,
        FragmentBindingModule::class
    )
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}