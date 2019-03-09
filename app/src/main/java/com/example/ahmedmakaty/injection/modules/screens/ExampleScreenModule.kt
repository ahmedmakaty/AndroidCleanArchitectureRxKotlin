package com.example.ahmedmakaty.injection.modules.screens

import com.example.ahmedmakaty.domain.interactor.example.GetExampleUseCase
import com.example.ahmedmakaty.presentaion.screens.example.ExampleViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ExampleScreenModule {
    @Provides
    fun providesExampleViewModelFactory(exampleUseCase: GetExampleUseCase): ExampleViewModelFactory {
        return ExampleViewModelFactory(exampleUseCase)
    }
}